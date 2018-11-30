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
 * 
 * This class describes the ActionNode object, the nodes to be placed on the
 * Undo and Redo stacks. This class wraps the data contained in data object.
 * @param data
 * The data of type ActionCommand that describes each node
 * @param next
 * The reference to the next node in the stack. The reference is of type ActionNode.
 *
 */
public class ActionNode {
	private ActionCommand data;
	private ActionNode next;
	
	
	/**
	 * The constructor for the ActionNode class.
	 * @param newData
	 * The data of type ActionCommand to be wrapped by the ActionNode
	 * 
	 */
	public ActionNode(ActionCommand newData){
		setData(newData);
	}
	
	/**
	 * A method that returns the data contained in the ActionNode object.
	 * @return
	 * The data describing the ActionNode object
	 */
	public ActionCommand getData() {
		return data;
	}
	
	/**
	 * A method that sets the data of an ActionNode object.
	 * @param data
	 * The data to be wrapped in the ActionNode object.
	 */
	public void setData(ActionCommand data) {
		this.data = data;
	}

	/**
	 * A method that returns a reference to the next ActionNode in the stack.
	 * @return
	 * The reference to the next ActionNode in the stack of nodes.
	 */
	public ActionNode getNext() {
		return next;
	}
	
	/**
	 * A method that sets the reference to the next ActionNode in the stack.
	 * @param next
	 * The node to be connected to the current ActionNode.
	 */
	public void setNext(ActionNode next) {
		this.next = next;
	}
	
	
}
