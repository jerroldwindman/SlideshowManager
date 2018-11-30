/**
 * Jerrold Windman 
 * (109070054)
 * Homework #3
 * CSE 214 Spring 2017 Recitation 14
 * TAs: Tayo Amuneke, Yiwen Wang
 * Grading TA: Anand Aiyer
 *
 */


import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;



/**
 * This class describes the ActionCommand object, which is the data that describes
 * the command pushed onto the stack, and the accessor and mutator methods for the data.
 * @param positionOne
 * The integer position in the slideshow to add or remove photos to or from. Also the source or
 * destination position for the swap or move commands.
 * @param positionTwo
 * The integer source or destination position for the swap or move commands.
 * @param photo
 * The string describing the name of the file that contains the photo to be added to the slideshow.
 * @param fix
 * A static boolean variable, when set to true, notifies the SlideShowManager driver class to
 * pop from the stack the last ActionNode pushed.
 * @param type
 * The ActionType which describes what kind of command the ActionNode wraps.
 * @param inverse
 * The ActionType opposite to the ActionType contained in the type parameter.
 * The inverse of ADD is REMOVE, the inverse of REMOVE is ADD, and SWAP and MOVE are inverses of one themselves.
 */
public class ActionCommand {
	private int positionOne;
	private int positionTwo;
	private String photo;
	static boolean fix = false;
	final ActionType type;
	
	Scanner input = new Scanner(System.in);
	private ActionCommand inverse;
	
	
	/**
	 * The constructor for the ActionCommand object.
	 * @param newType
	 * The ActionType that describes the ActionNode object that wraps this class.
	 */
	public ActionCommand(ActionType newType){
		type = newType;
	}
	
	/**
	 * The getter method for the positionOne variable
	 * @return
	 * The positionOne variable
	 */
	public int getPositionOne(){
		return positionOne;
	}
	
	/**
	 * The getter method for the positionTwo variable
	 * @return
	 * The positionTwo variable
	 */
	public int getPositionTwo(){
		return positionTwo;
	}
	
	/**
	 * The getter method for the photo variable
	 * @return
	 * The photo variable
	 */
	public String getPhoto(){
		return photo;
	}
	
	/**
	 * The getter method for the type variable
	 * @return
	 * The type variable
	 */
	public ActionType getType(){
		return type;
	}
	
	/**
	 * The setter method for the positionOne variable
	 * @param posOne
	 * The variable to be set as positionOne
	 */
	public void setPositionOne(int posOne){
		positionOne = posOne;
	}
	
	/**
	 * The setter method for the positionTwo variable
	 * @param posTwo
	 * The variable to be set as positionTwo
	 */
	public void setPositionTwo(int posTwo){
		positionTwo = posTwo;
	}
	
	/**
	 * The setter method for the photo variable
	 * @param photo
	 * The variable to be set as photo
	 */
	public void setPhoto(String photo){
		this.photo = photo;
	}
	
	/**
	 * Depending on the ActionType of the ActionCommand, the perform method will either ADD, REMOVE,
	 * SWAP, or MOVE photos to and around the slideshow ArrayList.
	 * @param slideshow
	 * An ArrayList object which contains the photos to be manipulated.
	 */
	public void perform(ArrayList<String> slideshow){
		if(type == ActionType.ADD){
			try{
				System.out.println("Please enter the photo file name:");
				String fileName = input.nextLine();
				this.setPhoto(fileName);
				
				System.out.println("Please enter the position:");
					try{
						int positionOne = input.nextInt();
						this.setPositionOne(positionOne);
					}catch(InputMismatchException e){
						System.out.println("Please enter a valid number!");
						fix = true;
						return;
					}
				
				slideshow.add(positionOne -1, fileName);
			}catch(IndexOutOfBoundsException e){
				System.out.println("Erroneous position, no spaces allowed!");
				fix = true;
				return;
			}
		}
		else if(type == ActionType.REMOVE){
			try{
				System.out.println("Please enter the position of the photo to remove:");
				positionOne = input.nextInt();
				this.setPositionOne(positionOne);
				
			
				if(positionOne > slideshow.size()){
					System.out.println("Erroneous position!");
					fix = true;
					return;
				}
				
				this.setPhoto(slideshow.get(positionOne - 1).toString());
				
				slideshow.remove(positionOne - 1);	
			}catch(IndexOutOfBoundsException e){
				System.out.println("Erroneous position!");
				fix = true;
				return;
			}catch(InputMismatchException e){
				System.out.println("Please enter a number!");
				fix = true;
				return;
			}
		}
		else if(type == ActionType.SWAP){
			try{
				System.out.println("Please enter the first position: ");
				positionOne = input.nextInt();
				this.setPositionOne(positionOne);
				
				System.out.println("Please enter the second position: ");
				positionTwo = input.nextInt();
				this.setPositionTwo(positionTwo);
				
				if(positionTwo < positionOne){
					int temp = positionOne;
					positionOne = positionTwo;
					positionTwo = temp;
				}
				
				if((positionOne > slideshow.size() || positionTwo > slideshow.size()) ||
						((positionOne == 0) || (positionTwo == 0))){
					System.out.println("Erroneous position!");
					fix = true;
					return;
				}
				
				if(positionOne == positionTwo){
					return;
				}
				
				photo = slideshow.remove(positionOne - 1);
				slideshow.add(positionOne -1, slideshow.get(positionTwo - 2));
				slideshow.remove(positionTwo-1);
				slideshow.add(positionTwo -1, photo);
			}catch(IndexOutOfBoundsException e){
				System.out.println("Erroneous position!");
				fix = true;
				return;
			}catch(InputMismatchException e){
				System.out.println("Please enter a number!");
				fix = true;
				return;
			}
			
			
		}
		else if(type == ActionType.MOVE){
			try{
				System.out.println("Please enter the source position: ");
				positionOne = input.nextInt();
				this.setPositionOne(positionOne);
				System.out.println("Please enter the destination position:");
				positionTwo = input.nextInt();
				this.setPositionTwo(positionTwo);
				
				if((positionOne > slideshow.size() || positionTwo > slideshow.size()) || ((positionOne == 0) || (positionTwo == 0))){
					System.out.println("Erroneous position!");
					fix = true;
					return;
				}
				
				photo = slideshow.remove(positionOne - 1);
				slideshow.add(positionTwo - 1, photo);
			}catch(IndexOutOfBoundsException e){
				System.out.println("Erroneous position!");
				fix = true;
				return;
			}catch(InputMismatchException e){
				System.out.println("Please enter a number!");
				fix = true;
				return;
			}
			
		}
		
	}
	
	/**
	 * A method that sets the inverse variable of the ActionCommand object.
	 * @return
	 * The inverse variable of type ActionType of the ActionCommand object
	 */
	public ActionCommand getInverse(){
		if(this.getType() == ActionType.ADD){
			inverse = new ActionCommand(ActionType.REMOVE);
			inverse.setPositionOne(this.getPositionOne());
			inverse.setPhoto(this.getPhoto());
			
		}
		else if(this.getType() == ActionType.REMOVE){
			inverse = new ActionCommand(ActionType.ADD);
			inverse.setPositionOne(this.getPositionOne());
			inverse.setPhoto(this.getPhoto());
			
		}
		else if(this.getType() == ActionType.MOVE){
			inverse = new ActionCommand(ActionType.MOVE);
			inverse.setPositionTwo(this.positionOne);
			inverse.setPositionOne(this.positionTwo);
			inverse.setPhoto(this.getPhoto());
			
		}
		else if(this.getType() == ActionType.SWAP){
			inverse = new ActionCommand(ActionType.SWAP);
			inverse.setPositionTwo(this.getPositionTwo());
			inverse.setPositionOne(this.getPositionOne());
			inverse.setPhoto(this.getPhoto());
			
		}
		return inverse;
	}
	
	
	
}
