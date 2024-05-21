<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="model.DemandeConge" %>
<html>
<head>
    <title>Mes demandes de congé</title>
    <style>
        /* Ajoutez vos styles CSS ici */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Mes demandes de congé</h2>
    <table>
        <thead>
            <tr> <th>ID</th>
                <th>Date de début</th>
               
                <th>Date de fin</th>
                <th>État</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<DemandeConge> demandes = (List<DemandeConge>)request.getAttribute("demandes");
            if(demandes != null) {
                for(DemandeConge demande : demandes) {
            %>
                <tr>
                    <td><%= demande.getId() %></td>
                    <td><%= demande.getDateDebut() %></td>
                    <td><%= demande.getDateFin() %></td>
                    <td><%= demande.getEtat() %></td>
                </tr>
            <% 
                }}
            %>
        </tbody>
    </table>
</html>

