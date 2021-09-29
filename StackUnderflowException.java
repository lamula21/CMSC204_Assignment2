
public class StackUnderflowException extends Exception {

	StackUnderflowException(){
		super("push method is called on a full stack.");
	}	
	StackUnderflowException(String message){
		super(message);
	}
}
