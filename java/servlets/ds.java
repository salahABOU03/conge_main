package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class ds extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int BUFFER_SIZE = 4096;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chemin du fichier sur le serveur
     //   String relativePath = "tt.docx";
        String filePath =  "C:\\Users\\hp125\\eclipse-workspace\\stage\\tt.docx" ; 
        //String filePath = getServletContext().getRealPath("") + File.separator + relativePath;
            
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
        
        // Obtient le type de MIME du fichier
        
        String mimeType = getServletContext().getMimeType(filePath);
        if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }
        
        // Modifie la réponse
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        
        // Définit les en-têtes pour forcer le téléchargement
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // Obtient le flux de sortie de la réponse
        OutputStream outStream = response.getOutputStream();
        
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // Écrit les données du fichier dans le flux de sortie
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
