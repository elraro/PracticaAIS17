package agenda;

import java.util.ArrayList;

/**
 *
 * @author Oscar de la Cuesta - www.palentino.es
 */
public class Contacto {
	private String nombre;
	private ArrayList<Integer> telefono;

	public Contacto() {
		this.nombre = null;
		this.telefono = new ArrayList<>();
	}

	public Contacto(String nombre, int telefono) {
		this.nombre = nombre;
		this.telefono.add(telefono);
	}

	public void set_nombre(String nomb) {
		this.nombre = nomb.toUpperCase();
	}

	public void set_telefono(int telf) {
		this.telefono.add(telf);
	}

	public String getNombre() {
		return this.nombre;
	}

	public ArrayList<Integer> getTelefono() {
		return telefono;
	}

}