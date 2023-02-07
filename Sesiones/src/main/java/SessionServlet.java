
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Establece el tipo MIME del mensaje de respuesta
		response.setContentType("text/html");
		// Crea un flujo de salida para escribir la respuesta a la petici´on del cliente
		PrintWriter out = response.getWriter();
		// Recoge la sesi´on actual si existe, en otro caso crea una nueva
		HttpSession session = request.getSession();
		Integer contadorAccesos;
		synchronized (session) {
			contadorAccesos = (Integer) session.getAttribute("contadorAccesos");
			if (contadorAccesos == null) {
				contadorAccesos = 0;
			} else {
				contadorAccesos = new Integer(contadorAccesos + 1);
			}
			session.setAttribute("contadorAccesos", contadorAccesos);
		}
		// Escribe el mensaje de respuesta en una p´agina html
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<meta http-equiv=’Content-Type’ content=’text/html; charset=UTF-8’>");
			out.println("<title>Servlet de prueba de sesi´on</title></head><body>");
			out.println("<h2>Accesos: " + contadorAccesos + " en esta sesi´on.</h2>");
			out.println("<p>(Identificador de sesi´on: " + session.getId() + ")</p>");
			out.println("<p>(Fecha de creaci´on de la sesi´on: " + new Date(session.getCreationTime()) + ")</p>");
			out.println(
					"<p>(Fecha de ´ultimo acceso a la sesi´on " + new Date(session.getLastAccessedTime()) + ")</p>");
			out.println(
					"<p>(M´aximo tiempo inactivo de la sesi´on: " + session.getMaxInactiveInterval() + " seconds)</p>");
			out.println("<p><a href='" + request.getRequestURI() + "'>Refrescar</a>");
			out.println("<p><a href='" + response.encodeURL(request.getRequestURI())
					+ "'> Refrescar con reescritura de URLs </a>");
			out.println("</body></html>");
		} finally {
			out.close(); // Cerrar el flujo de salida
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
