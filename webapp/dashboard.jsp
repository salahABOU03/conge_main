	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.DemandeConge" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        /* Styles CSS */
    </style>
</head>
<body>
    <div class="container">
        <h2>Liste des demandes de congé</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Numéro PPR</th>
                    <th>Nom Arabe</th>
                    <th>Prénom Arabe</th>
                    <th>Date de début</th>
                    <th>Durée (jours)</th>
                    <th>Date de fin</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<DemandeConge> demandes = (List<DemandeConge>) request.getAttribute("demandes");
                if (demandes != null && !demandes.isEmpty()) {
                    for (DemandeConge demande : demandes) {
                %>
                <tr>
                    <td><%= demande.getId() %></td>
                    <td><%= demande.getNumeroPPR() %></td>
                    <td><%= demande.getNomArabe() %></td>
                    <td><%= demande.getPrenomArabe() %></td>
                    <td><%= demande.getDateDebut() %></td>
                    <td><%= demande.getDuree() %></td>
                    <td><%= demande.getDateFin() %></td>
                    <td>
                        <button onclick="confirmerDecision(<%= demande.getId() %>, 'accepter')">Accepter</button>
                        <button onclick="confirmerDecision(<%= demande.getId() %>, 'refuser')">Refuser</button>
                    </td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="8">Aucune demande de congé n'a été trouvée.</td>
                </tr>
                <% 
                }
                %>
            </tbody>
        </table>
    </div>

    <!-- Script JavaScript pour la boîte de dialogue -->
    <script>
        function confirmerDecision(idDemande, action) {
            var confirmation = confirm("Voulez-vous " + (action === 'accepter' ? "accepter" : "refuser") + " cette demande ?");
            if (confirmation) {
                window.location.href = action === 'accepter' ? "AccepterDemandeServlet?id=" + idDemande : "RefuserDemandeServlet?id=" + idDemande;
            }
        }
    </script>
</html>
