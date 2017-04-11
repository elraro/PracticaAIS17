package agenda;

import java.util.ArrayList;

/**
 *
 * @author Oscar de la Cuesta - www.palentino.es
 */
public class Contacto {
	private String nombre;
	private ArrayList<String> telefonos;

	public Contacto() {
		this.nombre = null;
		this.telefonos = new ArrayList<String>();
	}

	public Contacto(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefonos.add(telefono);
	}

	public void set_nombre(String nomb) {
		this.nombre = nomb.toUpperCase();
	}

	public void set_telefono(String telf) {
		this.telefonos.add (telf);
	}
	public void set_telefonos(ArrayList<String>telefonos){
    	this.telefonos = telefonos;
    }
	public String getNombre() {
		return this.nombre;
	}

	public ArrayList<String> getTelefono() {
		return telefonos;
	}
	 
}