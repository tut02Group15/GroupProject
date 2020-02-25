
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;


public class GUI implements ActionListener, MouseListener {
	/*
	 game[][] is the board/(2d array) that records which position a player places the piece. (game board behind the scene)
	 
	 JButton b[][] is the buttons/board in the windows which players can place down a piece at a valid location by click on it.
	 
	 JFrame f is the windows that shows up when the program is runned
	 
	 boolean term is used to determine which player's turn is it. (true for player1 and false for player2)
	 
	 int win is a integer entered by user to that tells how many of same kind of piece in a row will result in victory
	 
	 */
	private int game[][];
	private JButton b[][];
	private JFrame f;
	private boolean term;
	private int win;
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
	public GUI (int r, int w)
	{
		//initialize game board and win variable
		game = new int[r][r];
		win = w;
		for (int i = 0; i < game.length;i++)
		{
			for (int j = 0; j < game.length;j++)
			{
				
				game[i][j] = 0;
				
			}
		}
		//initalize term and JFrame and term
		term = true;
		//Add necessary component to JFrame
		f = new JFrame("Hi");
		b = new JButton[r][r];
		f.setSize(700, 700);
		f.setLayout(new GridLayout(r,r));
		//f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		for (int i = 0; i < b.length;i++)
		{
			for (int j = 0; j < b.length;j++)
			{
				//create buttons and add image to it
				b[i][j] = new JButton(new ImageIcon("center.png"));				
				b[i][j].setBounds(100 * (j + 1),700 - 100*(i + 1),200,200);
				b[i][j].setBorder(BorderFactory.createEmptyBorder());
				b[i][j].setIcon(new ImageIcon("center.png"));

				b[i][j].setFont(new Font("Arial", Font.PLAIN, 200));
				//add them to f(JFrame).
				f.add(b[i][j]);
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
	 
	public boolean checkrow( int check)
	{
		for (int i = 0; i < game.length;i++)
		{
			for (int j = 0; j < game.length;j++)
			{
				//If there is a piece that matches the one you want to check
				
				if (game[i][j] == check && j + win < game.length)
				{
					int inrow = 0;
					//go forward in a row and see if the next piece matches the one you want to check
					for (int c = j; c < game.length; c++)
					{
						if (game[i][c] == check)
						{
							inrow += 1;
							if (inrow == win)
							{
								return true;
							}
						}
						else
						{
							inrow = 0;
						}
					}
				}
				
			}
		}
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
	
	public boolean checkcol(int check)
	{
		for (int i = 0; i < game.length;i++)
		{
			for (int j = 0; j < game.length;j++)
			{
				
				if (game[i][j] == check && i + win < game.length)
				{
					int inrow = 0;
					for (int c = i; c < game.length; c++)
					{
						if (game[c][j] == check)
						{
							inrow += 1;
							if (inrow == win)
							{
								return true;
							}
						}
						else
						{
							inrow = 0;
						}
					}
				}
				
			}
		}
		return false;
	}
	
	
	/*
	 * Check in diagonal function
	 */
	public boolean checkDiag(int check)
	{
		
		for (int i = 0; i < b.length;i++)
		{
			for (int j = 0; j < b.length;j++)
			{
				//forward 5 and downward 5
				if (game[i][j] == check && i + 5 < game.length && j + 5 < b.length)
				{
					int inrow = 0;
					int d = i;
					for (int c = j; c < j + 5; c++)
					{
						if (game[d][c] == check)
						{
							inrow += 1;
							d += 1;
							if (inrow == win)
							{
								return true;
							}
						}
						else
						{
							inrow = 0;
							d += 1;
						}
					}
				}
				//forward 5 and upward 5
				else if (game[i][j] == check && j + 5 < game.length && i - 5 >= 0)
				{
					int inrow = 0;
					int d = i;
					for (int c = j; c < j + 5; c++)
					{
						if (game[d][c] == check)
						{
							inrow += 1;
							d -= 1;
							if (inrow == win)
							{
								return true;
							}
						}
						else
						{
							inrow = 0;
							d -= 1;
					}
				}
			}
		}
		
		}
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
	 * Usage --> term();
	 * 
	 * parameter: None
	 * 
	 * Return: The function return true when it is player one's turn and false for player two's turn
	 */
	public boolean term ()
	{
		return new Boolean(term);
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
		if (term)
		{
			term = false;
		}
		else
		{
			term = true;
		}
	}
	
	/*
	 * main function
	 * 
	 */
	
	public static void main(String args[])
	{
		GUI z = new GUI(20, 5);
		z.addAction();
	
		
		
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
	
	public void endGame()
	{
		if (checkcol(1) || checkcol(2) || checkrow(1) || checkrow(2) || checkDiag(1) || checkDiag(2))
		{
		for (int i = 0; i < b.length;i++)
		{
			for (int j = 0; j < b.length;j++)
			{
				
				b[i][j].setEnabled(false);
				
			}
		}
		JOptionPane.showMessageDialog(null, "Game ends here", "Game status", JOptionPane.INFORMATION_MESSAGE);
	}
	}

	//O is 1 and X is 2
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
		
		if (term)
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
						
						
						game[i][j] = 2;
						
						break;
					}
					
				}
			}
			
			
		}
		((AbstractButton) e.getSource()).removeActionListener(this);
		endGame();
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
	
	


	
	


