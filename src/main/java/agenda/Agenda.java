package agenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Oscar de la Cuesta Campillo. www.palentino.es
 */
public class Agenda {

    private BaseDatos basedatos;									// muy importante.

    public Agenda() {
        BaseDatos aux = new BaseDatos();
        try {
             this.basedatos = aux.cargarFichero();
             this.basedatos.setContador_contactos(this.basedatos.getLista_contactos().size());
        } catch (IOException | ClassNotFoundException e) {
        }
    }


    public TreeSet<Contacto> getLista_contactos() {
        return this.basedatos.getLista_contactos();
    }

    public void setLista_contactos(TreeSet<Contacto> lista_contactos) {
        this.basedatos.setLista_contactos(lista_contactos);
    }

    public int getContador_contactos() {
        return this.basedatos.getContador_contactos();
    }

    public void setContador_contactos(int contador_contactos) {
        this.basedatos.setContador_contactos(contador_contactos);
    }

    public void aumentarContador() {
        this.basedatos.aumentarContador();
    }

    public void dismininuirContador() {
        this.basedatos.dismininuirContador();
    }

    public int getTope() {
        return this.basedatos.getTope();
    }

    public void setTope(int tope) {
        this.basedatos.setTope(tope);
    }

    public void Anadir(Contacto c) {
        this.basedatos.Anadir(c);
    }

    public void Anadir(String nombre, Integer telefono) {
        this.basedatos.Anadir(nombre,telefono);
    }

    public void eliminarTelefonoContacto(Contacto contacto, Integer tlf) {
        this.basedatos.eliminarTelefonoContacto(contacto, tlf);
    }

    public void Eliminar(String nombre) {
         this.basedatos.Eliminar(nombre);
    }

    public void Eliminar(Contacto c) {
         this.basedatos.Eliminar(c);
    }

    public void Consultar(String nombre, int telefono) {
        this.basedatos.Consultar(nombre,telefono);
    }

    public void Buscar(String nombre) {
        this.basedatos.Buscar(nombre);
    }

    public void Mostrar() {
        this.basedatos.Mostrar();
    }

    public void Vaciar() {
        this.basedatos.Vaciar();

    }

    public void Eliminar() throws IOException {
        this.basedatos.Eliminar();
    }

    public void Modificar() {
        this.basedatos.Modificar();
    }    
}
