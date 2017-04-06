package agenda;

import java.util.HashMap;
import java.util.Map;


public class Idiomas {
//Nombre del boton, Traduccion
	Map <String, String> Ingles= new HashMap<String, String>();
	Map <String,String> Espaniol = new HashMap<String,String>();
	
	public void Ingles(){
		Ingles.put("Idioma","Language");
		Ingles.put("Nuevo","New Contact");
		Ingles.put("Nombre","Name");
		Ingles.put("Telf","Phone");
		Ingles.put("ErrorTelf","It's not a numbre. Format incorrect");
		Ingles.put("Modificar","Modify Contact");
		Ingles.put("Borrar","Delete Contact");
		Ingles.put("Buscar", "Find...");
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
		Ingles.put("Aceptar","Accept");
		Ingles.put("Cancelar","Cancel");
		Ingles.put("NombreEliminar","Contact name to delete");
		Ingles.put("NumeroEliminar","Contact phone to delete");
		Ingles.put("ExitoEliminar","Contact deleted correctly");
		Ingles.put("NoEncontrado","Contact not found");
		Ingles.put("NombreModificar","Contact name to modify");
		Ingles.put("NumeroModificar", "Contact phone to modify");
	}
	
	public void Espaniol(){
		Espaniol.put("Idioma","Idioma");
		Espaniol.put("Nuevo","Nuevo Contacto");
		Espaniol.put("Nombre","Nombre");
		Espaniol.put("Telf","Telefono");
		Espaniol.put("ErrorTelf","No es un numero. Formato Incorrecto");
		Espaniol.put("Modificar","Modificar Contacto");
		Espaniol.put("Borrar","Borrar Contacto");
		Espaniol.put("Buscar", "Buscar...");
		Espaniol.put("ImpEx","Importar/Exportar");
		Espaniol.put("Cargar","Cargar Agenda");
		Espaniol.put("Guardar","Guardar Agenda");	
		Espaniol.put("Salir","Ha salido del programa");
		Espaniol.put("OpcionIncorrecta","Opncion incorrecta...");
		Espaniol.put("ContactoExistente","Este contacto ya existe");
		Espaniol.put("ContactoInexistente","Este contacto no existe");
		Espaniol.put("AgendaLlena","La agenda esta llena");
		Espaniol.put("NoContactos","No hay contactos");
		Espaniol.put("AvisoEliminar","Se eliminaran todos los contactos");
		Espaniol.put("Seguro?","¿Esta seguro?(S/N)");
		Espaniol.put("ExitoVaciado","COntactos eliminados correctamente");
		Espaniol.put("Aceptar", "Aceptar");
		Espaniol.put("Cancelar","Cancelar");
		Espaniol.put("NombreEliminar","Nombre del contactoa a eliminar");
		Espaniol.put("NumeroEliminar","Telefono del contacto a eliminar");
		Espaniol.put("ExitoEliminar","COntacto eliminado correctamente");
		Espaniol.put("NoEncontrado","Contacto no encontrado");
		Espaniol.put("NombreModificar","Nombre del contacto a eliminar");
		Espaniol.put("NumeroModificar", "Telefono del contactoa a eliminar ");
	}
	
	public String getIngles(String boton){
		return Ingles.get(boton);
	}
	public String getEspaniol(String boton){
		return Espaniol.get(boton);
	}
	
	
}
