/**
 * Program Name:MA_Project_Methods.java
 * Purpose: Put Something Useful Here, Dummy
 * Coder: Mohamad Arafat
 * Date: Mar 18, 2023
 */
import java.util.Scanner;
public class MA_Project_Methods
{

	/**
	 * Method name: createPlayerArray(). <br>
	 * purpose: a public class method that will take the number of players, validate it then ask the user to write the players names. <br>
	 * Accepts: an array of type string	<br>
	 * Returns: an array of type string	<br>
	 * Coder:	MA	<br>
	 * Date:	18/03/2023	<br>
	 */
	
	
	public static String[] createPlayerArray(String namesArray[]) {
		Scanner input = new Scanner(System.in);
	
		System.out.print("Enter the number of players (no less than 2 and no more than 6): ");
		int playersNum = input.nextInt();
		
		boolean flagVar = true;
		
		while(flagVar) {
			if(playersNum < 2 || playersNum > 6) {
				System.out.println("\nYou've entered an invalid number. Please try again!");
				System.out.print("Enter the number of players (no less than 2 and no more than 6): ");
				playersNum = input.nextInt();
			}//end if
			else {
				
				namesArray = new String[playersNum];
				for(int i = 0; i < namesArray.length; i++) {
					System.out.print("Enter first name of player# " + (i + 1) + " and press ENTER:");
					namesArray[i]= input.next();			
				}//end for	
				flagVar = false;
			}//end else
			
		}//end while
		
		return namesArray;
	}//end method
	
	/**
	 * Method name: createBankRollArray(). <br>
	 * purpose: a public class method that will creates an array of type int of the same length as the player array. 
	 * A value of 100 is placed in each element and the method returns the array <br>
	 * Accepts: an array of type int	<br>
	 * Returns: an array of type int	<br>
	 * Coder:	MA	<br>
	 * Date:	29/03/2023	<br>
	 */
	
	public static int[] createBankRollArray(int[] array) {
		int[] moneyArray = new int[array.length];
		for(int i = 0; i < moneyArray.length; i++) {
			
			array[i] = 100;
		}//end for
		
		return array;
	}//end method
	
	/**
	 * Method name: showRules() <br>
	 * purpose: a public class method that will print the rules of the game to the screen <br> 
	 * Accepts: a char	<br>
	 * Returns: Nothing! void method	<br>
	 * Coder:	MA	<br>
	 * Date:	29/03/2023	<br>
	 */
	
	public static void showRules(char rules) {
		
		
		if(rules == 'Y') {
			
			System.out.println("----------------------------- Rules -----------------------------");
			System.out.println("\n\nHere are the basic rules of the game: ");
			System.out.println("\nOne round of play with one person shooting the dice is known as 'a pass'.");
			System.out.println("\n1)The person with the dice is 'the shooter'. The shooter decides how much they want to bet, which is called 'the action'.");
			System.out.println("\n2)The shooter has to make a minimum bet of 10 dollars, and the bet amount must be a multiple of $10, "
					+ "\nbut they can bet up to the maximum amount in their bank roll.");
			System.out.println("\n3)The other players are invited to 'take a piece of the action', "
					+ "which means they can bet an amount up to but not greater than the amount of the action."
					+ " \nFor example, if the 'action amount' bet was $50, then one player could 'take $30 of the action, "
					+ "\na second player could 'take $10 of the action, and a third player could take the final $10 of the action."
					+ "\nThe action of $50 would then be 'covered' and no other players in the game would be able to lay a bet on this round."
					+ "\nWhen all bets are placed, the shooter rolls the dice on the 'come out' roll. Here are the possible outcomes:");
			System.out.println("\n1)IF the shooter rolls a seven or eleven, then they have rolled 'a natural' and they win their bet. "
					+ "\nThe bets of the otherplayers will be given to the shooter.");
			System.out.println("\n2)IF the shooter rolls a two ('snake-eyes), a three ('ace-deuce'), or a twelve ('box-cars') then the shooter 'craps out' (loses) "
					+ "\nand the amount of his action is split up and given to the other players according to the amount they bet.");
			System.out.println("\n3)IF the shooter rolls any other number besides 2,3,7,11, or 12, then that number becomes the shooter's point'. "
					+ "\nThe shooter now rolls the dice again until they either roll their point again, in which case they have 'come' and won the pass, "
					+ "\nor they roll a seven,in which case they 'don't come' (lose) because they have 'sevened out'.");
			System.out.println("\n4)After a pass is completed, the shooter may choose to continue to roll the dice ('make another pass') or they can pass "
					+ "\nthe dice to the next player ('pass on the bones').");
			System.out.println("\n5)If a player loses all of their money, they are out of the game ('cleaned out')."
					+ "\nThe game ends when one player has won all of the money in the game from the other players ('cleaned house').\n\n");
			
		}
		else if(rules == 'N') {
			
			System.out.println("\nOK, you know the rules...let's play some Craps!");
		}
		else {
			System.out.println("\nInvalid answer. The game will start without explaining the rules.");
		}//end else
		
	}//end method
	
	/**
	 * Method name: validateShooterBet() <br>
	 * purpose: a public class method that will validate that the amount being bet by the shooter meets three conditions: the bet has a minimum value of $10, 
	 * 					the bet must be an exact multiple of $10, and the maximum value of the bet does not exceed the shooter’s bank balance amount <br> 
	 * Accepts: three integers and one int type array	<br>
	 * Returns: a boolean	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static boolean validateShooterBet(int shooterID, int betAmount, int[] availableCredit, int minimumBet) {
		Scanner input = new Scanner(System.in);
		minimumBet = 10;

			if(betAmount >= minimumBet && betAmount % 10 == 0 && betAmount <= availableCredit[shooterID]) {
				
				System.out.println(shooterID + " " + betAmount + " " + availableCredit[shooterID] + " " + minimumBet);
				return true;
				
			}//end if
			else {
				System.out.print("Your bet can't be less than $10, more than $" + availableCredit[shooterID] + " (your available credit) or not a multiple of $10, Try again: ");
				return false;
				
			}//end else
			
	}//end method
	
	/**
	 * Method name: validateOpponentBet() <br>
	 * purpose: a public class method that will check if the bets made by the other players meet the following conditions: the bet has a 
	 * minimum value of $10, the bet must be an exact multiple of 10, the maximum value of the bet does not exceed the player’s 
	 * bank balance amount, AND, the bet cannot exceed whatever the REMAINING ACTION AMOUNT <br> 
	 * Accepts: four integers and one integer array	<br>
	 * Returns: a boolean	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static boolean validateOpponentBet(int opponentID, int betAmount, int[] availableCreditArray, int minimumBet, int remainingActionAmount) {
		Scanner input = new Scanner(System.in);
		//minimumBet = 10;
		
		while(true) {
			
			if(betAmount >= minimumBet && betAmount % 10 == 0 && betAmount <= availableCreditArray[opponentID] && remainingActionAmount > 0 && remainingActionAmount >= betAmount) {
				
					System.out.println(opponentID + " " + betAmount + " " + availableCreditArray[opponentID] + " " + minimumBet + " " + remainingActionAmount);
//					if(remainingActionAmount == 0) {
//						
//						System.out.println("Action has been covered, No more bets allowed");
//						return true;
//					}//end inner if
//					betAmount = input.nextInt();
					return true;
					
			}//end outer if
			
			else {
				System.out.print("Your bet can't be less than $10, more than $" + availableCreditArray[opponentID] + " (your available credit), must be a multiple of $10 and shouldn't be "
						+ "more than the shooter's remaining bet amount, Try again.");
//				betAmount = input.nextInt();
				return false;
			}//end else
			
		}//end while
		
		
	}//end method
	
	/**
	 * Method name: rollDice() <br>
	 * purpose: a public class method that will simulate the rolling of two dice and will return a random value from 2 to 12 inclusive<br> 
	 * Accepts: an int value	<br>
	 * Returns: an int value	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static int rollDice(int diceRolling) {
		
		diceRolling = (int)(Math.random() * 12 - 2 + 1) + 2;
		
		
		return diceRolling;
	}//end method
	
	/**
	 * Method name: adjustBankBalances() <br>
	 * purpose: a public class method that will return the bankRollArray after adjusting the bank balances of each player depending 
	 * 					on whether they won or lost on the round <br> 
	 * Accepts: 2 arrays, 2 ints and a boolean	<br>
	 * Returns: an int type array	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */

	public static int[] adjustBankBalances(int[] betArray, int[] bankArray, int actionAmount, int shooter, boolean isWinner) {
		
		for(int i = 1; i < bankArray.length; i++) {
			
			if(isWinner == true) {
				
						bankArray[shooter] += betArray[shooter] + betArray[i];
						bankArray[i] -= betArray[i];
			}
			else {
				
					bankArray[shooter] -= betArray[i];
					bankArray[i] += betArray[i];
				
			}//end else
		}//end for
		
		return bankArray;
	}//end method
	
	/**
	 * Method name: getNextShooter() <br>
	 * purpose: a public class method that will return an int value that corresponds to the index number of a name in the playerArray  <br> 
	 * Accepts: an int and an array of type int	<br>
	 * Returns: an int	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static int getNextShooter(int indexNum, int[]array) {
		
		
		while(true) {
			
			if(indexNum == array.length) {
				indexNum = 0;
				continue;
			}
			else if(array[indexNum] == 0) {
				indexNum++;
				continue;
			}
			break;
		}//end while
		
		return indexNum;
		
	}//end Method
	
	/**
	 * Method name: printPlayerBankBalances() <br>
	 * purpose: a public class method that will print out the name of each player and their current bank balance <br> 
	 * Accepts: int type array and a string type array	<br>
	 * Returns: Nothing! void method	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static void printPlayerBankBalances(String[] playerArray, int[] bank) {
		
		for(int i = 0; i < playerArray.length; i++) {
			
			System.out.println(playerArray[i] + " has: $" + bank[i]);
		}//end for
	}//end method
	
	/**
	 * Method name: checkForWinnner() <br>
	 * purpose: a public class method that will check if one of the players collected all the money in the game by multiplying the number of 
	 * 					players by 100 to determine if he won <br> 
	 * Accepts: an int type array, and an int	<br>
	 * Returns: a boolean indicating if the player won <br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static boolean checkForWinnner(int[] array, int total) {
		
		
		boolean isWinner = true;
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] == total) {
				return isWinner;
			}
		}//end for
		isWinner = false;
		return isWinner;
		
	}//end method
	
	/**
	 * Method name:  identifyWinner()<br>
	 * purpose: a public class method that will traverse the bankRollArray to find the index number of the element that has a value matching the 
	 * 					total amount of money in the game <br> 
	 * Accepts: a String array, an int array and an int	<br>
	 * Returns: a String	<br>
	 * Coder:	MA	<br>
	 * Date:	02/04/2023	<br>
	 */
	
	public static String identifyWinner(String[] playersArray, int[] bankArray, int total) {
		
		int winningIndex = 0;
		for(int i = 0; i < bankArray.length; i++) {
			
			if(total == bankArray[i]) {
				winningIndex = i;
			}
		}//end for
		
		return playersArray[winningIndex];
	}//end method
	
	/**
	 * Method name: shiftArrayValues() --------NOTE: THIS IS AN EXTRA METHOD ADDED BY THE CODER <br>
	 * purpose: a public class method that will shift the first element in an array to the last index <br> 
	 * Accepts: int array type	<br>
	 * Returns: int array type	<br>
	 * Coder:	MA	<br>
	 * Date:	07/04/2023	<br>
	 */
	
	public static int[] shiftArrayValues(int[]array) {
		
		
		int temp = array[0];

    for (int i = 1; i < array.length; i++) {
      array[i - 1] = array[i];
    }
    array[array.length - 1] = temp;
    
    return array;
	}//end method
	
	/**
	 * Method name: shiftArrayValues() OVERLOADED FOR STRING TYPE------- NOTE: THIS IS AN EXTRA METHOD ADDED BY THE CODER  <br>
	 * purpose: a public class method that will shift the first element in an array to the last index <br> 
	 * Accepts: String array type	<br>
	 * Returns: String array type	<br>
	 * Coder:	MA	<br>
	 * Date:	07/04/2023	<br>
	 */
	
	public static String[] shiftArrayValues(String[]array) {
		
		
		String temp = array[0];

    for (int i = 1; i < array.length; i++) {
      array[i - 1] = array[i];
    }
    
    array[array.length - 1] = temp;
    
    return array;
	}//end method
	
}//end class

