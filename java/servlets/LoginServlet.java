package servlets;

import interfaces.EmployeeDAO;
import Impl.EmployeeDAOImpl;
import model.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
      
    	System.out.println("tttttttt ");
        request.setCharacterEncoding("UTF-8");

        double numeroPPR = Double.parseDouble(request.getParameter("N°_PPR"));
        String nomArabe = request.getParameter("NOM_ARABE");
 
        System.out.println("Numéro PPR reçu: " +numeroPPR);
        System.out.println("Nom Arabe reçu: " + nomArabe);

        // Créer une instance du DAO pour l'employé
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        // Récupérer l'employé correspondant aux paramètres de connexion
     
        Employee employee = employeeDAO.getEmployeeByNumeroPPRandNomArabe(numeroPPR, nomArabe);
        // Vérifier si l'employé existe
        
        if (employee != null) {
            // L'employé existe, rediriger vers une page de succès ou d'accueil
        	 
        	HttpSession session = request.getSession();
        	 session.setAttribute("prenomArabe",employee.getPrenomArabe());
        	 session.setAttribute("congeJours",employee.getCongeJours());
             session.setAttribute("numeroPPR", numeroPPR);
             session.setAttribute("nomArabe", nomArabe);
             response.setCharacterEncoding("UTF-8");
             response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        } else {
        
        	// L'employé n'existe pas, rediriger vers une page d'erreur de connexion
            response.sendRedirect("error.jsp");
        }
    }
}
