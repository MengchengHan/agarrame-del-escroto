import jakarta.servlet.ServletException;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		doPost(request, response); //xd
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private Connection connection = null;
	ResultSet rSet = null;
	ArrayList<Integer> idPreguntas = getThreeRandom(); // Los ID de las tres preguntas
	HashMap<Integer, String> preguntas = null; // Las tres preguntas (id -> pregunta)
	HashMap<Integer, HashMap<String, Boolean>> opciones = null; // Las opciones (id -> (opciones -> correcta))
	ArrayList<Respuesta> respuestas = null;
	String sqlPreguntas = "SELECT id, pregunta FROM preguntas WHERE id in " + listToString(idPreguntas);
	String sqlRespuestas = "SELECT id, enunciado, correcta FROM opciones WHERE idPregunta = ";
	String errMsg = "Elige al menos una opción para poder continuar.";
	ArrayList<Integer> mal = new ArrayList<>();
	ArrayList<Integer> bien = new ArrayList<>();
	double notaFinal, notaP1, notaP2, notaP3;
	final double notaMax = 10;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession mySession = request.getSession();
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
		 * ArrayList respuestasCorrectas 
		 * HashMap respuestasCorrectas => clave -> valor, 
		 * Para el envío de respuestas marccadas se hará a través de una sesión para luego 
		 * recuperarlas en el jsp.
		 * 
		 */

		try {
			connection = Conexion.Conectar();
			preguntas = getQuestions(sqlPreguntas);
			redirect(request, response, mySession);

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

	@SuppressWarnings("unchecked")
	private void redirect(HttpServletRequest request, HttpServletResponse response, HttpSession mySession)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		/*
		 * Comprobar si el resultado marcado es correcto o no, para sumar o restar de la puntuación. 
		 * 
		 */
		
		ArrayList<Respuesta> respuestas;
		if (request.getParameter("hidden") == null) {
			respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(0));
			dasAuto(request, 1, respuestas);
			linea("/pregunta1.jsp", request, response);
		} else if (request.getParameter("hidden") != null) {
			if (Integer.parseInt(request.getParameter("hidden")) == 1) { // Si el hidden es 1
				if (request.getParameter("siguiente") != null && request.getParameter("siguiente").equals("Siguiente")) { // Tira para pregunta 2
					String [] respondido1 = (String []) request.getParameterValues("opciones" + request.getParameter("hidden"));
					notaP1 = simplyClever((ArrayList<Respuesta>) mySession.getAttribute("respuestas1"), respondido1);
					mySession.setAttribute("respondido1", respondido1);
					respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(1));
					if(respondido1 == null) {				
						respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(0));
						request.setAttribute("errMsg", errMsg);
						dasAuto(request, 1, respuestas);
						linea("/pregunta1.jsp", request, response);						
					} 
					dasAuto(request, 2, respuestas);
					linea("/pregunta2.jsp", request, response);
				}
			} else if (Integer.parseInt(request.getParameter("hidden")) == 2) { // Si el hidden es 2
				if (request.getParameter("anterior") != null && request.getParameter("anterior").equals("Anterior")) { // Tira para pregunta 1
					respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(0));					
					dasAuto(request, 1, respuestas);
					linea("/pregunta1.jsp", request, response);
				} else if (request.getParameter("siguiente") != null && request.getParameter("siguiente").equals("Siguiente")) { // Tira para pregunta 3
					String [] respondido2 = (String []) request.getParameterValues("opciones" + request.getParameter("hidden"));
					notaP2 = simplyClever((ArrayList<Respuesta>) mySession.getAttribute("respuestas2"), respondido2);
					mySession.setAttribute("respondido2", respondido2);
					respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(2));		
					if(respondido2 == null) {				
						respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(1));
						request.setAttribute("errMsg", errMsg);
						dasAuto(request, 2, respuestas);
						linea("/pregunta2.jsp", request, response);						
					} 
					dasAuto(request, 3, respuestas);
					linea("/pregunta3.jsp", request, response);
				}
			} else if (Integer.parseInt(request.getParameter("hidden")) == 3) { // Si el hidden es 3
				if (request.getParameter("anterior") != null && request.getParameter("anterior").equals("Anterior")) { // Tira para pregunta 2
					respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(1));					
					dasAuto(request, 2, respuestas);
					linea("/pregunta2.jsp", request, response);
				} else if (request.getParameter("terminar") != null && request.getParameter("terminar").equals("Terminar")) { // Termina
					String [] respondido3 = (String []) request.getParameterValues("opciones" + request.getParameter("hidden"));
					notaP3 = simplyClever((ArrayList<Respuesta>) mySession.getAttribute("respuestas3"), respondido3);
					if(respondido3 == null) {				
						respuestas = getRespuestas(sqlRespuestas, idPreguntas.get(2));
						request.setAttribute("errMsg", errMsg);
						dasAuto(request, 3, respuestas);
						linea("/pregunta3.jsp", request, response);						
					} 
					notaFinal = notaP1 + notaP2 + notaP3;
					mySession.setAttribute("notaExamen", notaFinal);
					linea("/resultados.jsp", request, response);
				}
				mySession.setAttribute("respondido3", request.getParameterValues("opciones" + request.getParameter("hidden")));
			}
		}
	}
	
	private void dasAuto(HttpServletRequest request, int nPregunta, ArrayList<Respuesta> respuestas) {
		// Envío de la pregunta
		request.setAttribute("pregunta" + nPregunta, preguntas.get(idPreguntas.get(nPregunta-1)));
		// Envío de las respuestas asociadas con la pregunta
		request.setAttribute("respuestas" + nPregunta, respuestas);
		return;
	}
	
	/*
	 * Pongamos que la nota de la pregunta es notaFinal/número de preguntas
	 * Aplicándolo a nuestro ejercicio: 
	 * Sea notaFinal = 10
	 * Sea número de preguntas = 3
	 * 
	 * double notaPregunta; 
	 * 
	 * Resta la probabilidad de acertar una
	 * 
	 * Si una pregunta tiene 3 opciones válidas, la probablidad de acertar una, es de 1/3. 
	 * Eso quiere decir que resta notaPregunta/3	
	 * 
	 * Si tiene 2 opciones válidas, la probabilidad es de 1/2 
	 * Eso quiere decir que resta notaPregunta/2
	 *
	 * El número de opciones válidas consultaremos el mapa de k -> v y obtendremos el valor 
	 */
	
	private ArrayList<Integer> arrayToArrayList(String [] strArr) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(String str : strArr) {
			list.add(Integer.parseInt(str));
		}
		
		return list;
	}
	
	private double simplyClever(ArrayList<Respuesta> respuestas, String [] checked) {
		if(respuestas != null) {
			
			double puntuacion = notaMax/idPreguntas.size();
			int nOpcionesValidas = 0;
			// double p = notaPregunta/nOpcionesValidas;
			
			ArrayList<Integer> checkedValues = arrayToArrayList(checked);
				
			ArrayList<Integer> bien = new ArrayList<Integer>();
				
				HashMap<Integer, Boolean> x = new HashMap<Integer, Boolean>();
				for(Respuesta resp : respuestas) {
					x.put(resp.getId(), resp.isValida());
					
					if(resp.isValida()) {
						nOpcionesValidas++;
					}
				}
				
			if(bien.size() != 0) {
				for(int i : checkedValues) { // i es el valor del elemento 
					if(x.get(i)) {
						bien.add(i);
					}
				}
	
				Iterator<Entry<Integer, Boolean>> it = x.entrySet().iterator();;
				
				while (it.hasNext()) {
					 Map.Entry<Integer, Boolean> new_Map = (Map.Entry<Integer, Boolean>) it.next();
					 if(new_Map.getValue()) {
						 checkedValues.remove(Integer.valueOf(new_Map.getKey())); 					 
					 }
				}
				
				//puntuacion += bien.size()*notaOpcion;
				if (checkedValues.size() != 0) {
					puntuacion -= (puntuacion/nOpcionesValidas);
				}
				
			} else {
					puntuacion -= (puntuacion/nOpcionesValidas);					
				}
			return puntuacion;
		}
		return 0;
	}
}















