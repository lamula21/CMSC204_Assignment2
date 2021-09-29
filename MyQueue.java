/*
 * Programmer: Jose A. Valdivia Rojas
 * Class: CMSC204.CRN21479
 * Instructor: Robert Alexander
 * Description: Create a generic queue class called NotationQueue.  
 * NotationQueue will implement the QueueInterface given you. 
 * You will be creating NotationQueue from scratch 
 * (do not use an internal object derived from the Queue interface from the Java API, from java.util)
 * Due: 09/29/2021
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 *Print your Name here: Jose A. Valdivia Rojas
 */
import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

	int capacity, nodes, front, rear;
	ArrayList<T> datos;
	
	// Default Constructor
	public MyQueue() {
		
		capacity = 5;
		nodes = 0;
		front = 0;
		rear = 0;
		datos = new ArrayList<T>(capacity);
	}
	
	// Constructor
	public MyQueue(int capacity) {
		
		this.capacity = capacity;
		nodes = 0;
		front = 0;
		rear = 0;
		datos = new ArrayList<T>(capacity);
	}
	
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if (nodes == 0)
			return true;
		else
			return false;
	}

	/**
	 * Determines of the Queue is empty
	 * @return
	 */
	@Override
	public boolean isFull() {
		if (nodes == capacity)
			return true;
		else 
			return false;
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
//	@Override
	public T dequeue() throws QueueUnderflowException {
		if (nodes == 0)
		throw new QueueUnderflowException("The Queue is empty");
		else {
			front = (front + 1) % capacity;
			nodes = nodes - 1;
			T dequeue = datos.get(0);
			datos.remove(0);
			return dequeue;
		}
	}

	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return nodes;
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (nodes == capacity)
			throw new QueueOverflowException("The Queue is full");
	
		else {
			datos.add(e);
			nodes ++;
			rear = (rear + 1) % capacity;
			return true;
		}
	}
	
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String aString = "";
		ArrayList<T> aCopyofDatos = datos;
		
		for (int i=0; i<aCopyofDatos.size(); i++)
		{
			
			aString += aCopyofDatos.get(i).toString();
		}
		
		// Elimina los espacios iniciales y finales string.trim()
		aString = aString.trim();
		return aString;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String aString = "";
		ArrayList<T> aCopyofDatos = datos;
		String prefix = "";
		for (int i=0; i<aCopyofDatos.size(); i++)
		{
			
			aString += prefix + aCopyofDatos.get(i).toString();
			prefix = delimiter;
		}
		
		// Elimina los espacios iniciales y finales string.trim()
		aString = aString.trim();
		return aString;
	}
	
	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  */
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyListDatos = new ArrayList<>();
		copyListDatos.addAll(datos);

		for (int i = 0 ; i < list.size(); i++) 
		{
			// add each object of list to this copyDatos list
			copyListDatos.add(list.get(i));
			
			
			try {
				
				enqueue(copyListDatos.get(i));
				
			} catch (QueueOverflowException e){ 
				e.printStackTrace();}			
		}
	}
	
	
	
	
}
