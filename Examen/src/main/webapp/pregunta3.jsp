<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Generador.Genera"
	import="java.util.HashMap, 
	java.util.Map.Entry, 
	java.util.Map,  
	java.util.ArrayList, 
	java.util.Iterator,
	DataTypes.Respuesta" 
	session="true"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="formStyles.css">
</head>
<%
	String pregunta = (String) request.getAttribute("pregunta3");
	ArrayList<Respuesta> respuestas = (ArrayList<Respuesta>) request.getAttribute("respuestas3"); 
	
	Map<Integer, String> mapaRespuestas = new HashMap<Integer, String>();
	
	Iterator<Respuesta> it = respuestas.iterator();
	while(it.hasNext()) {
		Respuesta r = it.next();
		mapaRespuestas.put(r.getId(), r.getEnunciado());
	}
	
	HttpSession mySession = request.getSession(false);
	int [] checkedValues = null;
	String [] strings = (String []) mySession.getAttribute("respondido3"); 
	if(mySession != null) {
		if (strings != null){		
			checkedValues = new int [strings.length];
			for(int i = 0; i < checkedValues.length; i++){
				checkedValues[i] = Integer.parseInt(strings[i]);
			}
		}
	}
	
	mySession.setAttribute("respuestas3", respuestas);
%>
<body>
	<main id="container">
		<h1>Pregunta 3</h1>
		<p><%out.print(pregunta); %></p>
		<form action="Core" method="post">
			<% out.print(Genera.checkboxes("opciones3", mapaRespuestas, checkedValues)); %>
			<input type="hidden" name="hidden" value="3">
			<div id='submits'>
				<input type="submit" name="anterior" value="Anterior">	
				<input type="submit" name="terminar" value="Terminar">
			</div>
		</form>
	</main>
</body>
</html>