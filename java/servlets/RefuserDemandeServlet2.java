

package servlets;

import javax.servlet.annotation.WebServlet;
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
@WebServlet("/RefuserDemandeServlett")
public class RefuserDemandeServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de la demande à partir des paramètres de la requête
        int idDemande = Integer.parseInt(request.getParameter("id"));

        // Mettre à jour l'état de la demande dans la base de données
        try {
            // Votre code pour mettre à jour l'état de la demande dans la base de données
            // Par exemple :
            DemandeCongeDAOImpl demandeCongeDAO = new DemandeCongeDAOImpl();
            demandeCongeDAO.updateEtatDemande(idDemande, "refusé par  RH");

            // Rediriger vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("confirmationRefus.jsp");
        } catch (SQLException e) {
            // Gérer les erreurs de base de données
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
