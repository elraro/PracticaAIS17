package agenda;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
public class Tests {

	@Test
	public void ExportarCSVTest() throws IOException {
		TreeSet<Contacto> lista_contactos = new TreeSet<Contacto>();
		Contacto contacto1 = new Contacto("Pepe","916180325");
		//contacto1.set_nombre("Pepe");
		//contacto1.set_telefono("916180325");
		Contacto contacto2 = new Contacto();
		contacto2.set_nombre("Juan");
		contacto2.set_telefono("689961352");		
		lista_contactos.add(contacto1);
		lista_contactos.add(contacto2);
		CSV.ExportarCSV(lista_contactos, "prueba.csv");
		File fichero = new File(System.getProperty("user.dir") + File.separator +"prueba.csv"+ File.separator);
		assertTrue("El fichero no ha sido creado. La exportacion ha sido fallida",fichero.exists());
		
	}

}
