package dal;

/**
 * DALException
 * Etend exception (donc possède les mêmes attributs message, etc...)
 * Exception personalisée qu'on utilise dans la couche DAL
 *
 */
public class DALException extends Exception {

	// on surcharge le constructeur pour ajouter une personalisation au message
	public DALException(String message) {
		super("Exception dans la couche DAL \n" + message);
	}

	
}
