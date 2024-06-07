<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.DemandeConge" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>  Dashboard RH Chef de division </title>
     <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-image: url('images/pro.jpg');
            background-size: cover;
            background-position: center;
            color: #333;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
            max-width: 1200px;
            width: 90%;
            margin: 20px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        button {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .accept-button {
            background-color: #e74c3c;
            color: white;
        }

        .accept-button:hover {
            background-color: #c0392b;
        }

        .reject-button {
            background-color: #e74c3c;
            color: white;
        }

        .reject-button:hover {
            background-color: #c0392b;
        }
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
                        <button class="accept-button" onclick="confirmerDecision(<%= demande.getId() %>, 'accepter')">Accepter</button>
                        <button class="reject-button" onclick="confirmerDecision(<%= demande.getId() %>, 'refuser')">Refuser</button>
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
</body>
</html>
