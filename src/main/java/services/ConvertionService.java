package services;

import java.util.Base64;

public class ConvertionService {
	
	public static String convertTobas64(String input) {
	    try {
	    	// Convertir la chaîne d'entrée en base 64
	    	String base64Bytes = Base64.getEncoder().encodeToString(input.getBytes());

	        return base64Bytes;
	      
	    } catch (Exception e) {
	        // Gérer l'exception si l'algorithme de hachage MD5 n'est pas disponible
	        e.printStackTrace();
	    }
	    return null;
	}
	

}
