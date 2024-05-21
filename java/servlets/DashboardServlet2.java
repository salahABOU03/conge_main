package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Impl.DemandeCongeDAOImpl;
import model.DemandeConge;

@WebServlet("/dashboardser2")
public class DashboardServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DemandeCongeDAOImpl demandeCongeDAO = new DemandeCongeDAOImpl();
        List<DemandeConge> demandes = null;
        try {
            demandes = demandeCongeDAO.listerDemandesConge();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur
        }
        
        List<DemandeConge> demandesFiltrees = new ArrayList<>();
        if (demandes != null && !demandes.isEmpty()) {
            for (DemandeConge demande : demandes) {
                if ("accepté par chef de division".equals(demande.getEtat())) {
                    demandesFiltrees.add(demande);
                }
            }
            request.setAttribute("demandes", demandesFiltrees);
            System.out.println("demandes sont affecter");
        } else {
            request.setAttribute("errorMessage", "Aucune demande de congé n'a été trouvée.");
        }
        
       request.getRequestDispatcher("/dashboard2.jsp").forward(request, response);
    }

}

