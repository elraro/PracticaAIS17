/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saulalonso
 */
public class BaseDatos {

    private TreeSet<Contacto> lista_contactos = new TreeSet<>();
    private int contador_contactos = 0; // Contador de objetos creados. Variable
    private int tope = 100;

    public BaseDatos() {
    }

    public BaseDatos(TreeSet<Contacto> lista_contactos) {
        this.lista_contactos = lista_contactos;
    }

    public TreeSet<Contacto> getLista_contactos() {
        return lista_contactos;
    }

    public void setLista_contactos(TreeSet<Contacto> lista_contactos) {
        this.lista_contactos = lista_contactos;
        this.setContador_contactos(lista_contactos.size());
    }

    public int getContador_contactos() {
        return contador_contactos;
    }

    public void setContador_contactos(int contador_contactos) {
        this.contador_contactos = contador_contactos;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public static void guardarFicheros(BaseDatos basedatos) throws IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agenda" + File.separator);
        FileOutputStream f = null;
        ObjectOutputStream salida = null;
        try {

            f = new FileOutputStream(fichero);
            salida = new ObjectOutputStream(f);
            salida.writeObject(basedatos);
            salida.close();

        } catch (IOException e) {

        }
        System.out.println("Correcto!! Guardado..");
    }

    public BaseDatos cargarFichero() throws ClassNotFoundException, FileNotFoundException, IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agenda" + File.separator);
        BaseDatos basedatos = new BaseDatos();
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        BufferedInputStream bufferedInputStream;

        try {
            if (fichero.exists()) {

                fileInputStream = new FileInputStream(fichero);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                objectInputStream = new ObjectInputStream(bufferedInputStream);
                basedatos = (BaseDatos) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return basedatos;
    }

    public static void guardarFicherosSeguridad(Agenda historial) throws IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agendaSeguridad" + File.separator);
        FileOutputStream f = null;
        ObjectOutputStream salida = null;
        try {

            f = new FileOutputStream(fichero);
            salida = new ObjectOutputStream(f);
            salida.writeObject(historial);
            salida.close();

        } catch (IOException e) {

        }
        System.out.println("Correcto!! Guardado..");
    }

    public static Agenda cargarFicheroSeguridad() throws ClassNotFoundException, FileNotFoundException, IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agendaSeguridad" + File.separator);
        Agenda agenda = new Agenda();
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        BufferedInputStream bufferedInputStream;

        try {
            if (fichero.exists()) {

                fileInputStream = new FileInputStream(fichero);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                objectInputStream = new ObjectInputStream(bufferedInputStream);
                agenda = (Agenda) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return agenda;
    }

    public void dismininuirContador() {
        this.contador_contactos--;
    }

    public void aumentarContador() {
        this.contador_contactos++;
    }

    public void Anadir(Contacto c) {
        this.lista_contactos.add(c);
    }

    public void Anadir(String nombre, Integer telefono) {
        if (this.contador_contactos < tope - 1) {
            Contacto contacto = new Contacto(nombre, telefono);
            this.lista_contactos.add(contacto);
            this.aumentarContador();
        } else {
            System.out.println("La agenda está llena");
        }
    }

    public void Consultar(String nombre, Integer telefono) {
        Iterator<Contacto> it = getLista_contactos().iterator();
        while (it.hasNext()) {
            Contacto aux = it.next();
            if (aux.getNombre().equals(nombre) || (aux.getTelefono().equals(telefono))) {
                System.out.println(aux);
            } else {
                System.out.println("No existe el contacto");
            }
        }
    }

    public void Buscar(String nombre) {
        boolean encontrado = false;

        Iterator<Contacto> it = getLista_contactos().iterator();
        while (it.hasNext()) {
            Contacto aux = it.next();
            if (aux.getNombre().equals(nombre)) {
                System.out.println(aux);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Contacto inexistente");
        }
    }

    public void Mostrar() {
        if (this.getContador_contactos() == 0) {
            System.out.println("No hay contactos");
        } else {
            Iterator<Contacto> it = getLista_contactos().iterator();
            while (it.hasNext()) {
                Contacto aux = it.next();
                System.out.println(aux);

            }
        }
    }

    public void Vaciar() {
        try {
            System.out.println("Se eliminarán todos los contactos");
            System.out.println("¿Estas seguro (S/N)?");
            String respuesta;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            respuesta = teclado.readLine();
            respuesta = respuesta.toUpperCase();
            if (respuesta.equals("S")) {
                this.getLista_contactos().clear();
                this.setContador_contactos(0);
                System.out.println("Agenda vaciada correctamente");
            } else {
                System.out.println("Acción cancelada");
            }
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Eliminar() throws IOException {
        try {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Nombre de contacto a eliminar:");
            String eliminar = teclado.readLine().toUpperCase();
            if (this.getContador_contactos() == 0) {
                System.out.println("No hay contactos");
            } else {
                Iterator<Contacto> it = getLista_contactos().iterator();
                while (it.hasNext()) {
                    Contacto aux = it.next();
                    if (aux.getNombre().equals(eliminar)) {
                        System.out.println(aux);
                        System.out.println("¿Qué contacto quieres eliminar, introduce el número asociado?");
                        Integer eliminar_numero = Integer.parseInt(teclado.readLine());
                        System.out.println("¿Estas seguro (S/N)?");
                        String respuesta;
                        respuesta = teclado.readLine();
                        respuesta = respuesta.toUpperCase();
                        if (respuesta.equals("S")) {
                            Contacto auxiliar = aux;
                            Eliminar(aux);
                            Anadir(auxiliar);
                            this.dismininuirContador();
                            System.out.println("Contacto eliminado correctamente");
                        } else {
                            System.out.println("Operacion cancelada");
                        }
                    } else {
                        System.out.println("Contacto no encontrado");
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Modificar() {
        try {
            boolean encontrado = false;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Nombre de contacto a modificar:");
            String eliminar = teclado.readLine().toUpperCase();
            if (this.getContador_contactos() == 0) {
                System.out.println("No hay contactos");
            } else {
                Iterator<Contacto> it = getLista_contactos().iterator();
                while (it.hasNext()) {
                    Contacto aux = it.next();
                    if (aux.getNombre().equals(eliminar)) {
                        System.out.println(aux);
                        System.out.println("¿Qué contacto quieres modificar?, introduce el número:");
                        Integer modificar_numero = Integer.parseInt(teclado.readLine());

                        Contacto auxiliar = aux;
                        Eliminar(aux);
                        eliminarTelefonoContacto(aux, modificar_numero);
                        System.out.println("Introduce teléfono, formato numerico:");
                        Integer telefono_nuevo = Integer.parseInt(teclado.readLine());
                        aux.getTelefono().add(telefono_nuevo);

                    } else {
                        System.out.println("No existe el contacto");
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarTelefonoContacto(Contacto contacto, Integer tlf) {
        contacto.getTelefono().remove(tlf);
    }

    public void Eliminar(String nombre) {
        Iterator<Contacto> it = getLista_contactos().iterator();
        while (it.hasNext()) {
            Contacto aux = it.next();
            if (aux.getNombre().equals(nombre)) {
                this.lista_contactos.remove(aux);
            } else {
                System.out.println("No existe el contacto");
            }
        }
    }

    public void Eliminar(Contacto c) {
        this.lista_contactos.remove(c);
    }

    public void Consultar(String nombre, int telefono) {
        Consultar(nombre, telefono);
    }
}
