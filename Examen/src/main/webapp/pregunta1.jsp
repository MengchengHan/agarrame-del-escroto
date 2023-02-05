<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Generador.Genera"
	import="java.util.HashMap, java.util.ArrayList" session="true"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="formStyles.css">
</head>
<!-- 
	Podría llevarme conmigo el HashMap a todos los jsp e ir sacando en cada uno la pregunta correspondiente
	O podría enviar a cada jsp su pregunta correspondiente, evitando así tener que lidiar con los iterators 
 -->
<%
	HttpSession mySession = request.getSession();
	ArrayList<Integer> idPreguntas;
	HashMap<Integer, String> preguntas = (HashMap<Integer, String>) mySession.getAttribute("preguntas"); // Las tres preguntas (id -> pregunta)
	HashMap<Integer, HashMap<String, Boolean>> opciones = (HashMap<Integer, HashMap<String, Boolean>>) mySession.getAttribute("opciones"); // Las opciones (id -> (opciones -> correcta))
%>
<body>
	<main>
		<h1>Pregunta 1</h1>
		<p><%out.print(preguntas); %></p>
		<div id="formulario">
			<form action="Core" method="post">
				<input name="opcion" type="checkbox" value="<% out.print(opciones.get(0));%>">
				<label for="opcion"><% out.print(opciones.get(0));%></label>
				<br>
				<input name="opcion" type="checkbox" value="<% out.print(opciones.get(1));%>">
				<label for="opcion"><% out.print(opciones.get(1));%></label>
				<br>
				<input name="opcion" type="checkbox" value="<% out.print(opciones.get(2));%>">
				<label for="opcion"><% out.print(opciones.get(2));%></label>
				<br>
				<input name="opcion" type="checkbox" value="<% out.print(opciones.get(3));%>">
				<label for="opcion"><% out.print(opciones.get(3));%></label>
				<br>
				<input type="hidden" name="hidden" value="1">	
				<input type="submit" value="Siguiente">
			</form>
		</div>	
	</main>
</body>
</html>