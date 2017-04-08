package agenda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oscar de la Cuesta Campillo. www.palentino.es
 */
public class AgendaLogica {

	private BaseDatos baseDatos;
	
	public AgendaLogica() {
		this.baseDatos = new BaseDatos();
		try {
			this.baseDatos.cargarFichero();
		} catch (ClassNotFoundException | IOException e) {
			// TODO
			// Lanzar aviso de que no se ha podido leer el fichero por lo que sea
		}
	}
	
	public List<Contacto> listaContactos() {
		return new ArrayList<Contacto>(this.baseDatos.getListaContactos());
	}

	public boolean anadirContacto(Contacto c) {
		return this.baseDatos.anadirContacto(c);
	}
	
	public void guardar() {
		this.baseDatos.guardarFichero();
	}

}