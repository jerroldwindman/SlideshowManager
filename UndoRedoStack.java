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
 * A stack class which is used to create an Undo Stack and a Redo Stack for the
 * slideshow. Objects of type ActionNode, which wrap ActionCommands and ActionTypes
 * are pushed onto the top of the stack, and removed from the position.
 * @param head
 * A reference to the head of the linked list, which is also the top of the stack
 * @param cursor
 * A reference to a particular node of the stack, which can be used for traversal
 * @param num
 * An integer which describes the amount of nodes in a particular stack.
 */
public class UndoRedoStack {
	private ActionNode head;
	private ActionNode cursor;
	private int num = 0;
	
	
	/**
	 * A getter method which returns the number of nodes in a particular stack
	 * @return
	 * The number of nodes in  a stack.
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * A getter method which returns the ActionNode that the cursor is currently referencing.
	 * @return
	 * The ActionNode being referenced by cursor.
	 */
	public ActionNode getCursor(){
		return cursor;
	}
	
	/**
	 * A method which sets the cursor refernce to the node being referenced by the head reference
	 * of the stack. Points the cursor at the top of the stack.
	 * <dt><b>Postconditions</b><dd>
	 * The cursor now references the head or top of the stack.
	 */
	public void resetCursorToHead(){
		cursor = head;
	}
	
	/**
	 * A method which clears the stack of all nodes by setting the references to the
	 * stack to null and the number of nodes in the stack to 0.
	 * <dt><b>Postconditions</b><dd>
	 * The stack is now empty.
	 */
	public void clearStack(){
		head = null;
		cursor = null;
		num = 0;
	}
	
	/**
	 * A method to push an ActionCommand onto the top of the stack.
	 * The ActionCommand is first wrapped in a ActionNode object and then
	 * becomes the new node referenced by head and cursor.
	 * @param a
	 * The ActionCommand to be pushed on top of the stack.
	 * <dt><b>Postconditions</b><dd>
	 * The head and the cursor now reference the new node. The int variable
	 * which holds the number of nodes in the stack is increased by one.
	 */
	public void push(ActionCommand a){
		ActionNode temp = new ActionNode(a);
		if(head == null){
			head = temp;
			cursor = head;
		}
		else{
			temp.setNext(head);
			head = temp;
		}
		num++;
	}
	
	/**
	 * A method to change which node the cursor is referencing by pointing it to the
	 * node referenced by next of the node currently being pointed to by the cursor.
	 * <dt><b>Preconditions</b><dd>
	 * The next reference of the node currently being referenced by the cursor is not null.
	 * <dt><b>Postconditions</b><dd>
	 * The cursor now references the next node in the stack.
	 * @throws EmptyStackException
	 * The cursor cannot be moved any farther forward or the list is empty.
	 */
	public void cursorForward() throws EmptyStackException{
		if(cursor.getNext() != null){
			cursor = cursor.getNext();
		}
		else{
			throw new EmptyStackException();
		}
	}
	
	/**
	 * A method to remove the node currently being referenced by head, the top
	 * of the stack.
	 * <dt><b>Preconditions</b><dd>
	 * The list is not empty.
	 * <dt><b>Postconditions</b><dd>
	 * The cursor now references the next node of what was formally the head node. If the
	 * stack had only one node, then the head now references null. The 
	 * int variable describing the number of nodes in the stack is decreased by one.
	 * @throws EmptyStackException
	 * If the stack is empty, then trying to pop a node from the stack will throw the exception.
	 * 
	 */
	public ActionCommand pop() throws EmptyStackException{
		if(head != null){
			ActionCommand temp = head.getData();
			head = head.getNext();
			num--;
			return temp;
		}
		else{
			throw new EmptyStackException();
		}
	}
	
	/**
	 * A method to view the node currently referenced by head, the top of the stack,
	 * without removing the node itself, such as with the pop command.
	 * <dt><b>Preconditions</b><dd>
	 * The list is not empty.
	 * @return
	 * The data of type ActionCommand currently being referenced by the head reference.
	 * @throws EmptyStackException
	 * If the list is empty, the exception is thrown.
	 */
	public ActionCommand peek() throws EmptyStackException{
		if(head != null){
			return head.getData();
		}
		else{
			throw new EmptyStackException();
		}
	}
	
	/**
	 * A method to check if the stack is empty.
	 * @return
	 * A boolean true if the stack is empty, and false if the stack is not empty.
	 */
	public boolean isEmpty(){
		return (head == null);
	}
	
}
