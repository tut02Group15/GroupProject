
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
public class Game
{	
	
	public static void commandLinePlayGame(Game_Configuration gc,ComputerPlayer AI) 
	{
		int x,y;
		Scanner s = new Scanner(System.in);
		System.out.println("Do you want to play chess with people or computers?");
		System.out.println("If you want to play chess with someone, please enter 1");
		System.out.println("If you want to play chess with your computer, enter 2");
        int enter=s.nextInt();
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
                }
                else
                	{System.out.println("White(AI) play chess now");
                    AI.playChess();
                	}
    		}
        else if(enter==1)
        {
		while(true)
		{
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
		
		
			
	}
	
	/*
	 * main function
	 * 
	 */
	
	public static void main(String args[])
	{   
		Game_Configuration gameConfiguration = new Game_Configuration();
		gameConfiguration.addAction();
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
	{int x=0,y=0;
	x=(int)(Math.random()*100)%15;
	y=(int)(Math.random()*100)%15;
	while(!gc.commandLinePlayChess(x,y))
	{   x=(int)(Math.random()*100)%15;
	    y=(int)(Math.random()*100)%15;
	}
	}

	
	}


class Game_Configuration implements ActionListener, MouseListener {
	/*
	 game[][] is the board/(2d array) that records which position a player places the piece. (game board behind the scene)
	 
	 JButton b[][] is the buttons/board in the windows which players can place down a piece at a valid location by click on it.
	 
	 JFrame f is the windows that shows up when the program is run
	 
	 boolean term is used to determine which player's turn is it. (true for player1 and false for player2)
	 
	 int win is a integer entered by user to that tells how many of same kind of piece in a row will result in victory
	 
	 */
	private int game[][];
	private JButton b[][];
	private JFrame f;
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
	public int[][] getGame_Configuration()
	{int [][]gameCopy=new int[15][15];
		for(int i=0;i<r;i++)
			for(int j=0;j<r;j++) {
				gameCopy[i][j]=game[i][j];
			}
		return gameCopy;
	}
	
	
	
	public boolean commandLinePlayChess(int x,int y)
{
		if(game[x][y]==0)
		{
		if (stepcount%2==0)
		{
				Image img;
				try {
					img = ImageIO.read(getClass().getResource("gomoku black.png"));

				img = img.getScaledInstance( 45, 45,100 ) ; 
				b[x][y].setIcon(new ImageIcon(img));
				//from  https://stackoverflow.com/questions/11380318/jlabel-imageicon-position
				b[x][y].setHorizontalTextPosition(JLabel.CENTER);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//((AbstractButton) e.getSource()).setText("O");
			

							game[x][y] = 1;
						
		}
		else
			
		{
			
				Image img;
				try {
					img = ImageIO.read(getClass().getResource("gomoku white.png"));
				
				img = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ; 
				b[x][y].setIcon(new ImageIcon(img));
				//from  https://stackoverflow.com/questions/11380318/jlabel-imageicon-position
				b[x][y].setHorizontalTextPosition(JLabel.CENTER);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				            game[x][y] = 2;
					
				}

		b[x][y].removeActionListener(this);
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
		//initalize term and JFrame and term
		
		//Add necessary component to JFrame
		f = new JFrame("Hi");
		b = new JButton[r][r];
		f.setSize(700, 700);
		f.setLayout(new GridLayout(r,r));
		//f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int i = 0; i < b.length;i++)
		{
			for (int j = 0; j < b.length;j++)
			{
				//create buttons and add image to it
				b[i][j] = new JButton();				
				b[i][j].setBounds(100 * (j + 1),700 - 100*(i + 1),200,200);
				b[i][j].setBorder(BorderFactory.createEmptyBorder());


				b[i][j].setFont(new Font("Arial", Font.PLAIN, 200));
				//add them to f(JFrame).
				f.add(b[i][j]);
			}
		}
		f.setVisible(true);
		
		
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
		for (int i = x + 1; i < 15; i++)
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
        if(counter>=5)
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
		for (int j = y + 1; j < 15; j++)
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
        if(counter>=5)
		return true;
        return false;
		
	}
	
	/*
	 * Check in diagonal function
	 */
	public boolean checkDiagonal(int x, int y)
	{
			int counter = 0;
			for (int i = x + 1,j =y+1; i < 15 && j < 15;i++,j++)
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
	        if(counter>=5)
			return true;
	        return false;
	}
	
	
	/*
	 * This method add ActionListener(which allows the program to know when the button is clicked) and 
	 * MouseListener(which allows the program know when the mouse is hovering above the button)
	 * 
	 * Usage --> a.addAction(); (a must be a GUI)
	 * 
	 * Parameter: None
	 *
	 * return: this method does not return anything
	 *
	 */
	public void addAction ()
	{
		for (int i = 0; i < b.length;i++)
		{
			for (int j = 0; j < b.length;j++)
			{
				
				b[i][j].addActionListener(this);
				b[i][j].addMouseListener(this);
			}
		}
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
		for (int i = 0; i < b.length;i++)
		{
			for (int j = 0; j < b.length;j++)
			{
				
				b[i][j].setEnabled(false);
				
			}
		}

		if(getPlayer()==1)
		JOptionPane.showMessageDialog(null, "Black is the winner", "Game status", JOptionPane.INFORMATION_MESSAGE);

		else
			JOptionPane.showMessageDialog(null, "White is the winner", "Game status", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
		if(stepcount>=224)
			JOptionPane.showMessageDialog(null, "The game ends without a winner", "Game status", JOptionPane.INFORMATION_MESSAGE);
		return false;
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
	@Override
	public void actionPerformed(ActionEvent e) {
		int x=-1,y=-1;
		if (stepcount%2+1==1)
		{
			//((AbstractButton) e.getSource()).removeActionListener(this);
			try {
				Image img = ImageIO.read(getClass().getResource("gomoku black.png"));
				img = img.getScaledInstance( 45, 45,100 ) ; 
				((AbstractButton) e.getSource()).setIcon(new ImageIcon(img));
				//from  https://stackoverflow.com/questions/11380318/jlabel-imageicon-position
				((AbstractButton) e.getSource()).setHorizontalTextPosition(JLabel.CENTER);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			//((AbstractButton) e.getSource()).setText("O");
			
			
			for (int i = 0; i < b.length;i++)
			{
				for (int j = 0; j < b[i].length;j++)
				{
					
					if ( e.getSource()== b[i][j])
					{
						x=i;
						y=j;
							game[i][j] = 1;
						
							
						
						break;
					}
					
				}
			}
			
			
		}
		else
			
		{
			
			try {
				Image img = ImageIO.read(getClass().getResource("gomoku white.png"));
				img = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ; 
				((AbstractButton) e.getSource()).setIcon(new ImageIcon(img));
				//from  https://stackoverflow.com/questions/11380318/jlabel-imageicon-position
				((AbstractButton) e.getSource()).setHorizontalTextPosition(JLabel.CENTER);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
			
			
			for (int i = 0; i < b.length;i++)
			{
				for (int j = 0; j < b[i].length;j++)
				{
					
					if (e.getSource() == b[i][j])
					{
						
						x=i;
						y=j;
						game[i][j] = 2;
						
						break;
					}
					
				}
			}
			
			
		}
		((AbstractButton) e.getSource()).removeActionListener(this);
		judgeWin(x,y);
		nextTerm();
		
	}
	/*
	 * This method is implemented from MouseListener
	 * This method is not used
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * This method is implemented from MouseListener. This method highlights the button when the mouse hovers above it.
	 * 
	 * Usage --> When the mouse hovers above the button that has mouseListener, the method will be ran
	 * 
	 * Parameter:
	 * MouseEvent e: mouse event that happened on the button
	 * 
	 * return: there is no return for this method
	 * 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		((AbstractButton)e.getSource()).setBackground(Color.GREEN);
		
	}
	/*
	 * This method is implemented from MouseListener. This method turns the button back to normal when the mouse leaves the button
	 * 
	 * Usage -->When the mouse moves away from the button, the method will run
	 * 
	 * Parameter:
	 * MouseEvent e: mouse event that happened on the button
	 * 
	 * return: there is no return for this method
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		((AbstractButton)e.getSource()).setBackground(UIManager.getColor("control"));
		
	}
	/*
	 * This method is implemented from MouseListener
	 * This method is not used
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * This method is implemented from MouseListener
	 * This method is not used
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	}
	



	
	


