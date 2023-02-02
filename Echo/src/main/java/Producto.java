public class Producto {
	private String id;
	private String nombre;
	private String descripcion;
	private int stockActual;
	private int stockMinimo;
	private double pvp;

	public Producto(String id, String nombre, String descripcion, int stockActual, int stockMinimo, double pvp) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.pvp = pvp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stockActual="
				+ stockActual + ", stockMinimo=" + stockMinimo + ", pvp=" + pvp + "]";
	}

	
}
