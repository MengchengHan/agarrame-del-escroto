
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import paquete.Datos;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Servlet implementation class Recepcion
 */
public class Recepcion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recepcion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LinkedHashMap<String, String> Credenciales = Datos.Credenciales;

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

//		System.out.println(Credenciales.containsKey(user));
//		System.out.println(Credenciales.containsValue(pass));
//		System.out.println();
		Cookie[] cookies = request.getCookies();

		if (user != null && Credenciales.containsKey(user)) {
			if (pass != null && pass.equals(Credenciales.get(user))) {
				if (cookies != null) {
				    for (Cookie cookie : cookies) {
				        System.out.println("Cookie name: " + cookie.getName());
				        System.out.println("Cookie value: " + cookie.getValue());
				    }
				} else {
				    System.out.println("No cookies found.");
				}
				response.sendRedirect(request.getContextPath() + "/bienvenido.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

}
