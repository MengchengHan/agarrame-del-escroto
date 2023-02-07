
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class Core
 */
public class Consulta extends HttpServlet {
	// El m´etodo doGet() se ejecuta una vez por cada petici´on HTTP GET.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Establecemos el tipo MIME del mensaje de respuesta
				response.setContentType("text/html");
				// Creamos un objeto para poder escribir la respuesta
				PrintWriter out = response.getWriter();
				Connection conn = null;
				Statement stmt = null;
				try {
					// Paso 1: Cargar el driver JDBC.
					Class.forName("com.mysql.cj.jdbc.Driver");
					// Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
					String userName = "root";
					String password = "";
					// URL de la base de datos(equipo, puerto, base de datos)
					String url = "jdbc:mysql://localhost/tiendalibros";
					conn = DriverManager.getConnection(url, userName, password);
					// Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
					stmt = conn.createStatement();
					// Paso 4: Ejecutar las sentencias SQL a trav´es de los objetos Statement
					String sqlStr = "select * from libros where autor = " + "'" + request.getParameter("autor") + "'";
					// Generar una p´agina HTML como resultado de la consulta
					out.println("<html><head><title>Resultado de la consulta</title></head><body>");
					out.println("<h3>Gracias por tu consulta.</h3>");
					out.println("<p>Tu consulta es: " + sqlStr + "</p>");
					ResultSet rset = stmt.executeQuery(sqlStr);
					// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet
					int count = 0;
					while (rset.next()) {
						out.println("<p>" + rset.getString("autor") + ", " + rset.getString("titulo") + ", "
								+ rset.getDouble("precio") + "</p>");
						count++;
					}
					out.println("<p>==== " + count + " registros encontrados =====</p>");
					out.println("</body></html>");
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					out.close(); // Cerramos el flujo de escritura
					try {
						// Cerramos el resto de recursos
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
		
	}

}
