package agenda;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
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
    		ArrayList<String> telefonos = aux.getTelefono();
    		for (int i=0;i<telefonos.size();i++){
    			contenido=contenido+telefonos.get(i)+";";
    		}
    		contenido=contenido+"\r\n";
    	}
        File fichero = new File(System.getProperty("user.dir") + File.separator + ruta + File.separator);
        Writer f = null;
        try {
            f = new BufferedWriter(new FileWriter(fichero));
            f.write(contenido);
            f.close();

        } catch (IOException e) {

        }
        System.out.println("Correcto!! Guardado..");
    }

    public TreeSet<Contacto> ImportarCSV(String ruta) throws ClassNotFoundException, FileNotFoundException, IOException {
        File fichero = new File(System.getProperty("user.dir") + File.separator + ruta + File.separator);
        String linea;
        FileReader f=null;
        BufferedReader bufferedReader = null;
        TreeSet<Contacto> lista_contactos = new TreeSet<>();
        try {
            if (fichero.exists()) {
                f = new FileReader(fichero);
                bufferedReader = new BufferedReader(f);
                while ((linea = bufferedReader.readLine())!=null){
                	String []contenido=linea.split(";");
                	String nombre = contenido[0];
                	ArrayList<String> numeros = new ArrayList<String>();
                	for (int i = 1; i <contenido.length;i++){
                		numeros.add(contenido[i]);
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

