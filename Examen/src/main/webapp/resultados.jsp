<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultados de examen</title>
</head>
<body>
	<h1>Resumen de examen</h1>
	<%
		HttpSession mySession = request.getSession(false);

		double notaExamen = (double) mySession.getAttribute("notaExamen");
	%>

	<h1>Has sacado un 
		<%
			out.print(notaExamen);
		%>
	</h1>

</body>
</html>