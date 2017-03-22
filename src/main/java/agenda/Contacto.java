package agenda;

import java.util.ArrayList;

/**
 *
 * @author Oscar de la Cuesta - www.palentino.es
 */
public class Contacto implements Comparable {

    private String nombre;
    private ArrayList<Integer> telefonos;

    public Contacto() {
        this.nombre = null;
        this.telefonos = new ArrayList<>();
    }

    public Contacto(String nombre, Integer telefono) {
        this.nombre = nombre;
        this.telefonos.add(telefono);
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", telefono=" + telefonos + '}';
    }

    public void set_nombre(String nomb) {
        this.nombre = nomb.toUpperCase();
    }

    public void set_telefono(int telf) {
        this.telefonos.add(telf);
    }

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList<Integer> getNumeros(){
    	return this.telefonos;
    }
    public ArrayList<Integer> getTelefono() {
        return telefonos;
    }
    
    @Override
    public int compareTo(Object o) {
        Contacto c = (Contacto) o;
        return getNombre().compareTo(c.getNombre());
    }

    public Boolean equals(Contacto c) {
        return (this.getNombre().equals(c.getNombre()));
    }
    
}