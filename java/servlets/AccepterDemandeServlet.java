package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import interfaces.DemandeCongeDAO;
import Impl.DemandeCongeDAOImpl;

@WebServlet("/AccepterDemandeServlet")
public class AccepterDemandeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de la demande à partir des paramètres de la requête
        int idDemande = Integer.parseInt(request.getParameter("id"));

        // Mettre à jour l'état de la demande dans la base de données
        DemandeCongeDAO demandeCongeDAO = new DemandeCongeDAOImpl();
        try {
            demandeCongeDAO.updateEtatDemande(idDemande, "accepté par chef de division");
            // Redirection vers une page de confirmation
            response.sendRedirect("confirmation.jsp");
        } catch (SQLException e) {
            // Gérer les erreurs
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
