package agenda;

/**
 * @author Alberto de Dios Bernáez
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
