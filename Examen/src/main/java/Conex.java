

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet implementation class Conex
 */
public class Conex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Charset.defaultCharset().name();
		System.setProperty("file.encoding", "UTF-8");
		String preguntas = "¿Quién descubrió América?\r\n"
				+ "¿Qué país es conocido como el país de los ocho mundiales de futbol?\r\n"
				+ "¿Qué país es conocido por el palacio de Versalles?\r\n" + "¿Cuál es la capital de Australia?\r\n"
				+ "¿Quién escribió la Odisea?\r\n" + "¿Quién es el autor de \"Don Quijote de la Mancha\"?\r\n"
				+ "¿Qué país es conocido por la \"Opera de Sydney\"?\r\n"
				+ "¿Cuál es el monumento más famoso de Egipto?\r\n" + "¿Quién es el padre de la medicina?\r\n"
				+ "¿Qué país es conocido por la Torre Eiffel?\r\n" + "¿Quién escribió \"La Ilíada\"?\r\n"
				+ "¿Cuál es el país más grande del mundo en términos de superficie?\r\n"
				+ "¿Cuál es la capital de Rusia?\r\n" + "¿Qué país es conocido por sus canales y molinos de viento?\r\n"
				+ "¿Cuál es la moneda oficial de Japón?\r\n" + "¿Quién es el autor de \"El Principito\"?\r\n"
				+ "¿Cuál es el país más pequeño del mundo en términos de población?\r\n"
				+ "¿Quién es el autor de \"Dracula\"?\r\n" + "¿Cuál es la capital de China?\r\n"
				+ "¿Qué país es conocido por la ciudad de Roma?\r\n" + "¿Quién escribió \"Guerra y Paz\"?\r\n"
				+ "¿Qué país es conocido por la famosa \"Hora del Té\"?\r\n"
				+ "¿Quién es el autor de \"Cien años de soledad\"?\r\n" + "¿Cuál es la capital de Alemania?\r\n"
				+ "¿Qué país es conocido por sus tulipanes y molinos?\r\n"
				+ "¿Quién escribió \"Matar a un ruiseñor\"?\r\n" + "¿Cuál es la moneda oficial de Suiza?\r\n"
				+ "¿Qué país es conocido por sus vinos y quesos?\r\n" + "¿Cuál es la capital de Portugal?\r\n"
				+ "¿Quién escribió \"El Gran Gatsby\"?\r\n" + "¿Qué país es conocido por sus \"fiordos\"?\r\n"
				+ "¿Cuál es la capital de Dinamarca?\r\n" + "¿Quién es el autor de \"1984\"?\r\n"
				+ "¿Qué país es conocido por sus montañas y lagos?\r\n" + "¿Cuál es la capital de Noruega?\r\n"
				+ "¿Cuál es el lugar más frío de la tierra?\r\n" + "¿Cómo se llama la capital de Mongolia?\r\n"
				+ "¿Cuál es el río más largo del mundo?\r\n" + "¿En qué continente está Ecuador?\r\n"
				+ "¿Dónde originaron los juegos olímpicos?\r\n" + "¿Qué tipo de animal es la ballena?\r\n"
				+ "¿De qué colores es la bandera de México?\r\n" + "¿Qué cantidad de huesos en el cuerpo humano?\r\n"
				+ "¿Cuándo acabó la II Guerra Mundial?\r\n" + "¿Quién es el autor de el Quijote?\r\n"
				+ "¿Quién pintó “la última cena”?\r\n" + "¿En qué país se encuentra la torre de Pisa?\r\n"
				+ "¿Dónde se encuentra la Sagrada Familia?\r\n"
				+ "¿Cómo se denomina el resultado de la multiplicación?\r\n" + "¿Cuál es el océano más grande?\r\n"
				+ "¿Qué año llegó Cristóbal Colón a América?\r\n" + "¿Quién es el padre del psicoanálisis?\r\n"
				+ "¿Cuál es el disco más vendido de la historia?\r\n"
				+ "¿Quién es el famoso Rey de Rock en los Estados Unidos?\r\n"
				+ "¿Cómo se llama el nuevo presidente de los Estados Unidos?\r\n" + "¿Qué significa FIFA?\r\n"
				+ "¿En qué se especializa la cartografía?\r\n" + "¿Cuál es el país más grande del mundo?\r\n"
				+ "¿Dónde se encuentra la famosa Torre Eiffel?\r\n" + "¿Qué deporte practicaba Michael Jordan?\r\n"
				+ "¿En qué año comenzó la II Guerra Mundial?\r\n" + "¿Cuál es tercer planeta en el sistema solar?\r\n"
				+ "¿Qué país tiene forma de bota?\r\n" + "¿Cuál es la moneda del Reino Unido?\r\n"
				+ "¿Cuál es país más poblado de la Tierra?\r\n" + "¿En qué lugar del cuerpo se produce la insulina?\r\n"
				+ "¿Cuál es el color que representa la esperanza?\r\n"
				+ "¿Cómo se llama el himno nacional de Francia?\r\n"
				+ "¿De qué estilo arquitectónico es la Catedral de Notre Dame en París?\r\n"
				+ "¿Con qué se fabricaba el pergamino?\r\n" + "¿Cuántas patas tiene la araña?\r\n"
				+ "¿Cómo se llama el animal más rápido del mundo?\r\n" + "¿Cuál es la ciudad de los rascacielos?\r\n"
				+ "¿A qué país pertenecen los cariocas?\r\n"
				+ "¿En qué país se encuentra el famoso monumento Taj Mahal?\r\n"
				+ "¿Cuál es el nombre de la lengua oficial en china?\r\n"
				+ "¿Quién era el general de los nazis en la Segunda Guerra Mundial?\r\n"
				+ "¿Cómo le llaman a los textos de autores desconocidos?\r\n"
				+ "¿Qué instrumento óptico permite ver los astros de cerca?\r\n"
				+ "¿Cuál es el único mamífero capaz de volar?\r\n" + "¿Cuál es el libro sagrado del Islam?\r\n"
				+ "¿Qué es más pequeño, un átomo o una molécula?\r\n" + "¿Quién escribió “Hamlet”?\r\n"
				+ "¿Cuál es la moneda oficial de Estados Unidos?\r\n"
				+ "¿A qué país pertenece la ciudad de Varsovia?\r\n" + "¿A quién le crecía la nariz cuando mentía?\r\n"
				+ "¿En qué año se aprobó la actual Constitución española?\r\n" + "";

		List<String> pre = Arrays.asList(preguntas.split("\r\n"));

		//pre.forEach(e -> System.out.println(e));
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String urlDB = "jdbc:mysql://localhost/examen";

			conn = DriverManager.getConnection(urlDB, "root", "");	
			conn.setAutoCommit(false);
			String sqlStmt = "INSERT INTO preguntas VALUES (null, ?);";
			PreparedStatement prepStmt = conn.prepareStatement(sqlStmt);

			for(int i = 0; i < pre.size(); i++) {				
				prepStmt.setString(1, pre.get(i));
				conn.commit();
				int affectedRows = prepStmt.executeUpdate();
				System.out.println(pre.get(i) + " insertado" + " afectada " + affectedRows + " fila");				
			}
		} catch (SQLException e) {
			if (conn != null) {
				e.printStackTrace();
				try {
					conn.rollback();
					System.out.println("Hago rollback en catch");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
