<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" import="paquete.*, java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Título</title>
</head>
<body>
	<p>Esto es bienvenido.jsp</p>
	
	<%
		HashMap<String, String> opciones = Datos.Colores;
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("galleta")) {
					cookieValue = cookie.getValue();
				}
			}
		}
	%>

	<form action="Recepcion">
		<%
		out.print(Scriptlets.generaBotonesRadio("opciones", opciones, cookieValue));
		%>
		<input type="submit" value="Enviar">
	</form>

</body>
</html>