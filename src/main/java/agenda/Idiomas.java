package agenda;

import java.util.HashMap;
import java.util.Map;


public class Idiomas {
//Nombre del boton, Traduccion
	Map <String, String> Ingles= new HashMap<String, String>();
	
	public void Ingles(){
		Ingles.put("Idioma","Language");
		Ingles.put("Nuevo","New Contact");
		Ingles.put("Nombre","Name");
		Ingles.put("Telf","Phone");
		Ingles.put("ErrorTelf","It's not a numbre. Format incorrect");
		Ingles.put("Modificar","Modify Contact");
		Ingles.put("Borrar","Dalete Contact");
		Ingles.put("Buscar", "Find Contact");
		Ingles.put("ImpEx","Import / Export");
		Ingles.put("Cargar","Charge Contact List");
		Ingles.put("Guardar","Save Contact List");	
		Ingles.put("Salir","You've exit of program");
		Ingles.put("OpcionIncorrecta","Wrong choice...");
		Ingles.put("ContactoExistente","Contact already exist");
		Ingles.put("ContactoInexistente","Non-existent");
		Ingles.put("AgendaLlena","Contact list is full");
		Ingles.put("NoContactos","No contacts");
		Ingles.put("AvisoEliminar","All contacts will be deleted");
		Ingles.put("Seguro?","Are you sure?(Y/N)");
		Ingles.put("ExitoVaciado","Contacts list emptied correctly");
		Ingles.put("Cancelado","Canceled");
		Ingles.put("NombreEliminar","Contact name to delete");
		Ingles.put("NumeroEliminar","Contact phone to delete");
		Ingles.put("ExitoEliminar","Contact deleted correctly");
		Ingles.put("NoEncontrado","Contact not found");
		Ingles.put("NombreModificar","Contact name to modify");
		Ingles.put("NumeroModificar", "Contact phone to modify");
		Ingles.put();
	}
	
	public String getIngles(String boton){
		return Ingles.get(boton);
	}
	
	
	
}
