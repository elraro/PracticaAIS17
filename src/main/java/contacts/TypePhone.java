package contacts;

import java.io.Serializable;

/**
 * @author Saul Alonso
 */
public enum TypePhone implements Serializable {

	HOME("Home"), OFFICE("Office"), MOBILE("Mobile"), FAX("Fax");

	private String type;

	private TypePhone(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
