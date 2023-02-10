<%@page import="Generador.Genera, DataTypes.Respuesta, java.util.ArrayList"%>
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
			if(notaExamen < 0){
				out.print(0);
				out.print("<br>");
		%>
	</h1>
	<% out.print("<p>" + "Nota real: " + Math.round(notaExamen * 100.0) / 100.0 + "</p>"); %>
	<h1>
		<%
			} else {
				out.print(Math.round(notaExamen*100.0) / 100.0);				
				out.print("<br>");					
			}
		%>
	</h1>
	
	<main id='container'>
	<% 
		out.print(Genera.cajaRespuestas((String) mySession.getAttribute("pregunta1"),(ArrayList<Respuesta>) mySession.getAttribute("respuestas1"), (String []) mySession.getAttribute("respondido1")));
		out.print(Genera.cajaRespuestas((String) mySession.getAttribute("pregunta2"),(ArrayList<Respuesta>) mySession.getAttribute("respuestas2"), (String []) mySession.getAttribute("respondido2")));			
		out.print(Genera.cajaRespuestas((String) mySession.getAttribute("pregunta3"),(ArrayList<Respuesta>) mySession.getAttribute("respuestas3"), (String []) mySession.getAttribute("respondido3")));
	%>
	</main>
</body>
</html>