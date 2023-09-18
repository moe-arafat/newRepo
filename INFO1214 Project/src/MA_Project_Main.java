/**
 * Program Name:MA_Project_Main.java
 * Purpose: This program will run a game of Craps. it will give the user the ability to choose how many players, input their names and each player will be able to input his bet
 * Coder: Mohamad Arafat
 * Date: Mar 18, 2023
 * 
 * pseudocode
 * 
 * 1) Ask for the number of players and validate that it's no less than 2 and no more than 6.
 * 2) Ask each player to enter their name and store it inside an array called playerArray().
 * 3) Create another array of type int called the bankRollArray() which will have the same number of elements as the playerArray and 
 * 		grants each player $100 at the start.
 * 4) Create another array called betAmountArray() which has the same number of elements as the playerArray().
 * 5) Welcome the players to the casino and ask if they want to see the rules of the game. The first player can answer with a 'Y' or a 'N'.
 * 6) Write a statement where the shooter has to put a minimum bet of $10 and up to how much they have in their account. Bet can only be
 * 	  a multiple of $10, otherwise prompt the user to re-enter.
 * 7) Invite the other players to place a bet starting from the player after the shooter. the bets in total can be up to the shooter amount.
 * 8) Simulate the shooter’s Dice roll by randomly generating two numbers from 1 to 6 inclusive and adding them together so the total
 * 		will turn out 2 - 12.
 * 9) After the pass is completed, redistribute the money wagered to the appropriate players and then show each player’s current bank balance.
 * 10) Ask the shooter if they want to roll again or if they want to pass to the next player. The shooter can pick either option.
 * 11) When a player balance reaches zero '0', he won't be asked again to play or place bets.
 * 12) When only one player remains, announce him as the winner and display how much he earned.
 */
import java.util.Scanner;
public class MA_Project_Main
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		
		//1) Ask for the number of players and validate that it's no less than 2 and no more than 6.
		//2) Ask each player to enter their name and store it inside an array called playerArray().
		String[] playersArray = MA_Project_Methods.createPlayerArray(args);
		
		//3)  Create another array of type int called the bankRollArray() which will have the same number of elements as the playerArray and 
		// 		grants each player $100 at the start.
		int[] playersMoneyArray = new int[playersArray.length];
		
		MA_Project_Methods.createBankRollArray(playersMoneyArray);
		
		//4) Create another array called betAmountArray() which has the same number of elements as the playerArray().
		int[] betAmountArray = new int[playersArray.length];
		
		//5) Welcome the players to the arena and ask if they want to see the rules of the game. The first player can answer with a 'Y' or a 'N'.
		System.out.println("Welcome to Moe's Games Space! The game here is Craps, so we need to get some information about your party...\n\n");
		System.out.print("Would you like a brief explanation of the rules of the game? Enter 'Y' for yes, or 'N' for no: ");
		
		char rules = input.next().charAt(0);
		MA_Project_Methods.showRules(rules);
		
		//some useful variables
		final int MINIMUM_BET = 10;
		int shooterBetAmount;
		int playerBetAmount;
		int remainingBalance;
		int actionAmount = 0;
		boolean isWinner = true;
		boolean isValidShooter = true;
		boolean isValidOpponent = true;
		int shooterCounter = 0;
		int diceRoll = 0;
		int moneyTotal = playersMoneyArray.length * 100;
		
		//6) Write a statement where the shooter has to put a minimum bet of $10 and up to how much they have in their account. Bet can only be
		//   a multiple of $10, otherwise prompt the user to re-enter.
		while(true) {
			
			
			System.out.println("\n" + playersArray[shooterCounter] + ", you are the shooter!");
			System.out.print("You have $" + playersMoneyArray[shooterCounter] + " in your bank roll and minimum bet is $" + MINIMUM_BET + ". Enter your bet amount: ");
			
			while(true) {
				shooterBetAmount = input.nextInt();
					isValidShooter = MA_Project_Methods.validateShooterBet(shooterCounter, shooterBetAmount, playersMoneyArray, MINIMUM_BET);
					remainingBalance = shooterBetAmount;
					if(isValidShooter == true) {
						
						break;
					}
				
			}//end while
			//7) Invite the other players to place a bet starting from the player after the shooter. the bets in total can be up to the shooter amount.
			for(int x = 1; x < playersArray.length; x++) {
				
				if(remainingBalance == 0) {
					System.out.println("The shooter's bet has been completely covered. NO MORE BETS!.");
					break;
				}
				else {
					//11) When a player balance reaches zero '0', he won't be asked again to play or place bets.
					if(playersMoneyArray[x] == 0) {
						continue;
					}
					else {
						System.out.println("\n" + playersArray[x] + ", " + playersArray[shooterCounter] + " has bet $" + shooterBetAmount);
						System.out.println("How much of the $" + remainingBalance + " action do you want?");
						System.out.print("You have $" + playersMoneyArray[x] + " in your bank roll and minimum bet is $" + MINIMUM_BET + ". Enter your bet amount: ");
		
						while(true) {
							playerBetAmount = input.nextInt();
							isValidOpponent = MA_Project_Methods.validateOpponentBet(x, playerBetAmount, playersMoneyArray, MINIMUM_BET, remainingBalance);
							if(isValidOpponent == true) {
								betAmountArray[x] = playerBetAmount;
								remainingBalance -= playerBetAmount;
								actionAmount += playerBetAmount;
								break;
							}
						}//end while
					}//end inner else
				}//end outer else
			
			}//end for
			
			//8) Simulate the shooter’s Dice roll by randomly generating two numbers from 1 to 6 inclusive and adding them together so the total
			//* 		will turn out 2 - 12.
			input.nextLine();
			int rollResult = MA_Project_Methods.rollDice(diceRoll);
			System.out.println("\n\n***** Rolling the dice...and the result is: " + rollResult + "! *****");

			//9) After the pass is completed, redistribute the money wagered to the appropriate players and then show each player’s current bank balance.
			if(diceRoll == 7 || diceRoll == 11) {
				System.out.println("Congratulations " + playersArray[shooterCounter] + "! You have rolled a natural. You win!");
				isWinner = true;
				MA_Project_Methods.adjustBankBalances(betAmountArray, playersMoneyArray, actionAmount, shooterCounter, isWinner);
			}
			else if(diceRoll == 2 || diceRoll == 3 || diceRoll == 12) {
				System.out.println("Unfortunately " + playersArray[shooterCounter] + ", you crapped out. You lose!");
				isWinner = false;
				MA_Project_Methods.adjustBankBalances(betAmountArray, playersMoneyArray, actionAmount, shooterCounter, isWinner);
			}
			else {
				while(true) {
					System.out.println("Rolling the dice again to try for your point...");
					int newResult = MA_Project_Methods.rollDice(diceRoll);
					System.out.println("\n\nRolling...you rolled a " + newResult + "!");
					if(rollResult == newResult) {
						System.out.println("Congratulations " + playersArray[shooterCounter] + "! You rolled your point! You win!");
						isWinner = true;
						MA_Project_Methods.adjustBankBalances(betAmountArray, playersMoneyArray, actionAmount, shooterCounter, isWinner);
						break;
					}
					else if(newResult == 7){
						System.out.println("Unfortunately " + playersArray[shooterCounter] + ", You sevened out and did not make your point. You lose!");
						isWinner = false;
						MA_Project_Methods.adjustBankBalances(betAmountArray, playersMoneyArray, actionAmount, shooterCounter, isWinner);
						break;
					}
					else {
						continue;
					}
				}//end while
			}//end else
			
			System.out.println("After this pass, here are the bankroll balances for everyone:\n");
			
			MA_Project_Methods.printPlayerBankBalances(playersArray, playersMoneyArray);
			
			
			boolean isGameWinner = MA_Project_Methods.checkForWinnner(playersMoneyArray, moneyTotal);
			//10) Ask the shooter if they want to roll again or if they want to pass to the next player. The shooter can pick either option.
			//12) When only one player remains, announce him as the winner and display how much he earned.
			if(isGameWinner == true) {
				String winner = MA_Project_Methods.identifyWinner(playersArray, playersMoneyArray, moneyTotal);
				System.out.println("***** AND WE HAVE THE GAME WINNER! Congratulations, " + winner + "!*****");
				break;
			}
			else {
				System.out.print(playersArray[shooterCounter] + " do you want to roll again or pass the dice? Press Y to roll or press P to pass the dice to the next shooter:");
				char rollOrPass = input.next().charAt(0);
				
				if(rollOrPass == 'Y') {
					continue;
				}
				else if(rollOrPass == 'P') {
					shooterCounter = MA_Project_Methods.getNextShooter(shooterCounter, playersMoneyArray);
					
					//This is an extra method that i built to make it easier to control the arrays after passing the shooter
					MA_Project_Methods.shiftArrayValues(playersMoneyArray);
					MA_Project_Methods.shiftArrayValues(playersArray);
					
				}//end else if
			}//end else
			
		}//end for
		
		//house keeping
		input.close();
	}
}
//end main}

//end class