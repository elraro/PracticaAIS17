package agenda;

/**
 * @author Diego Forte Jara
 */
class Telefono implements Comparable<Object> {

	/**
	 * String por lo que hablamos. Asi podemos empezar con +34 (extension
	 * internacional española) y el numero
	 */
	private String numero;
	private TipoTelefono tipo;

	public Telefono(String numero, TipoTelefono tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefono getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoTelefono tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Object o) {
		Telefono t = (Telefono) o;
		return (getNumero()).compareTo(t.getNumero());
	}

	public Boolean equals(Telefono t) {
		return (this.getNumero().equals(t.getNumero()));
	}

	@Override
	public String toString() {
		return "Telefono [numero=" + numero + ", tipo=" + tipo + "]";
	}
}
