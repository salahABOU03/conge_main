<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
                    background-image: url('images/pro.jpg'); /* Chemin de votre image */
        
            font-family: Arial, sans-serif;
         /*   background: linear-gradient(135deg, #8ec5fc, #e0c3fc);*/
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: linear-gradient(135deg, #f3f3f3, #ededed);
            padding: 20px;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            margin: 50px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #555;
        }

        p {
            margin-bottom: 10px;
            color: #333;
        }

        form {
            max-width: 300px;
            margin: 0 auto;
        }

        input[type="number"],
        input[type="text"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background: linear-gradient(135deg, #4fc3f7, #29b6f6);
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background: linear-gradient(135deg, #29b6f6, #03a9f4);
        }

        label {
            margin-bottom: 5px;
            color: #444;
            display: block;
        }

        #dateFinConge {
            background: #f9f9f9;
            cursor: not-allowed;
        }

        a {
            color: #4fc3f7;
            text-decoration: none;
            display: block;
            text-align: center;
            margin-bottom: 20px;
        }

        a:hover {
            color: #29b6f6;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome</h2>
        <p>Numéro PPR : <%= session.getAttribute("numeroPPR") %></p>
        <p>Nom Arabe : <%= (String) session.getAttribute("nomArabe") %></p>
        <p>Nombre de congés : <%= session.getAttribute("congeJours") %></p>
        <a href="/stage/AfficherDemandesServlet"> Afficher mes demandes </a>
        <a href="DownloadServlet" class="download-button">Télécharger le ficher Word</a>
    </div>

    <div class="container">
        <h3>Formulaire de demande de congé :</h3>
        <form action="EnregistrerDemandeCongeServlet" method="post">
            <label for="dateConge">Date de début de congé :</label>
            <input type="date" id="dateConge" name="dateConge" required><br><br>

            <label for="dureeConge">Durée du congé (en jours) :</label>
            <input type="number" id="dureeConge" name="dureeConge" required><br><br>

            <label for="dateFinConge">Date de fin de congé :</label>
            <input type="date" id="dateFinConge" name="dateFinConge" readonly><br><br>

            <input type="submit" value="Soumettre">
        </form>
    </div>

    <script>
        // Fonction pour vérifier si une date est un week-end (samedi ou dimanche) au Maroc
        function isWeekend(date) {
            var dayOfWeek = date.getDay();
            return (dayOfWeek === 6 || dayOfWeek === 0);
        }

        // Fonction pour vérifier si une date est un jour férié au Maroc
        function isHoliday(date) {
            // Liste des jours fériés au Maroc (civils et religieux)
            var holidays = ["01-01", "11-01", "01-05", "30-07", "14-08", "20-08", "21-08", "06-11", "18-11"];

            var dateFormat = date.getDate() + "-" + (date.getMonth() + 1);
            return holidays.includes(dateFormat);
        }

        // Calcul automatique de la date de fin de congé en tenant compte des week-ends et des jours fériés
        document.getElementById("dateConge").addEventListener("change", function() {
            var dateDebut = new Date(document.getElementById("dateConge").value);
            var duree = parseInt(document.getElementById("dureeConge").value);
            if (!isNaN(dateDebut.getTime()) && !isNaN(duree)) {
                var endDate = new Date(dateDebut.getTime() + duree * 24 * 60 * 60 * 1000);
                while (isWeekend(endDate) || isHoliday(endDate)) {
                    endDate.setDate(endDate.getDate() + 1);
                }
                document.getElementById("dateFinConge").valueAsDate = endDate;
            }
        });

        document.getElementById("dureeConge").addEventListener("change", function() {
            var dateDebut = new Date(document.getElementById("dateConge").value);
            var duree = parseInt(document.getElementById("dureeConge").value);
            if (!isNaN(dateDebut.getTime()) && !isNaN(duree)) {
                var endDate = new Date(dateDebut.getTime() + duree * 24 * 60 * 60 * 1000);
                while (isWeekend(endDate) || isHoliday(endDate)) {
                    endDate.setDate(endDate.getDate() + 1);
                }
                document.getElementById("dateFinConge").valueAsDate = endDate;
            }
        });
    </script>
</body>
</html>
