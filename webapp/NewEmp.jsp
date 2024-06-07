<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un nouvel employé</title>
    <style>
        body {
                    background-image: url('images/pro.jpg'); /* Chemin de votre image */
        
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f5f5f5;
            color: #333;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 90%;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"], input[type="number"], input[type="date"], input[type="email"] {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        .btn-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            color: white;
        }

        .btn-primary {
            background-color: #3498db;
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }

        .btn-secondary {
            background-color: #e74c3c;
        }

        .btn-secondary:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Ajouter un nouvel employé</h2>
        <form action="AjouterEmployeServlet" method="post">
            <label for="numeroPPR">Numéro PPR</label>
            <input type="number" id="numeroPPR" name="numeroPPR" required>

            <label for="nomArabe">Nom Arabe</label>
            <input type="text" id="nomArabe" name="nomArabe" required>

            <label for="prenomArabe">Prénom Arabe</label>
            <input type="text" id="prenomArabe" name="prenomArabe" required>

            <label for="imputationBudgetaire">Imputation Budgétaire</label>
            <input type="text" id="imputationBudgetaire" name="imputationBudgetaire" required>

            <label for="grade">Grade</label>
            <input type="text" id="grade" name="grade" required>

            <label for="congeJours">Congé (jours)</label>
            <input type="number" id="congeJours" name="congeJours" required>

            <div class="btn-container">
                <button type="submit" class="btn btn-primary">Ajouter</button>
                <button type="reset" class="btn btn-secondary">Réinitialiser</button>
            </div>
        </form>
    </div>
</body>
</html>

