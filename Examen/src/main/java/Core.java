
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import DataTypes.Respuesta;

/**
 * Servlet implementation class Core
 */
public class Core extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Core() {
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
	Connection connection = null;
	ResultSet rSet = null;
	ArrayList<Integer> idPreguntas = getThreeRandom(); // Los ID de las tres preguntas
	HashMap<Integer, String> preguntas = null; // Las tres preguntas (id -> pregunta)
	HashMap<Integer, HashMap<String, Boolean>> opciones = null; // Las opciones (id -> (opciones -> correcta))
	ArrayList<Respuesta> respuestas = null;
	String sqlPreguntas = "SELECT id, pregunta FROM preguntas WHERE id in " + listToString(idPreguntas);
	String sqlOpciones = "SELECT enunciado, correcta FROM opciones WHERE idPregunta = ";
	String sqlRespuestas = "SELECT id, enunciado, correcta FROM opciones WHERE idPregunta = ";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Empezar enviando las preguntas por una sesión Cada pregunta recuperará su
		 * correspondiente Así como sus opciones disponibles
		 * 
		 * Para obtener las preguntas, se hará una consulta a la base de datos Para
		 * obtener las opciones, se hará una consulta a la base de datos con la
		 * condición de que tengan el mismo id que la pregunta
		 * 
		 * A la hora de almacenar estas consultas, las preguntas se guardarán en un mapa
		 * clave -> valor. La clave será el id de la pregunta, el valor será el
		 * enunciado de la pregunta.
		 * 
		 * Las opciones se guardarán en un mapa de clave -> valor. La clave será el id
		 * la pregunta, el valor será un mapa, de clave el enunciado de la opción y de
		 * valor true or false.
		 * 
		 */

		try {
			connection = Conexion.Conectar();
//			Statement stmt = connection.createStatement();
			HttpSession mySession = request.getSession(false);

			// Si la sesión no existe, se crea
//			if (mySession == null) {
//				mySession = request.getSession();
//
//				// Obteniendo y guardando los id's y preguntas
			preguntas = getQuestions(sqlPreguntas);
			
			// Obteniendo y guardando las opciones, así como si son correctas o no
			// opciones = getOptions(sqlOpciones, idPreguntas);

//				mySession.setAttribute("preguntas", preguntas);
//				mySession.setAttribute("opciones", opciones);

//				request.setAttribute("preguntas", preguntas);
//				request.setAttribute("opciones", opciones);

//			if (request.getParameter("hidden") == null) {
//				// Envío de la pregunta
//				request.setAttribute("pregunta1", preguntas.get(idPreguntas.get(0)));
//				// Envío de las respuestas asociadas con la pregunta
//				request.setAttribute("respuestas1", getRespuestas(sqlRespuestas, idPreguntas.get(0)));
//				// Opciones checked
//				request.setAttribute("opciones1", request.getParameterValues("opciones1"));
//				linea("/pregunta1.jsp", request, response);
//			} else {
			redirect(request, response);
//			}
//				} else if (request.getParameter("hidden") != null && Integer.parseInt(request.getParameter("hidden")) == 1 ) {
//					request.setAttribute("pregunta2", preguntas.get(1));
//					linea("/pregunta2.jsp", request, response);
//				} else if (request.getParameter("hidden") != null && Integer.parseInt(request.getParameter("hidden")) == 2 ) {
//					if(request.getParameter("anterior").equals("Anterior")) {
//						request.setAttribute("pregunta1", preguntas.get(0));
//						linea("/pregunta1.jsp", request, response);						
//					} else if (request.getParameter("siguiente").equals("Siguiente")) {
//						request.setAttribute("pregunta3", preguntas.get(2));
//						linea("/pregunta3.jsp", request, response);										
//					}
//				} else if (request.getParameter("hidden") != null && Integer.parseInt(request.getParameter("hidden")) == 3 ) {
//					if(request.getParameter("anterior").equals("Anterior")) {
//						request.setAttribute("pregunta2", preguntas.get(1));
//						linea("/pregunta2.jsp", request, response);						
//					}
//				}
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getRandom(int max) {
		Random random = new Random();
		int rand = (int) random.nextInt(max - 1) + 1;
		return rand;
	}

	private ArrayList<Integer> getThreeRandom() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int num, rows = countRows();
		for (int i = 0; i < 3; i++) {
			num = getRandom(rows);
			if (!list.contains(num)) {
				list.add(num);
			} else {
				i--;
			}
		}
		return list;
	}

	private int countRows() {
		Connection conn = null;
		int nRows = -1;
		try {
			conn = Conexion.Conectar();
			PreparedStatement prep = conn.prepareStatement("SELECT COUNT(*) FROM preguntas");
			ResultSet resultSet = prep.executeQuery();
			resultSet.next();
			nRows = resultSet.getInt(1);
			return nRows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nRows;
	}

	private String listToString(ArrayList<Integer> list) {
		String out = "(";
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			out += it.next();
			if (it.hasNext()) {
				out += ", ";
			}
		}
		out += ")";
		return out;
	}

	private HashMap<Integer, String> getQuestions(String sql) throws SQLException, ClassNotFoundException {
		HashMap<Integer, String> preguntas = new HashMap<Integer, String>();
		// Obteniendo y guardando los id's y preguntas
		Connection connection = Conexion.Conectar();
		Statement stmt = connection.createStatement();
		ResultSet rSet = stmt.executeQuery(sql);
		while (rSet.next()) {
			preguntas.put(rSet.getInt(1), rSet.getString(2));
		}
		return preguntas;
	}

//	private HashMap<Integer, HashMap<String, Boolean>> getOptions(String sql, ArrayList<Integer> idPreguntas)
//			throws SQLException, ClassNotFoundException {
//		// Obteniendo y guardando las opciones, así como si son correctas o no
//		HashMap<Integer, HashMap<String, Boolean>> opciones = new HashMap<Integer, HashMap<String, Boolean>>();
//		Connection connection = Conexion.Conectar();
//		Statement stmt = connection.createStatement();
//		int i = 0;
//		while (idPreguntas.iterator().hasNext() && i < idPreguntas.size()) { // (6,2,10)
//			int id = idPreguntas.get(i);
//			ResultSet rSet = stmt.executeQuery(sql + id);
//			HashMap<String, Boolean> aux = new HashMap<String, Boolean>();
//			while (rSet.next()) {
//				aux.put(rSet.getString(1), rSet.getBoolean(2));
//			}
//			opciones.put(id, aux);
//			i++;
//		}
//		return opciones;
//	}

	private ArrayList<Respuesta> getRespuestas(String sql, int idPregunta) throws SQLException, ClassNotFoundException {
		ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
		Connection connection = Conexion.Conectar();
		Statement stmt = connection.createStatement();
		ResultSet rSet = stmt.executeQuery(sql + idPregunta);
		while (rSet.next()) {
			Respuesta respuesta = new Respuesta(rSet.getInt(1), rSet.getString(2), rSet.getBoolean(3));
			respuestas.add(respuesta);
		}
		return respuestas;

	}

	private void linea(String jsp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		/*
		 * De primeras tenemos que el parámetro hidden es null, por lo que el usuario no
		 * ha entrado a la primera pregunta Una vez que ha entrado y le da a enviar,
		 * tenemos que recoger el valor del hidden, que siendo la primera pregunta será
		 * de 1. Entonces tendremos que comprobar a parte de si el hidden es 1, también
		 * si ha pulsado el botón siguiente.
		 * 
		 * 
		 */

		if (request.getParameter("hidden") == null) {
			// Envío de la pregunta
			request.setAttribute("pregunta1", preguntas.get(idPreguntas.get(0)));
			// Envío de las respuestas asociadas con la pregunta
			request.setAttribute("respuestas1", getRespuestas(sqlRespuestas, idPreguntas.get(0)));
			// Opciones checked
			request.setAttribute("opciones1", request.getParameterValues("opciones1"));
			linea("/pregunta1.jsp", request, response);
		} else if (request.getParameter("hidden") != null) {
			if (Integer.parseInt(request.getParameter("hidden")) == 1) { // Si el hidden es 1
				if (request.getParameter("siguiente") != null && request.getParameter("siguiente").equals("Siguiente")) { // Tira para pregunta 2
					// Envío de la pregunta
					request.setAttribute("pregunta2", preguntas.get(idPreguntas.get(1)));
					// Envío de las respuestas asociadas con la pregunta
					request.setAttribute("respuestas2", getRespuestas(sqlRespuestas, idPreguntas.get(1)));
					// Opciones checked
					request.setAttribute("opciones2", request.getParameterValues("opciones2"));
					linea("/pregunta2.jsp", request, response);
				}
			} else if (Integer.parseInt(request.getParameter("hidden")) == 2) { // Si el hidden es 2
				if (request.getParameter("anterior") != null && request.getParameter("anterior").equals("Anterior")) { // Tira para pregunta 1
					// Envío de la pregunta
					request.setAttribute("pregunta1", preguntas.get(idPreguntas.get(0)));
					// Envío de las respuestas asociadas con la pregunta
					request.setAttribute("respuestas1", getRespuestas(sqlRespuestas, idPreguntas.get(0)));
					// Opciones checked
					request.setAttribute("opciones1", request.getParameterValues("opciones1"));
					linea("/pregunta1.jsp", request, response);
				} else if (request.getParameter("siguiente") != null && request.getParameter("siguiente").equals("Siguiente")) { // Tira para pregunta 3
					// Envío de la pregunta
					request.setAttribute("pregunta3", preguntas.get(idPreguntas.get(2)));
					// Envío de las respuestas asociadas con la pregunta
					request.setAttribute("respuestas3", getRespuestas(sqlRespuestas, idPreguntas.get(2)));
					// Opciones checked
					request.setAttribute("opciones3", request.getParameterValues("opciones3"));
					linea("/pregunta3.jsp", request, response);
				}
			} else if (Integer.parseInt(request.getParameter("hidden")) == 3) { // Si el hidden es 3
				if (request.getParameter("anterior") != null && request.getParameter("anterior").equals("Anterior")) { // Tira para pregunta 2
					// Envío de la pregunta
					request.setAttribute("pregunta2", preguntas.get(idPreguntas.get(1)));
					// Envío de las respuestas asociadas con la pregunta
					request.setAttribute("respuestas2", getRespuestas(sqlRespuestas, idPreguntas.get(1)));
					// Opciones checked
					request.setAttribute("opciones2", request.getParameterValues("opciones2"));
					linea("/pregunta2.jsp", request, response);
				}
			}
		}
	}
}
