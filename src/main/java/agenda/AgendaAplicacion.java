/**
 * 
 */
package agenda;

/**
 * @author alberto
 *
 */
public class AgendaAplicacion {
	public AgendaAplicacion() {
		new AgendaInterfaz(new AgendaLogica());
	}

	public static void main(String[] args) {
		new AgendaAplicacion();
	}
}
