
import java.util.Calendar;

public class Valida {
	static boolean Nombre(String nombre) {
		return nombre != null;
	}

	static boolean Clave(String clave) {
		return (clave.length() > 5 && clave.length() < 13);
	}

	static boolean Fecha(String date){
		return date != null;
	}

	static boolean isOver18(Calendar birthDate) {
		Calendar currentDate = Calendar.getInstance();
		int years = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		if (birthDate.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)
				|| (birthDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
						&& birthDate.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH))) {
			years--;
		}
		return years >= 18;
	}

//	public static void main(String[] args) {
//		Calendar birthDate = Calendar.getInstance();
//		birthDate.set(2003, Calendar.JANUARY, 1);
//		if (isOver18(birthDate)) {
//			System.out.println("You are over 18 years old");
//		} else {
//			System.out.println("You are under 18 years old");
//		}
//	}

	

	static boolean Sexo(String sexo) {
		return sexo.equals("Mujer") || sexo.equals("Hombre");
	}

	static boolean Paises(String pais) {
		return pais != null;
	}

	static boolean Comentario(String comentario) {
		return comentario != null;
	}

	static boolean Colores(String[] colores) {
		return colores != null;
	}
}