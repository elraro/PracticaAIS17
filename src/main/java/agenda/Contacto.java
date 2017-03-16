package agenda;

import java.util.ArrayList;

/**
 *
 * @author Oscar de la Cuesta - www.palentino.es
 */
public class Contacto implements Comparable {

    private String nombre;
    private ArrayList<Integer> telefono;

    public Contacto() {
        this.nombre = null;
        this.telefono = new ArrayList<>();
    }

    public Contacto(String nombre, Integer telefono) {
        this.nombre = nombre;
        this.telefono.add(telefono);
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", telefono=" + telefono + '}';
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
    
    public int compareTo(Object o) {
        Contacto c = (Contacto) o;
        return getNombre().compareTo(c.getNombre());
    }

    public Boolean equals(Contacto c) {
        return (this.getNombre() == c.getNombre());
    }
    
    public void eliminarNumero(Integer telefono){
        this.getTelefono().remove(telefono);
    }
    
    public void anadirNumero(Integer telefono){
        this.getTelefono().add(telefono);
    }
}