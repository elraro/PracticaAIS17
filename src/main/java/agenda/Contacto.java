package agenda;

import java.util.ArrayList;

/**
 * @author Diego Forte Jara
 */
public class Contacto implements Comparable<Object> {

    private String nombre;
    private ArrayList<Telefono> lista = new ArrayList();

    /*public Contacto() { Tengo dudas de que esto nos sirva para algo
		this.nombre = null;
		this.lista = ;
	}*/
    public Contacto(String nombre) { //Caso de con nombre y sin numero
        this.nombre = nombre;
    }

    public Contacto(ArrayList<Telefono> telefono) { //Caso de sin nombre y con numero
        this.nombre = "";
        this.lista = telefono;
    }

    public Contacto(String nombre, ArrayList<Telefono> telefono) { //Caso normal
        this.nombre = nombre;
        this.lista = telefono;
    }

    public void setNombre(String nomb) {
        this.nombre = nomb.toUpperCase();
    }

    public void addTelefono(Telefono telf) {
        if (this.lista.indexOf(telf) != -1) { //Evita duplicados en el contacto
            this.lista.add(telf);

        } else {
            System.out.println("El numero de telefono introducido ya existe en el contacto");
        }
    }

    public String getNombre() {
        if (this.nombre == "") {
            return this.lista.get(0).getNumero(); //Muestra el primer numero de telefono como nombre si no hay nombre
        } else {
            return this.nombre;
        }
    }

    public ArrayList<Telefono> getLista() {
        return lista;
    }
    
    

    //Para buscar numero de Telefono
    //Para buscar por nombre

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
}
