package servlets;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DemandeConge;
import Impl.DemandeCongeDAOImpl;
@WebServlet("/RefuserDemandeServlet")
public class RefuserDemandeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de la demande à partir des paramètres de la requête
        int idDemande = Integer.parseInt(request.getParameter("id"));

        // Mettre à jour l'état de la demande dans la base de données
        try {
            // Votre code pour mettre à jour l'état de la demande dans la base de données
            // Par exemple :
            DemandeCongeDAOImpl demandeCongeDAO = new DemandeCongeDAOImpl();
            demandeCongeDAO.updateEtatDemande(idDemande, "refusé par  chef de dision");

            // Rediriger vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("confirmd.jsp");
        } catch (SQLException e) {
            // Gérer les erreurs de base de données
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
    private void sendEmailToChefDivision() {
        // Paramètres de configuration pour l'email
        String host = "smtp.gmail.com"; // Remplacez par votre serveur SMTP
        final String user = "se552733@gmail.com"; // Remplacez par votre email
        final String password = "pgpo wnei inht fsnd"
        		+ ""; // Remplacez par votre mot de passe email

        // Adresse email du chef de division
        String to = "se552733@gmail.com"; // Remplacez par l'email du chef de division

        // Configuration des propriétés de l'email
     // Set up the mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);

        // Créer une session de messagerie avec authentification
        Session session = Session.getDefaultInstance(properties, // Notez l'utilisation de `properties` avec un `p` minuscule
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });


        try {
            // Composer le message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Nouvelle demande de congé acceptée");
            message.setText("Une demande de congé avec  a été Refusé par le chef de division.");

            // Envoyer le message
            Transport.send(message);

            System.out.println("Message envoyé avec succès");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
