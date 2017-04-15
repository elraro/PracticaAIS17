package contacts;

public class WrongNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @author Saul Alonso
	 */
	public WrongNumberException() {
		super("Invalid number");
	}

}
