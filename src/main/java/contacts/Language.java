package contacts;

import java.util.HashMap;
import java.util.Map;

public class Language {
	private Map<String, String> english = new HashMap<String, String>();
	private Map<String, String> spanish = new HashMap<String, String>();
	
	private String language;

	public Language(String language) {
		if (language.equals("spanish")) {
			this.language = "spanish";
		} else {
			this.language = "english";
		}
		// Main
		english.put("ContactsTitle", "URJC Contacts");
		english.put("ViewContact", "View contact");
		english.put("NewContact", "New contact");
		english.put("ModifyContact", "Modify Contact");
		english.put("DeleteContact", "Delete Contact");
		english.put("Search", "Search");
		english.put("Search2", "Search...");
		english.put("ImportCSV", "Import CSV");
		english.put("ExportCSV", "Export CSV");
		// Options
		english.put("Name", "Name");
		english.put("Phones", "Phones");
		english.put("AddPhone", "Add phone");
		english.put("ModifyPhone", "Modify phone");
		english.put("DeletePhone", "Delete phone");
		english.put("Phone", "Phone");
		english.put("TypePhone", "Type");
		english.put("Accept", "Accept");
		english.put("Cancel", "Cancel");
		english.put("Close", "Close");
		english.put("SaveChanges", "Save changes?");
		english.put("Yes", "Yes");
		english.put("No", "No");
		english.put("DeletePhoneWarning", "Are you sure you want to delete the phone?");
		english.put("DeleteContactWarning", "Are you sure you want to delete the contact?");
		english.put("WrongNumber", "It's not a number. Incorrect format. Please fix it.");
		english.put("WrongLengthNumber", "The number must be 3 or greater in length.");
		english.put("DuplicatedContact", "A contact with that name already exists. You can not save contacts with the repeated name.");
		english.put("Warning", "Warning");
		english.put("Home", "Home");
		english.put("Office", "Office");
		english.put("Fax", "Fax");
		english.put("Mobile", "Mobile");
		english.put("CSVError", "Error when trying import csv. Please check the file.");

		//Spanish
		spanish.put("ContactsTitle", "Agenda URJC");
		spanish.put("NewContact", "Nuevo contacto");
		spanish.put("ViewContact", "Ver contacto");
		spanish.put("ModifyContact", "Modificar contacto");
		spanish.put("DeleteContact", "Borrar contacto");
		spanish.put("Search", "Buscar");
		spanish.put("Search2", "Buscar...");
		spanish.put("ImportCSV", "Importar CSV");
		spanish.put("ExportCSV", "Exportar CSV");
		// Options
		spanish.put("Name", "Nombre");
		spanish.put("Phones", "Teléfonos");
		spanish.put("AddPhone", "Añadir télefono");
		spanish.put("ModifyPhone", "Modificar télefono");
		spanish.put("DeletePhone", "Borrar teléfono");
		spanish.put("Phone", "Teléfono");
		spanish.put("TypePhone", "Tipo");
		spanish.put("Accept", "Aceptar");
		spanish.put("Cancel", "Cancelar");
		spanish.put("Close", "Cerrar");
		spanish.put("SaveChanges", "¿Desea guardar los cambios?");
		spanish.put("Yes", "Sí");
		spanish.put("No", "No");
		spanish.put("DeletePhoneWarning", "¿Está seguro que desea borrar este teléfono?");
		spanish.put("DeleteContactWarning", "¿Está seguro que desea borrar este contacto?");
		spanish.put("WrongNumber", "El formato del número es incorrecto. Por favor, arreglelo.");
		spanish.put("WrongLengthNumber", "La longitud del número debe ser 3 o superior.");
		spanish.put("DuplicatedContact", "Ya existe un contacto con ese nombre. No se permiten contactos con nombres repetidos.");
		spanish.put("Warning", "Advertencia");
		spanish.put("Home", "Casa");
		spanish.put("Office", "Oficina");
		spanish.put("Fax", "Fax");
		spanish.put("Mobile", "Móvil");
		english.put("CSVError", "Error al intentar importar el csv. Por favor, comprueba el fichero.");
	}

	public String getProperty(String property) {
		if (this.language.equals("spanish")) {
			String p = this.spanish.get(property);
			if (p == null) {
				return "Wrong property";
			} else {
				return p;
			}
		}
		else {
			String p = this.english.get(property);
			if (p == null) {
				return "Wrong property";
			} else {
				return p;
			}
		}
	}
}
