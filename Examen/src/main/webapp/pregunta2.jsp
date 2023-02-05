<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Generador.Genera"
	import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="formStyles.css">
</head>
<%
	String pregunta = (String) request.getAttribute("pregunta");	
	ArrayList<String> opciones = (ArrayList<String>) request.getAttribute("opciones");
%>
<body>
	<main>
		<h1>Pregunta 2</h1>
		<p><%out.print(pregunta); %></p>
		<div id="formulario">
			<form action="Core" method="post">
				<input name="opcion1" type="checkbox" value="<% out.print(opciones.get(0));%>">
				<label for="opcion1"><% out.print(opciones.get(0));%></label>
				<br>
				<input name="opcion2" type="checkbox" value="<% out.print(opciones.get(1));%>">
				<label for="opcion2"><% out.print(opciones.get(1));%></label>
				<br>
				<input name="opcion3" type="checkbox" value="<% out.print(opciones.get(2));%>">
				<label for="opcion3"><% out.print(opciones.get(2));%></label>
				<br>
				<input name="opcion4" type="checkbox" value="<% out.print(opciones.get(3));%>">
				<label for="opcion4"><% out.print(opciones.get(3));%></label>
				<br>
				<input type="hidden" name="hidden" value="2">	
				<input type="submit" name="anterior" value="Anterior">
				<input type="submit" name="siguiente" value="Siguiente">
			</form>
		</div>
	</main>
</body>
</html>