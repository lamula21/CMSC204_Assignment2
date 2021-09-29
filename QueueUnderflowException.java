
public class QueueUnderflowException extends Exception {

	QueueUnderflowException(){
		super("enqueue method is called on a full queue");
	}
	QueueUnderflowException(String message){
		super(message);
	}
}
