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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author saulalonso
 */
public class CSV {

    private TreeSet<Contacto> lista_contactos = new TreeSet<>();


    public static void ExportarCSV(TreeSet<Contacto>lista_contactos,String ruta) throws IOException {
    	Iterator<Contacto> it = lista_contactos.iterator();
    	String contenido = "";
    	while(it.hasNext()){
    		Contacto aux = it.next();
    		contenido=contenido+aux.getNombre()+";";
    		ArrayList<String> telefonos = aux.getTelefono();//Cambiar Long por String
    		for (int i=0;i<telefonos.size();i++){
    			contenido=contenido+telefonos.get(i)+";";
    		}
    		contenido=contenido+"\n";
    	}
        File fichero = new File(System.getProperty("user.dir") + File.separator + ruta + File.separator);
        FileOutputStream f = null;
        ObjectOutputStream salida = null;
        try {
            f = new FileOutputStream(fichero);
            salida = new ObjectOutputStream(f);
            salida.writeObject(contenido);
            salida.close();

        } catch (IOException e) {

        }
        System.out.println("Correcto!! Guardado..");
    }

    public TreeSet<Contacto> ImportarCSV(String ruta) throws ClassNotFoundException, FileNotFoundException, IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + ruta + File.separator);
        String contenido;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        BufferedInputStream bufferedInputStream;
        TreeSet<Contacto> lista_contactos = new TreeSet<>();
        try {
            if (fichero.exists()) {
                fileInputStream = new FileInputStream(fichero);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                objectInputStream = new ObjectInputStream(bufferedInputStream);
                contenido = (String) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
                String [] contenidoaux  = contenido.split(";");
                for(int i = 0; i < contenidoaux.length; i++){
                	String nombre = contenidoaux[i];
                	ArrayList<String> numeros = new ArrayList<String>();
                	while (isNumeric(contenidoaux[i+1])){
                		i++;
                		numeros.add(contenidoaux[i]);
                	}
                    Contacto contacto = new Contacto();
                    contacto.set_nombre(nombre);
                    contacto.set_telefonos(numeros);
                    lista_contactos.add(contacto);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return lista_contactos;
    }
    
    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }
}

