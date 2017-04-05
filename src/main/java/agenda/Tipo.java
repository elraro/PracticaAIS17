package agenda;

/**
 * @author saulalonso
 */
public enum Tipo {

	CASA("Casa"), OFICINA("Oficina"), MOVIL("Movil"), FAX("Fax"), VACIO("Vacio");

	private String tipo;

	private Tipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
