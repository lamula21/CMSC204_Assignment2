
public class InvalidNotationFormatException extends Exception {

	InvalidNotationFormatException(){
		super("Notation format is incorrect");
	}	
	InvalidNotationFormatException(String message){
		super(message);
	}
}
