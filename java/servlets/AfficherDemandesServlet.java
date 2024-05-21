package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DemandeConge;
import Impl.DemandeCongeDAOImpl;

@WebServlet("/AfficherDemandesServlet")
public class AfficherDemandesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer le numéro PPR de l'employé depuis la session
        System.out.println("Début de la méthode doGet de la servlet AfficherDemandesServlet");
        Double numeroPPR = (Double) request.getSession().getAttribute("numeroPPR");

        // Vérifier si l'utilisateur est connecté
        if (numeroPPR == null) {
            // Rediriger vers une page d'erreur si l'utilisateur n'est pas connecté
            System.out.println("Utilisateur non connecté. Redirection vers error.jsp");
            response.sendRedirect("error.jsp");
            return;
        }

        // Accéder à la base de données pour récupérer les demandes de congé de l'employé
        try {
            DemandeCongeDAOImpl demandeCongeDAO = new DemandeCongeDAOImpl();
            List<DemandeConge> demandes = demandeCongeDAO.getDemandesParNumeroPPR(numeroPPR);
            
            // Mettre les demandes dans l'attribut de la requête pour les afficher dans la JSP
            request.setAttribute("demandes", demandes);
           if(demandes!=null) { System.out.println("C est nulllllll");};
           // Rediriger vers la page JSP pour afficher les demandes de congé
            System.out.println("Redirection vers afficher_demandes.jsp");
            request.getRequestDispatcher("afficher_demandes.jsp").forward(request, response);
        } catch (SQLException e) {
            // En cas d'erreur, rediriger vers une page d'erreur
            e.printStackTrace();
            System.out.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
            System.out.println("Redirection vers error.jsp");
            response.sendRedirect("error.jsp");
        }
        System.out.println("Fin de la méthode doGet de la servlet AfficherDemandesServlet");
    }

}
