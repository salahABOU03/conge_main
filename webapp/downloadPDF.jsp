<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>T�l�charger le PDF</title>
</head>
<body>
    <h1>T�l�charger le Document PDF</h1>
    <a href="${pageContext.request.contextPath}/generatePDF" download="generatedDocument.pdf">
        <button type="button">T�l�charger PDF</button>
    </a>
</body>
</html>
