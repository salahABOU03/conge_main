<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="model.DemandeConge" %>
<html>
<head>
    <title>Mes demandes de congé</title>
    <style>
       /* Styles CSS améliorés */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2 {
            color: #2c3e50;
            margin-top: 20px;
            font-size: 24px;
        }

        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #34495e;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td {
            color: #555;
        }

        td:first-child, th:first-child {
            text-align: center;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        td {
            color: #666;
        }
        
        /* Boutons et liens */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 0;
            font-size: 14px;
            font-weight: bold;
            color: #fff;
            background-color: #3498db;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn:hover {
            background-color: #2980b9;
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

