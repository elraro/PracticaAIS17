package agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Forte Jara
 */
public class Contacto implements Serializable, Comparable<Object> {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<Telefono> listaTelefonos;

	/**
	 * Caso de con nombre y sin numero
	 * 
	 * @param nombre
	 */
	public Contacto(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Caso de sin nombre y con numero
	 * 
	 * @param telefono
	 */
	public Contacto(ArrayList<Telefono> listaTelefono) {
		this.nombre = "";
		this.listaTelefonos = listaTelefono;
	}

	/**
	 * Caso normal
	 * 
	 * @param nombre
	 * @param telefono
	 */
	public Contacto(String nombre, List<Telefono> listaTelefono) {
		this.nombre = nombre;
		this.listaTelefonos = listaTelefono;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Telefono> getLista() {
		return this.listaTelefonos;
	}

	// Para buscar numero de Telefono
	// Para buscar por nombre

	@Override
	public int compareTo(Object o) {
		Contacto c = (Contacto) o;
		return this.nombre.compareTo(c.getNombre());
	}

	public Boolean equals(Contacto c) {
		return (this.nombre.equals(c.getNombre()));
	}

	@Override
	public String toString() {
		return "Contacto{" + "nombre=" + this.nombre + ", lista=" + this.listaTelefonos.toString() + '}';
	}
}
