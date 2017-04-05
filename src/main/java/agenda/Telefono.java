package agenda;


/**
 * @author Diego Forte Jara
 */
class Telefono implements Comparable<Object> {

	private String numero; // String por lo que hablamos. Asi podemos empezar
							// con +34 (extension internacional española) y el
							// numero
	private Tipo tipo;

	public Telefono(String numero) { // Constructor para el primer numero
		this.numero = numero;
		this.tipo = Tipo.VACIO;
	}

	public Telefono(String numero, Tipo tipo) {// Constructor para segundo
												// numero y posteriores
		this.numero = numero;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
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
