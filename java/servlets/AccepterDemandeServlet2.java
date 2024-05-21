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
import interfaces.EmployeeDAO;
import Impl.EmployeeDAOImpl;
import model.DemandeConge;
import model.Employee;

@WebServlet("/AccepterDemandeServlett")
public class AccepterDemandeServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de la demande à partir des paramètres de la requête
    	
        int idDemande = Integer.parseInt(request.getParameter("id"));

        // Mettre à jour l'état de la demande dans la base de données
        DemandeCongeDAO demandeCongeDAO = new DemandeCongeDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        try {
            // Récupérer la demande de congé
            DemandeConge demande = demandeCongeDAO.getDemandeCongeById(idDemande);
           
            // Récupérer l'employé correspondant à la demande
            /*Employee employee = employeeDAO.getEmployeeById(demande.getEmployeeId());*/
            Employee employee = employeeDAO.getEmployeeByNumeroPPRandNomArabe(demande.getNumeroPPR(),demande.getNomArabe());
            // Vérifier si l'employé a suffisamment de jours de congé
            int joursCongesDisponibles = employee.getCongeJours();
            int dureeDemandeConge = demande.getDuree(); // Assurez-vous que la durée est définie dans votre modèle DemandeConge

            if (joursCongesDisponibles >= dureeDemandeConge) {
                // Mettre à jour l'état de la demande
                demandeCongeDAO.updateEtatDemande(idDemande, "accepté par RH");

                // Soustraire la durée de la demande de congé du solde de congés de l'employé
                employee.setCongeJours(joursCongesDisponibles - dureeDemandeConge);
                /*employee.setJoursCongesDisponibles(joursCongesDisponibles - dureeDemandeConge);*/
                employeeDAO.updateEmployee(employee);
                

                // Redirection vers une page de confirmation
                response.sendRedirect("confirmation.jsp");
            } else {
                // Redirection vers une page d'erreur indiquant l'épuisement des jours de congé
                response.sendRedirect("erreurConge.jsp");
            }
        } catch (SQLException e) {
            // Gérer les erreurs
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
