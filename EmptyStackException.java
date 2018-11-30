/**
 * Jerrold Windman 
 * (109070054)
 * Homework #3
 * CSE 214 Spring 2017 Recitation 14
 * TAs: Tayo Amuneke, Yiwen Wang
 * Grading TA: Anand Aiyer
 *
 */


/**
 * An exception class which is thrown when the user attempts to manipulate
 * an empty stack. 
 *
 */
public class EmptyStackException extends Exception {
	/**
	 * Default constructor, posts a message notifying the user of the error they
	 * performing
	 */
	public EmptyStackException(){
		super("Operatin cannot be performed, you have reached the end of the list"
				+ " or the list is empty");
	}
	
	/**
	 *  A constructor that allows someone using the class to
	 * display their own message when an exception is thrown.
	 * @param message
	 * The message to be displayed when the exception is thrown
	 * 
	 */
	public EmptyStackException(String message){
		super(message);
	}
	
}
