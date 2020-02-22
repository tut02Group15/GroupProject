
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
	
	private int game[][];
	private JButton b[][];
	private JFrame f;
	private boolean term;
	private int win;
	//r is the length of the board
	//w is win when how many of same kind in a row
	public GUI (int r, int w)
	{
		game = new int[r][r];
		win = w;
		for (int i = 0; i < game.length;i++)
		{
			for (int j = 0; j < game.length;j++)
			{
				
				game[i][j] = 0;
				
			}
		}
		term = true;
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
				b[i][j] = new JButton("+");				
				b[i][j].setBounds(100 * (j + 1),700 - 100*(i + 1),100,100);
				b[i][j].setBorder(BorderFactory.createEmptyBorder());
				//b[i][j].setContentAreaFilled(false);
				b[i][j].setFont(new Font("Arial", Font.PLAIN, 10));
				f.add(b[i][j]);
			}
		}
		
		
	}
	//check win in row 
	//parameter:
	//int a: how many in a row to win
	//int check: the kind of piece
	//return true if n of same piece in a row
	public boolean checkrow( int check)
	{
		for (int i = 0; i < game.length;i++)
		{
			for (int j = 0; j < game.length;j++)
			{
				
				if (game[i][j] == check && j + win < game.length)
				{
					int inrow = 0;
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
	
	public boolean term ()
	{
		return new Boolean(term);
	}
	
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
	
	public static void main(String args[])
	{
		GUI z = new GUI(20, 5);
		z.addAction();
	
		
		
	}
	
	public void endGame()
	{
		if (checkcol(1) || checkcol(2) || checkrow(1) || checkrow(2))
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
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (term)
		{
			try {
				Image img = ImageIO.read(getClass().getResource("gomoku black.jpg"));
				img = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ; 
				((AbstractButton) e.getSource()).setIcon(new ImageIcon(img));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			//((AbstractButton) e.getSource()).setText("O");
			((AbstractButton) e.getSource()).setEnabled(false);
			
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
			((AbstractButton) e.getSource()).setText("X");
			((AbstractButton) e.getSource()).setEnabled(false);
			
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
		//System.out.println(game[0]);
		endGame();
		nextTerm();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		((AbstractButton)e.getSource()).setBackground(Color.GREEN);
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		((AbstractButton)e.getSource()).setBackground(UIManager.getColor("control"));
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	}
	
	


	
	


