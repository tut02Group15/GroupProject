
import java.util.Scanner;

import javax.swing.*;

public class GameWithoutGUI
{	
	
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






class ComputerPlayer
{
	private Game_Configuration gc;
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






class Game_Configuration {
	/*
	 game[][] is the board/(2d array) that records which position a player places the piece. (game board behind the scene)
	 
	 JButton b[][] is the buttons/board in the windows which players can place down a piece at a valid location by click on it.
	 
	 JFrame f is the windows that shows up when the program is run
	 
	 boolean term is used to determine which player's turn is it. (true for player1 and false for player2)
	 
	 int win is a integer entered by user to that tells how many of same kind of piece in a row will result in victory
	 
	 */
	private int game[][];
	private int stepcount;

	private static final int r=15;
	private static final int win=5;
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
	*Usage --> checkrow(int check)
	*
	*parameter:
	*int check: the kind of piece  (1 for black and 2 for white)
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
	 * Usage --> checkcol(int check)
	 * 
	 * Parameter:
	 * int check: the kind of piece (1 for black and 2 for white)
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
	 * Check in diagonal function
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
	 * Return: The function return true when it is player one's turn and false for player two's turn
	 */
	public int getPlayer()
	{
	return stepcount%2+1;
	}
	/*
	 * This method will switch term will one player places down a piece. When the function is called term will switch to the other player
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
	 * and whether a player already win. If the game ended then all the buttons will be disabled and a pop up will 
	 * show up on the screen indicates that the game ended.
	 * 
	 * Usage --> endGame();
	 * 
	 * Parameter:None;
	 * 
	 * Return: no return
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
			System.out.println("The game ends without a winner");
		return false;
	}
}

	//black is 1 and white is 2
	/*
	 * This is the implemented method from actionListener. This method allows me to execute actions when a button with 
	 * actionListeners are pressed
	 * 
	 * Usage --> The method will be used when a button is pressed
	 * 
	 * Parameter:
	 * ActionEvent e: the event that happened on the button
	 * 
	 * return: there is no return for this method
	 */

	