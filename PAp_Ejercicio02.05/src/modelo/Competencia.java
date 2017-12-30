package modelo;

import java.util.List;

public class Competencia implements java.io.Serializable {

	private String nombre;
	private String categoria;
	
	public Competencia() {
		
	}

	public Competencia(String nombre, String categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Competencia [nombre=" + nombre + ", categoria=" + categoria + "]";
	}

}
