package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin1login")
public class Admin1LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Vérification de l'email et du mot de passe dans la base de données ou autre logique d'authentification
        if (email.equals("admin1@example.com") && password.equals("motdepasse1")) {
            // Authentification réussie
            response.sendRedirect("/stage/dashboardser");
        } else {
            // Authentification échouée
            PrintWriter out = response.getWriter();
            out.println("<h1>Login failed. Please try again.</h1>");
        }
    }
}
