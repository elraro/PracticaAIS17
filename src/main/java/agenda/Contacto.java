package agenda;

import java.util.ArrayList;

/**
 * @author Diego Forte Jara
 */
public class Contacto implements Comparable<Object> {

	private String nombre;
	private ArrayList<Telefono> lista = new ArrayList<Telefono>();

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
	public Contacto(ArrayList<Telefono> telefono) {
		this.nombre = "";
		this.lista = telefono;
	}

	/**
	 * Caso normal
	 * 
	 * @param nombre
	 * @param telefono
	 */
	public Contacto(String nombre, ArrayList<Telefono> telefono) {
		this.nombre = nombre;
		this.lista = telefono;
	}

	public void setNombre(String nomb) {
		this.nombre = nomb.toUpperCase();
	}

	public void addTelefono(Telefono telf) {
		if (this.lista.indexOf(telf) != -1) { // Evita duplicados en el contacto
			this.lista.add(telf);

		} else {
			System.out.println("El numero de telefono introducido ya existe en el contacto");
		}
	}

	public String getNombre() {
		if (this.nombre == "") {
			return this.lista.get(0).getNumero(); // Muestra el primer numero de
													// telefono como nombre si
													// no hay nombre
		} else {
			return this.nombre;
		}
	}

	public ArrayList<Telefono> getLista() {
		return lista;
	}

	// Para buscar numero de Telefono
	// Para buscar por nombre

	@Override
	public int compareTo(Object o) {
		Contacto c = (Contacto) o;
		return getNombre().compareTo(c.getNombre());
	}

	public Boolean equals(Contacto c) {
		return (this.getNombre().equals(c.getNombre()));
	}

	@Override
	public String toString() {
		return "Contacto{" + "nombre=" + nombre + ", lista=" + lista + '}';
	}

	public void setLista(ArrayList<Telefono> lista) {
		this.lista = lista;
	}
}
