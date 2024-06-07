package servlets;

import Impl.EmployeeDAOImpl;
import interfaces.EmployeeDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AjouterEmployeServlet")
public class AjouterEmployeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    public AjouterEmployeServlet() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double numeroPPR = Double.parseDouble(request.getParameter("numeroPPR"));
            String nomArabe = request.getParameter("nomArabe");
            String prenomArabe = request.getParameter("prenomArabe");
            String imputationBudgetaire = request.getParameter("imputationBudgetaire");
            String grade = request.getParameter("grade");
            int congeJours = Integer.parseInt(request.getParameter("congeJours"));

            Employee nouvelEmploye = new Employee(numeroPPR, nomArabe, prenomArabe, imputationBudgetaire, grade, congeJours);

            employeeDAO.ajouterEmploye(nouvelEmploye);

            response.sendRedirect("confirmation.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de l'ajout de l'employé", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
