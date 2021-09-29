
public class StackOverflowException extends Exception {

	StackOverflowException(){
		super("a top or pop method is called on an empty stack.");
	}	
	StackOverflowException(String message){
		super(message);
	}
}
