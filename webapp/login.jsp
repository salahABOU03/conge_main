<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            background-image: url('images/pro.jpg'); /* Chemin de votre image */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
          label {
            display: block;
            margin-bottom: 8px;
            color: black ;
            font-weight: bold; /* Texte en gras */
        }
        .container {
         background-color: #f3f3f3;
         /*   background-image: url('images/pro.jpg');  Chemin de votre image */
         	   background-size: cover;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
            width: 300px; /* Ajustez la largeur si nécessaire */
            
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        form {
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #666;
        }

        input[type="number"],
        input[type="text"],
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: none;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="login" method="post" accept-charset="UTF-8">
            <label for="numeroPPR">Numéro PPR :</label>
            <input type="number" name="N°_PPR" id="numeroPPR" step="any" required>
            <label for="nomArabe">Nom Arabe  :</label>
            <input type="text" name="NOM_ARABE" id="nomArabe" required>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
