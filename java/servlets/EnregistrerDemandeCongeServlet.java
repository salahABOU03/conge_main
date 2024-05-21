package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DemandeConge;
import interfaces.DemandeCongeDAO;
import Impl.DemandeCongeDAOImpl;

@WebServlet("/EnregistrerDemandeCongeServlet")
public class EnregistrerDemandeCongeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateCongeStr = request.getParameter("dateConge");
        String dureeCongeStr = request.getParameter("dureeConge");
        String dateFinCongeStr = request.getParameter("dateFinConge");

        if (dateCongeStr.isEmpty() || dureeCongeStr.isEmpty() || dateFinCongeStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        Date dateConge = null;
        Date dateFinConge = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateConge = sdf.parse(dateCongeStr);
            dateFinConge = sdf.parse(dateFinCongeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return;
        }

        int dureeConge = Integer.parseInt(dureeCongeStr);

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        Double numeroPPR = (Double) session.getAttribute("numeroPPR");
        String nomArabe = (String) session.getAttribute("nomArabe");
        String prenomArabe = (String) session.getAttribute("prenomArabe");

        if (numeroPPR == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Ajout de l'état par défaut
        String etatParDefaut = "au cours de traitement";

        DemandeConge demandeConge = new DemandeConge();
        demandeConge.setNumeroPPR(numeroPPR);
        demandeConge.setDateDebut(dateConge);
        demandeConge.setDuree(dureeConge);
        demandeConge.setDateFin(dateFinConge);
        demandeConge.setNomArabe(nomArabe);
        demandeConge.setPrenomArabe(prenomArabe);
        demandeConge.setEtat(etatParDefaut); // Ajout de l'état par défaut

        try {
            DemandeCongeDAO demandeCongeDAO = new DemandeCongeDAOImpl();
            demandeCongeDAO.ajouterDemandeConge(demandeConge);
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
