
public class QueueOverflowException extends Exception {

	QueueOverflowException(){
		super("dequeue method is called on an empty queue");
	}
	QueueOverflowException(String message){
		super(message);
	}
}
