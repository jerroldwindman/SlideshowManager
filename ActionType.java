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
 * The ActionType enum class creates several constants to represent
 * the different types of commands that can be performed on the slideshow.
 * These types describe the nodes to be pushed onto to Undo Stack and
 * the Redo Stack.
 *
 */
public enum ActionType {
	ADD, REMOVE, MOVE, SWAP
}
