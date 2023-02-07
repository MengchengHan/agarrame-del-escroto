package DataTypes;

public class Respuesta {

	private int id;
	private String enunciado;
	private boolean valida;
	
	public Respuesta(int id, String enunciado, boolean valida) {
		this.id = id;
		this.enunciado = enunciado;
		this.valida = valida;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public boolean isValida() {
		return valida;
	}
	public void setValida(boolean valida) {
		this.valida = valida;
	}
	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", enunciado=" + enunciado + ", valida=" + valida + "]";
	}
	
	
}
