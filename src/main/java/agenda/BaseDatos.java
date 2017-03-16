/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author saulalonso
 */
public class BaseDatos {


    public static void guardarFicheros(Agenda historial) throws IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agenda"+File.separator);
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

    public static Agenda cargarFichero() throws ClassNotFoundException, FileNotFoundException, IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agenda"+File.separator);
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

    public static void guardarFicherosSeguridad(Agenda historial) throws IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agendaSeguridad"+File.separator);
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
        File fichero = new File(System.getProperty("user.dir") + File.separator + "agendaSeguridad"+File.separator);
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

}