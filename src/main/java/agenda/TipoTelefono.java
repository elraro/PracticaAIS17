package agenda;

import java.io.Serializable;

/**
 * @author Saul Alonso
 */
public enum TipoTelefono implements Serializable {

	CASA("Casa"), OFICINA("Oficina"), MOVIL("Movil"), FAX("Fax");

	private String tipo;

	private TipoTelefono(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
