
import java.util.Scanner;

import javax.swing.*;
/*
main class.It control the entire game program
 */
public class GameWithoutGUI
{	
	/*commandLinePlayGame:this method Prompts the user for input.Processes the input.Displays the result
	 *
	 *Usage --> commandLinePlayGame(Game_Configuration gc,ComputerPlayer AI) 
	 *
	 *Parameter:
	 *Game_Configuration gc : An instance of class "Game_Configuration"
	 *ComputerPlayer AI : An instance of class "ComputerPlayer"
	 *
	 *Return: no return

	*/

	public static void commandLinePlayGame(Game_Configuration gc,ComputerPlayer AI) 
	{
		int x,y;
		Scanner s = new Scanner(System.in);
		System.out.println("Do you want to play chess with people or computers?");
		System.out.println("If you want to play chess with someone, please enter 1");
		System.out.println("If you want to play chess with your computer, enter 2");
        int enter=s.nextInt();
        if(enter==1)  
        {
    		while(true){
                if(gc.getPlayer()==1)
    			    System.out.println("Black should play chess now");
                else
                	System.out.println("White should play chess now");
    			System.out.println("Please enter the abscissa (0<=x<15) of the position where you want to play chess");
    			x=s.nextInt();
    			System.out.println("Please enter the ordinate (0<=y<15) of the position where you want to play chess");
    			y=s.nextInt();
    			if(!gc.commandLinePlayChess(x,y))
    			{
    				System.out.println("There are already pawns in this position. Please re-enter the coordinates.");
    				continue;
    			}
    			if(gc.judgeWin(x,y))
    				break;
                    }	
            }
        if(enter==2)
        	while(true)
    		{
                if(gc.getPlayer()==1)
                {
    			System.out.println("Black(you) should play chess now");
    			System.out.println("Please enter the abscissa (0<=x<15) of the position where you want to play chess");
    			x=s.nextInt();
    			System.out.println("Please enter the ordinate (0<=y<15) of the position where you want to play chess");
    			y=s.nextInt();
    			if(!gc.commandLinePlayChess(x,y))
    			{
    				System.out.println("There are already pawns in this position. Please re-enter the coordinates.");
    				continue;
    			}

    			if(gc.judgeWin(x,y))
    				break;
                else
                	{System.out.println("White(AI) is playing chess now");
                    AI.playChess();
                	}
    		}
       
    		}
	}

	
	/*
	 * main function
	 * 
	 */
	
	public static void main(String args[])
	{   
		Game_Configuration gameConfiguration = new Game_Configuration();
		ComputerPlayer AI=new ComputerPlayer(gameConfiguration);
		commandLinePlayGame(gameConfiguration,AI);
		
	}
	}

/*
 *Constructor: This is a constructor that takes in two parameters. It initialize all the instance variable the program needs.
 *It initialize both the game board behind the theme and the game board in the frame.
 *It creates the frame. 
 *
 *Usage --> GUI z = new GUI(int r, int w);
 *
 *Parameter:
 *int r is the length of the board.
 *int w is win condition. (how many of same kind in a row)
 *
 *Return: no return
 */


/*
This is an AI class.Now only playing chess casually, its function needs to be perfected.

*/

class ComputerPlayer
{   //This variable can receive checkerboard information
	private Game_Configuration gc;
	
	/*
	 *Constructor: This is a constructor.
	 *It initialize AI .
	 *
	 *Usage --> ComputerPlayer(Game_Configuration gc)
	 *
	 *Parameter:none
	 *
	 *Return:An instance of the class "ComputerPlayer"
	 */
	public ComputerPlayer(Game_Configuration gc)
	{
		this.gc=gc;
	}
	
	public void playChess()
	{
		int x=0,y=0;
	x=(int)(Math.random()*100)%15;
	y=(int)(Math.random()*100)%15;
	while(!gc.commandLinePlayChess(x,y))
	{   x=(int)(Math.random()*100)%15;
	    y=(int)(Math.random()*100)%15;
	}
	}

	
	}



/*
This is the game configuration class

*/


class Game_Configuration {
	/*
	 game[][] is the board/(2d array) that records which position a player places the piece. (game board behind the scene)
     int r R is the number of rows and columns of the chessboard.
	 int win is a integer entered by user to that tells how many of same kind of piece in a row will result in victory
	 
	 */
	private int game[][];
	private int stepcount;
	// step recorder:Record how many moves we have played.
	private static final int r=15;
	private static final int win=5;
	
	/*This method receives a pair of horizontal and vertical coordinates and plays chess at a specified position.
	 *
	 *Usage --> commandLinePlayChess(int x,int y);
	 *
	 *Parameter:int x,int y  The horizontal and vertical coordinates of the chess piece just played.
	 *
	 *Return:An boolean value.
	 *If its return value is true, it means that the position of x,y is empty.Player plays chess successfully.
	 */

	
	public boolean commandLinePlayChess(int x,int y)
{
		if(game[x][y]==0)
		{
		if (stepcount%2==0)
		{
							game[x][y] = 1;
						
		}
		else
			
		{
				            game[x][y] = 2;				
				}
		System.out.println("The board is as follows (1 for black and 2 for white)");
		for(int i=0;i<r;i++)
		{
        for(int j=0;j<r;j++)
		{
			System.out.print(game[i][j]);
		}
        System.out.print("\n");
		}
		judgeWin(x,y);
		nextTerm();
		return true;
		}
		return false;
	}
	
	/*
	 *Constructor: This is a constructor.
	 *It initialize both the game board .
	 *
	 *Usage --> Game_Configuration();
	 *
	 *Parameter:none
	 *
	 *Return:An instance of the class "Game_Configuration"
	 */
	

	public Game_Configuration()
	{
		//initialize game board and win variable
		game = new int[r][r];
		stepcount=0;
		for (int i = 0; i < game.length;i++)
		{
			for (int j = 0; j < game.length;j++)
			{
				
				game[i][j] = 0;
				
			}
		}
		
		
		
	}
	/*
	*check whether a player win the game by having # of same piece in a row
	*
	*Usage --> checkCow(int X,int y)
	*
	*parameter:
    * Parameter:int x,int y  The horizontal and vertical coordinates of the chess piece just played.
	*
	*
	*return true if # of same piece in a row, or return false if the condition for win in row is not met.
	 */
	 
	public boolean checkRow(int x, int y) {
		int counter = 0;
		for (int i = x + 1; i < r; i++)
		{
			if (game[i][y] ==game[x][y])
				counter++;
				else break;
		}
		
		for (int i = x; i >= 0; i--) {
			if (game[i][y] ==game[x][y])
				counter++;
            else break;
		}
        if(counter>=win)
		return true;
        return false;
		
	}
	/*
	 * This method checks whether a player win the game by having # of same piece in a column
	 * 
	 * Usage --> checkCol(int x,int y)
	 * 
	 * Parameter:
	 * * Parameter:int x,int y  The horizontal and vertical coordinates of the chess piece just played.
	 * 
	 * return true if player one of the player met the win condition for win in column, else return false.
	 */
	

	
	public boolean checkCol(int x, int y) 
	{
		int counter = 0;//
		for (int j = y + 1; j < r; j++)
		{
			if (game[x][j] ==game[x][y])
				counter++;
				else break;
		}
		
		for (int j = x; j >= 0; j--) {
			if (game[x][j] ==game[x][y])
				counter++;
            else break;
		}
        if(counter>=win)
		return true;
        return false;
		
	}
	
	/*
	 *checkDiagonal: Check if five pieces of the same color appear diagonally
	 *
	 *Usage -->checkDiagonal(int x, int y)
	 *
	 *Parameter:

	 * Parameter:int x,int y  The horizontal and vertical coordinates of the chess piece just played.
	 *Return: boolean: If the return value is true, it means that the player has won.
	 */
	public boolean checkDiagonal(int x, int y)
	{
			int counter = 0;
			for (int i = x + 1,j =y+1; i < r && j < r;i++,j++)
			{
				if (game[i][j] ==game[x][y])
					counter++;
					else break;
			}
			
			for (int i = x,j =y; i >=0 && j >=0;i--,j--) {
				if (game[i][j] ==game[x][y])
					counter++;
	            else break;
			}
	        if(counter>=win)
			return true;
	        return false;
	}
	

	/*
	 * This method return whose turn is it to play
	 * 
	 * Usage --> getPlayer();
	 * 
	 * parameter: None
	 * 
	 * Return: The function return 1 when it is player black's turn and 2 for player white's turn
	 */
	public int getPlayer()
	{
	return stepcount%2+1;
	}
	
	/*
	 * This method increases the value of the step recorder by one.
	 * 
	 * Usage --> nextTerm();
	 * 
	 * parameter: None
	 * 
	 * Return: none
	 */
	public void nextTerm()
	{
		stepcount++;
	}

	
	/*
	 * This function checks to see if a game ended by checking whether there are avaliable place for placing down
	 * and whether a player already win.
	 * 
	 * Usage --> judgeWin();
	 * 
	 * Parameter:int x,int y The horizontal and vertical coordinates of the chess piece just played.
	 * 
	 * Return: A boolean Value.If it is true, the game is over
	 */
	
	public boolean judgeWin(int x,int y)
	{
		if (checkCol(x,y) || checkRow(x,y)|| checkDiagonal(x,y))
		{
		if(getPlayer()==1)
			System.out.println("Black is the winner!!!!!!!");

		else
			System.out.println("White is the winner!!!!!!!");
		return true;
	}
		if(stepcount>=224)
			{System.out.println("The game ends without a winner");
		return true;}
		return false;
	}
}

	//black is 1 and white is 2

	