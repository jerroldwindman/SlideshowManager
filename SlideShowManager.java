/**
 * Jerrold Windman 
 * (109070054)
 * Homework #3
 * CSE 214 Spring 2017 Recitation 14
 * TAs: Tayo Amuneke, Yiwen Wang
 * Grading TA: Anand Aiyer
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;




public class SlideShowManager {
	public static void main(String[] args) throws EmptyStackException{
		String operator;
		boolean running =true;
		
		UndoRedoStack commandStack = new UndoRedoStack();
		UndoRedoStack redoStack = new UndoRedoStack();
		ArrayList slideshow = new ArrayList();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to jSlide! Make your own Slideshow!\n");
		System.out.println("Menu:");
		System.out.println("A) Add a photo");
		System.out.println("R) Remove a photo");
		System.out.println("S) Swap photos");
		System.out.println("M) Move photos");
		System.out.println("P) Print");
		System.out.println("Z) Undo");
		System.out.println("Y) Redo");
		System.out.println("Q) Quit");
		
		
		while(running){
			System.out.println("Please select an option: ");
			operator = input.nextLine();
			
			if(operator.equalsIgnoreCase("A")){
				ActionCommand tempComm = new ActionCommand(ActionType.ADD);
				
				tempComm.perform(slideshow);
				commandStack.push(tempComm);
				if(tempComm.fix){
					commandStack.pop();
					tempComm.fix = false;
					continue;
				}
				redoStack.clearStack();
			}
			else if(operator.equalsIgnoreCase("R")){
				ActionCommand tempComm = new ActionCommand(ActionType.REMOVE);
				
				tempComm.perform(slideshow);
		
				commandStack.push(tempComm);
				if(tempComm.fix){
					commandStack.pop();
					tempComm.fix = false;
					continue;
				}
				redoStack.clearStack();
			}
			else if(operator.equalsIgnoreCase("S")){
				ActionCommand tempComm = new ActionCommand(ActionType.SWAP);
				commandStack.push(tempComm);
				tempComm.perform(slideshow);
				if(tempComm.fix){
					commandStack.pop();
					tempComm.fix = false;
					continue;
				}
				redoStack.clearStack();
			}
			else if(operator.equalsIgnoreCase("M")){
				ActionCommand tempComm = new ActionCommand(ActionType.MOVE);
				commandStack.push(tempComm);
				tempComm.perform(slideshow);
				if(tempComm.fix){
					commandStack.pop();
					tempComm.fix = false;
					continue;
				}
				redoStack.clearStack();
				
			}
			else if(operator.equalsIgnoreCase("P")){
				System.out.println("Slideshow:");
				System.out.println("-----------------------------------------------");
				
				Iterator slideIt = slideshow.iterator();
				
				for(int i = 1; slideIt.hasNext(); i++){
					System.out.print(i + "." + slideIt.next() + ", ");
				}
				
				System.out.println("\n\nUndo Stack:");
				if(commandStack.isEmpty()){
					System.out.println("[Empty]");
				}
				else{
					commandStack.resetCursorToHead();
					for(int i = 0; i < commandStack.getNum() ; i++){
						if(commandStack.getCursor().getData().getType() == ActionType.ADD){	
							System.out.print(commandStack.getCursor().getData().getType().toString() + " ");
							System.out.print(commandStack.getCursor().getData().getPhoto() + " in position ");
							System.out.print(commandStack.getCursor().getData().getPositionOne() + "\n");
						}
						else if(commandStack.getCursor().getData().getType() == ActionType.SWAP){
							System.out.print(commandStack.getCursor().getData().getType().toString());
							System.out.print(" position " + commandStack.getCursor().getData().getPositionOne());
							System.out.print(" and position " + commandStack.getCursor().getData().getPositionTwo() + "\n");
						}
						else if(commandStack.getCursor().getData().getType() == ActionType.REMOVE){
							System.out.print(commandStack.getCursor().getData().getType().toString());
							System.out.print(" from position ");
							System.out.print(commandStack.getCursor().getData().getPositionOne() + "\n");
						}
						else if(commandStack.getCursor().getData().getType() == ActionType.MOVE){
							System.out.print(commandStack.getCursor().getData().getType().toString());
							System.out.print(" from position " + commandStack.getCursor().getData().getPositionOne());
							System.out.print(" to position " + commandStack.getCursor().getData().getPositionTwo() + "\n");
						}
						try{
						commandStack.cursorForward();
						}catch(EmptyStackException e){
							continue;
						}
					}
				}
				System.out.println("\nRedo Stack:");
				if(redoStack.isEmpty()){
					System.out.println("[Empty]");
				}
				else{
					redoStack.resetCursorToHead();
					for(int i = 0; i < redoStack.getNum() ; i++){
						if(redoStack.getCursor().getData().getType() == ActionType.ADD){	
							System.out.print(redoStack.getCursor().getData().getType().toString() + " ");
							System.out.print(redoStack.getCursor().getData().getPhoto() + " in position ");
							System.out.print(redoStack.getCursor().getData().getPositionOne() + "\n");
						}
						else if(redoStack.getCursor().getData().getType() == ActionType.SWAP){
							System.out.print(redoStack.getCursor().getData().getType().toString());
							System.out.print(" position " + redoStack.getCursor().getData().getPositionOne());
							System.out.print(" and position " + redoStack.getCursor().getData().getPositionTwo() + "\n");
						}
						else if(redoStack.getCursor().getData().getType() == ActionType.REMOVE){
							System.out.print(redoStack.getCursor().getData().getType().toString());
							System.out.print(" from position ");
							System.out.print(redoStack.getCursor().getData().getPositionOne() + "\n");
						}
						else if(redoStack.getCursor().getData().getType() == ActionType.MOVE){
							System.out.print(redoStack.getCursor().getData().getType().toString());
							System.out.print(" from position " + redoStack.getCursor().getData().getPositionOne());
							System.out.print(" to position " + redoStack.getCursor().getData().getPositionTwo() + "\n");
						}
						try{
							redoStack.cursorForward();
						} catch(EmptyStackException e){
							continue;
						}
						
					}
				}
				
			}
			else if(operator.equalsIgnoreCase("Z")){
				try{
					ActionCommand temp = commandStack.pop();
					redoStack.push(temp);
					ActionCommand inverse = temp.getInverse();
					if(inverse.getType() == ActionType.REMOVE){
						slideshow.remove(inverse.getPositionOne() - 1);
						continue;
					}
					else if(inverse.getType() == ActionType.ADD){
						slideshow.add(inverse.getPositionOne() - 1, temp.getPhoto());
						
					}
					else if(inverse.getType() == ActionType.MOVE){
						Object photo = slideshow.remove(temp.getPositionTwo() - 1);
						slideshow.add(temp.getPositionOne() - 1, photo);
						
					}
					else if(inverse.getType() == ActionType.SWAP){
						if(inverse.getPositionOne() == inverse.getPositionTwo()){
							continue;
						}
						
						Object temp2 = slideshow.remove(inverse.getPositionOne() - 1);
						slideshow.add(inverse.getPositionOne() -1, slideshow.get(inverse.getPositionTwo() - 2));
						slideshow.remove(inverse.getPositionTwo()-1);
						slideshow.add(inverse.getPositionTwo() -1, temp2);
					}
				}catch(EmptyStackException e){
					System.out.println("No more commands to undo!");
					continue;
				}
				
			}
			else if(operator.equalsIgnoreCase("Y")){
				if(redoStack.isEmpty()){
					System.out.println("There is no action to redo!");
				}
				else{
					ActionCommand temp = redoStack.pop();
					if(temp.getType() == ActionType.ADD){
						slideshow.add(temp.getPositionOne() - 1, temp.getPhoto());
					}
					else if(temp.getType() == ActionType.REMOVE){
						slideshow.remove(temp.getPositionOne() - 1);
					}
					else if(temp.getType() == ActionType.MOVE){
						Object photo = slideshow.remove(temp.getPositionTwo() - 1);
						slideshow.add(temp.getPositionOne() - 1, photo);
					}
					else if(temp.getType() == ActionType.SWAP){
						if(temp.getPositionOne() == temp.getPositionTwo()){
							commandStack.push(temp);
							continue;
						}
						Object temp2 = slideshow.remove(temp.getPositionOne() - 1);
						slideshow.add(temp.getPositionOne() -1, slideshow.get(temp.getPositionTwo() - 2));
						slideshow.remove(temp.getPositionTwo()-1);
						slideshow.add(temp.getPositionTwo() -1, temp2);
					}
					commandStack.push(temp);
				}
			}
			else if(operator.equalsIgnoreCase("Q")){
				System.out.println("Thank you for using jSlide!");
				running = false;
			}
			else{
				System.out.println("Please enter a valid command!");
			}
			
		
		
		}
	}
}
