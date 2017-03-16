/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

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

    private final File fichero = new File(System.getProperty("user.dir") + File.separator + "agenda"+File.separator);
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    void guardarEnFicheros() throws IOException {

        try {

            if (!(fichero.exists())) {
                fichero.createNewFile();
            }

            fileOutputStream = new FileOutputStream(fichero);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            fileOutputStream.close();
            objectInputStream.close();

        } catch (IOException e) {

        }
        System.out.println("Correcto!! Guardado..");
    }

    protected Agenda cargarFichero() throws ClassNotFoundException {
        Agenda agenda = new Agenda();
        try {
            if (fichero.exists()) {

                fileInputStream = new FileInputStream(fichero);
                objectInputStream = new ObjectInputStream(fileInputStream);

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
