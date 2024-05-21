package servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/generatePDF")
public class PDFGenerationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");

        // Suggérer que le fichier soit téléchargé plutôt qu'affiché
        response.setHeader("Content-Disposition", "attachment; filename=\"generatedDocument.pdf\"");

        // Assurez-vous que le navigateur ne met pas en cache le document
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        // Création d'un document
        Document document = new Document();
        try {
            // Création d'un écrivain PDF
       // 	writer.setPdfVersion(PdfWriter.VERSION_1_7);
       //writer.setViewerPreferences(PdfWriter.PageModeFullScreen);
        	document.addTitle("Generated Document");
        	document.addAuthor("Your Name");
        	document.addSubject("Generated PDF using iText");
        	document.addKeywords("Java, PDF, iText");
        	document.addCreator("Your Application");

        	response.setCharacterEncoding("UTF-8");
        	response.setHeader("Content-Disposition", "attachment; filename=\"generatedDocument.pdf\"");
        	response.setContentType("application/pdf; charset=UTF-8");
            PdfWriter.getInstance(document, response.getOutputStream());

            // Ouverture du document
            document.open();

            // Ajout du contenu au document PDF avec une police par défaut pour l'arabe
            Paragraph paragraph = new Paragraph("المملكة الـمغربـــيـــة", FontFactory.getFont(FontFactory.HELVETICA, 12));
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph);

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du document dans le bloc finally
            if (document != null) {
                document.close();
            }
        }
    }
}
