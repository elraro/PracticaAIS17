package agenda;
import java.util.*;

/**
 * @author Diego Forte Jara
 */
class Telefono implements Comparable<Object> {
    
    private String numero; //String por lo que hablamos. Asi podemos empezar con +34 (extension internacional espa�ola) y el numero
    private String tipo; 
    
    public Telefono(String numero) { //Constructor para el primer numero
        this.numero = numero;
        this.tipo = "";
    }
    
    public Telefono(String numero, String tipo) {//Constructor para segundo numero y posteriores
        this.numero = numero;
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
        public int compareTo(Object o) {
            Telefono c =  (Telefono) o;
            return getNumero().compareTo(c.getNumero());
        }
        
        public Boolean equals(Contacto c) {
            return (this.getNumero().equals(c.getNumero()));
        }
}
