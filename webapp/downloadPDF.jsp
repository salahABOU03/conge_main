<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TÚlÚcharger le PDF</title>
</head>
<body>
    <h1>TÚlÚcharger le Document PDF</h1>
    <a href="${pageContext.request.contextPath}/generatePDF" download="generatedDocument.pdf">
        <button type="button">TÚlÚcharger PDF</button>
    </a>
</body>
</html>
