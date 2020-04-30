package empleado;

public class Empleado {
	private Integer id;
	private String nombre;
	
	public Empleado() {
		super();
		this.id = 0;
		this.nombre = "";
	}
	
	public Empleado(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}