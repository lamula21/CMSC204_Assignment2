/*
 * Programmer: Jose A. Valdivia Rojas
 * Class: CMSC204.CRN21479
 * Instructor: Robert Alexander
 * Description: You will be creating a utility class that converts an infix expression 
 * to a postfix expression, a postfix expression to an infix expression and evaluates 
 * a postfix expression.
 * Due: 09/29/2021
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 *Print your Name here: Jose A. Valdivia Rojas
 */

/**
 * Date: 09/29/2021
 * This is the Utility Class Notation
 * The Notation class will have a method infixToPostfix to convert infix notation 
 * to postfix notation that will take in a string and return a string, 
 * a method postfixToInfix to convert postfix notation to infix notation 
 * that will take in a string and return a string, and a method to evaluatePostfix 
 * to evaluate the postfix expression. It will take in a string and return a double.
 * @author Jose A. Valdivia
 */
public class Notation extends Object {

	/**
	 * Establish the presedence between '+' '-' '*' '/'
	 * if it is another operator or value, return -1
	 * @param c
	 * @return an int
	 */
	static int precedence(char c){
		
		switch ( c ) {
			
		case '+':
		case '-':
			return 1;
			
		case '*':
		case '/':
			return 2;
		}
		return -1;
	}
	
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException, QueueOverflowException, StackOverflowException, StackUnderflowException{

		MyStack<Character> operatorStack = new MyStack<Character>(30);
		MyQueue<Character> postfixQueue = new MyQueue<Character>(30);


		int rightParen = 0;
		int leftParen = 0;
		
		for( int i=0; i < infix.length(); i++) {

			char nextDigit = infix.charAt(i);


			switch( nextDigit ) {

				// If the Current character is a WhiteSpace
				case ' ': break;
				
				// If the Current character is a digit (Operand).
				case '0': case '1': case '2': case '3': case '4': 
				case '5': case '6': case '7':case '8':case '9': 
					postfixQueue.enqueue(nextDigit) ;
					break;
					
				// If the Current character is a Open Parentheses.
				case '(': 
					leftParen ++;
					operatorStack.push(nextDigit);
					break;
					
				// If the Current character is a Operator.
				case '+': case'-': case'*': case'/': 
					while (!operatorStack.isEmpty() && precedence(nextDigit) <= precedence(operatorStack.top()) ) 
					{
						char operator = operatorStack.top();
						postfixQueue.enqueue(operator);
					
					}
					operatorStack.push(nextDigit);
					break;
					
				// If the Current character is a Close Parentheses.
				case ')':
					rightParen++;
					while(!operatorStack.isEmpty() && operatorStack.top() != '(') 
					{
						char topOperator = operatorStack.pop();
						postfixQueue.enqueue(topOperator);

					}

					if ( operatorStack.size() > 1 && operatorStack.top() == '(' )
						operatorStack.pop();
					break;
			}
		} 

		if ( leftParen != rightParen)
			throw new InvalidNotationFormatException ("No left parenthesis");
		
		
		while(!operatorStack.isEmpty()) 
		{

			if (operatorStack.top() == '(')
				operatorStack.pop();
			else {
				char top_Operator = operatorStack.pop();
				postfixQueue.enqueue( top_Operator);
			}
		}

		return postfixQueue.toString();
	}
	
	
	
	
	
	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException{
		
	
		MyStack<String> myStack = new MyStack<>(30);
		
		
		// Read the postfix expression from left to right
		for( int i=0; i < postfix.length(); i++) {

			char nextDigit = postfix.charAt(i);

			switch( nextDigit ) {

				// If the Current character is a WhiteSpace
				case ' ': break;
				
				// If the Current character is a digit (Operand).
				case '0': case '1': case '2': case '3': case '4': 
				case '5': case '6': case '7':case '8':case '9': 
					myStack.push(nextDigit + "");
					break;
				
					
				// If the Current character is an Operator.
				case '+': 	
				case '-': 	
				case '*': 
				case '/': 
					// If there are fewer than 2 values throw an error
					if (myStack.size() < 2)
						throw new InvalidNotationFormatException("Error");
					
					else {
					// Pop the top 2 values from the stack. 				
					String operand2 = myStack.top();
					myStack.pop();
					String operand1 = myStack.top();
					myStack.pop();
					
					
					// Create a string with 1st value and then the operator and then the 2nd value
					String finalExp = "(" + operand1 + nextDigit + operand2 + ")";
					
					// Push the resulting string back to the stack
					myStack.push(finalExp);}
					break;
			}
		}
		
		if (myStack.size() > 1)
			throw new InvalidNotationFormatException("Error");
		else {
			return myStack.top();}
		}
	
	
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException{
		
		int  operand2 = 0;
		int operand1 = 0;
		
		
		MyStack<Integer> myStack = new MyStack<>(30);
		
		for( int i=0; i < postfixExpr.length(); i++) {

			char nextDigit = postfixExpr.charAt(i);


			switch( nextDigit ) {

				// If the Current character is a WhiteSpace
				case ' ': break;
				
				// If the Current character is a digit (Operand).
				case '0': case '1': case '2': case '3': case '4': 
				case '5': case '6': case '7':case '8':case '9': 
					myStack.push(nextDigit - '0');
					break;
				
					
				// If the Current character is a Operator.
				case '+': 
					if (myStack.size() < 2)
						throw new InvalidNotationFormatException("Error");
					else {
					operand2 = myStack.pop();
					operand1 = myStack.pop();
					myStack.push(operand1 + operand2);}
					break;	
				case'-': 
					if (myStack.size() < 2)
						throw new InvalidNotationFormatException("Error");
					else {
					operand2 = myStack.pop();
					operand1 = myStack.pop();
					myStack.push(operand1 - operand2);}
					break;	
				case'*': 
					if (myStack.size() < 2)
						throw new InvalidNotationFormatException("Error");
					else {
					operand2 = myStack.pop();
					operand1 = myStack.pop();
					myStack.push(operand1 * operand2);}
					break;
				case'/': 
					if (myStack.size() < 2)
						throw new InvalidNotationFormatException("Error");
					else {
					operand2 = myStack.pop();
					operand1 = myStack.pop();
					myStack.push(operand1 / operand2);}
					break;
	
			}
		}
		
		if ( myStack.size() > 1)
			throw new InvalidNotationFormatException("Error");
		
		return myStack.pop();
		}

	
	
	/**
	 * Evaluates a infix expression from a string to a double
	 * @param infixExpr
	 * @return the evaluation of the infix expression as a double
	 * @throws InvalidNotationFormatException
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException, QueueOverflowException, StackOverflowException, StackUnderflowException{
		
		// Convert the infix expression to postfix by calling the method
		String postFix = convertInfixToPostfix(infixExpr);
		
		// Evalue postfix by calling the evaluate method
		double evaluatePost = evaluatePostfixExpression(postFix);
		
		return evaluatePost;
	}
}
