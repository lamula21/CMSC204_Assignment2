/*
 * Programmer: Jose A. Valdivia Rojas
 * Class: CMSC204.CRN21479
 * Instructor: Robert Alexander
 * Description: Create a generic stack class called NotationStack. 
 * NotationStack will implement the Stack Interface given you. 
 * You will be creating NotationStack from scratch (do not use an internal object of the Stack class from the Java API, java.util)
 * Due: 09/29/2021
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 *Print your Name here: Jose A. Valdivia Rojas
 */
import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {

	int capacity, nodes, front, rear;
	ArrayList<T> datos;
	
	// Default Constructor
	public MyStack() {
		
		capacity = 5;
		nodes = 0;
		front = 0;
		rear = 0;
		datos = new ArrayList<T>(capacity);
	}
	
	// Constructor
	public MyStack(int capacity) {
		
		this.capacity = capacity;
		nodes = 0;
		front = 0;
		rear = 0;
		datos = new ArrayList<T>(capacity);
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if (nodes == 0)
			return true;
		else
			return false;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		if (nodes == capacity)
			return true;
		else 
			return false;
	}
	

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (nodes == 0)
		throw new StackUnderflowException("The Stack is empty");
		else {
			front = (front + 1) % capacity;
			nodes = nodes - 1;
			T pop = datos.get(datos.size()-1);
			datos.remove(datos.size()-1);
			return pop;
		}
	}
	
	
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (nodes == 0) {
			throw new StackUnderflowException("The Stack is empty");
		}
		else {
			T top = datos.get(datos.size()-1);
			return top;
		}
	}

	
	
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return nodes;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (nodes == capacity)
			throw new StackOverflowException("The Stack is full");
	
		else {
			datos.add(e);
			nodes ++;
			rear = (rear + 1) % capacity;
			return true;
		}
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
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
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
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
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
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
				push(copyListDatos.get(i));
				
			} catch (StackOverflowException e){ 
				e.printStackTrace();}			
		}
	}
	
	
	
	
	
}
