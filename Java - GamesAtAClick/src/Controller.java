
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Random;
@SuppressWarnings("serial")
public class Controller  extends Tictactoe implements ActionListener,Serializable {

	int count,flagCount,pturn=0;
	ArrayList<Integer> flags= null;
	//variable to keep track of the games
	ArrayList<Integer> games= null;
	static int choice=0;
	int alpha;
	public int gameType=-1;
	boolean gameOver;
	boolean myMove=true;
	int winner=0;
	int level=0;
	
	int port;
	Socket s = null;
	//Objects created for data transfer
	ObjectOutputStream cObOut =null; 
	ObjectInputStream cObIn = null;
	DataOutputStream cOut = null;
	DataInputStream cIn =null;
	
	
	Controller(int c) throws IOException {
		super();
		try {
			port =12345;
	 		//Client socket created.
	 		//192.168.43.53
			//192.168.43.131
			//127.0.0.1
	        s = new Socket("127.0.0.1",port);
	 		//giving IP address of server and the port for communication
	 			
	 		//Objects created for data transfer
	 		cObOut = new ObjectOutputStream(s.getOutputStream());
	 		//this doesn't make sense.
	 		cObIn = new ObjectInputStream(s.getInputStream());
		    //for sending object data 
	 		cOut = new DataOutputStream(s.getOutputStream());
	 		//for sending data
	  	    cIn = new DataInputStream(s.getInputStream());
	 		//for reading data
		}
		catch(Exception e) {
			
		}
		gameOver=false;
		count=0;
        flagCount=0;
        gameType=c;
        
    	flags= new ArrayList<Integer>();
    	
		for(int i=0;i<9;i++) {
			    flags.add(0);
		}
		//flags.add(10,-1);
		for(int i=0;i<3;i++) {
				
				for(int m=0;m<3;m++) {
				    buttonArray[i][m].addActionListener(this);
				    if(i==1 && m==1) {
				    	//buttonArray[i][m].setForeground(Color.BLUE);
				    	//buttonArray[i][m].setText("X");
				       //flags.add(4,2);	     
				    }
				}
		}
		
		// adding actionListeners to all buttons
		pvp.addActionListener(this);
		cvp.addActionListener(this);
		easy.addActionListener(this);
	    hard.addActionListener(this);
	    theme.addActionListener(this);
	    endGame.addActionListener(this);
	    playAgain.addActionListener(this);
	  //  leaderBoard.addActionListener(this);
	    theme1.addActionListener(this);
	    theme2.addActionListener(this);
	    theme3.addActionListener(this);
	    backToGame.addActionListener(this);
		switchLevel.addActionListener(this);
		//switchGame.addActionListener(this);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==cvp)
		{
			gameType=1;
			System.out.println("In CVP1");
			easy.setVisible(true);
			hard.setVisible(true);
			pvp.setBounds(520,520,300,50);
		    System.out.println("In CVP 2");
		    
		  
		}
		
		if(e.getSource()==pvp)
		{
			gameType=2;
			System.out.println("GameType is 2");
			//JOptionPane.showMessageDialog(this, "Hi here");
			easy.setVisible(false);
			easy.setEnabled(false);
			hard.setVisible(false);
			hard.setEnabled(false);
			cvp.setVisible(false);
			cvp.setEnabled(false);
			pvp.setVisible(false);
			pvp.setEnabled(false);
			game.setVisible(true);
			game2.setVisible(true);
			imageLabel.setVisible(false);
			//switchGame.setVisible(true);
			//TODO: remove the leaderboard functionality???
			
		}
		
		if(e.getSource()==switchGame) {
		  			
			if(gameType==2) {
				JOptionPane.showMessageDialog(this,"Game type has been switched to player vs computer");
				gameType=1;
			}
			else {
				JOptionPane.showMessageDialog(this,"Game type has been switched to player vs player");
				gameType=2;
			}
		}
		
		if(e.getSource()==easy)
		{
			JOptionPane.showMessageDialog(this, "Hi here");
			easy.setVisible(false);
			easy.setEnabled(false);
			hard.setVisible(false);
			hard.setEnabled(false);
			cvp.setVisible(false);
			cvp.setEnabled(false);
			pvp.setVisible(false);
			pvp.setEnabled(false);
			switchLevel.setVisible(true);
			//switchGame.setVisible(true);
			game.setVisible(true);
			game2.setVisible(true);
			imageLabel.setVisible(false);
			level=0;
		}
		
		if(e.getSource()==hard)
		{
			JOptionPane.showMessageDialog(this, "Hi here");
			easy.setVisible(false);
			easy.setEnabled(false);
			hard.setVisible(false);
			hard.setEnabled(false);
			cvp.setVisible(false);
			cvp.setEnabled(false);
			pvp.setVisible(false);
			pvp.setEnabled(false);
			switchLevel.setVisible(true);
			//switchGame.setVisible(true);
			game.setVisible(true);
			game2.setVisible(true);
			imageLabel.setVisible(false);
			level=1;
			
		}
		
		if(e.getSource()== switchLevel)
		{
			if(level==0) {
				level=1;
				JOptionPane.showMessageDialog(this,"Level changed to Hard");
			}
			else {
				level=0;
				JOptionPane.showMessageDialog(this,"Level changed to Easy");
			}
			// resetting everything 
			for(int i=0;i<3;i++) {
				
				for(int m=0;m<3;m++) {
				   // buttonArray[i][m].setBackground(new Color(70,117,170));
					buttonArray[i][m].setText("");
				}
			}
			gameOver=false;
			count=0;
	        flagCount=0;
	    	
			for(int i=0;i<9;i++) {
				    flags.set(i,0); 
			}
		}
		
		if(e.getSource()==theme)
		{
			//leaderBoard.setVisible(false);
		//	leaderBoard.setEnabled(false);
			playAgain.setVisible(false);
			playAgain.setEnabled(false);
			endGame.setVisible(false);
			endGame.setEnabled(false);
			theme.setVisible(false);
			theme.setEnabled(false);
			img1.setVisible(true);
			img2.setVisible(true);
			img3.setVisible(true);
			theme1.setVisible(true);
			theme2.setVisible(true);
			theme3.setVisible(true);
			switchLevel.setVisible(false);
			backToGame.setVisible(true);
		}

		if(e.getSource()==theme1) {
			try {
				image1= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/theme1.jpg"));
				System.out.println("in try");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Image dimg = image1.getScaledInstance(screenSize.width,screenSize.height, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			System.out.println("setting pane");
			//mainFrame.removeAll();
			setContentPane(new JLabel(imageIcon));
			System.out.println("before contentPane");
			this.repaint();
			this.revalidate();
			//mainFrame=this.getContentPane();
			for(int i=0;i<3;i++) {
				
				for(int m=0;m<3;m++) {
				    buttonArray[i][m].setBackground(new Color(204,187,153));  
				}
			}
			this.add(game);
			this.add(game2);
		}
		if(e.getSource()==theme2) {
			try {
				image1= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/theme2.jpg"));
				System.out.println("in try");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Image dimg = image1.getScaledInstance(screenSize.width,screenSize.height, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			System.out.println("setting pane");
			//mainFrame.removeAll();
			setContentPane(new JLabel(imageIcon));
			System.out.println("before contentPane");
			this.repaint();
			this.revalidate();
			//mainFrame=this.getContentPane();
			for(int i=0;i<3;i++) {
				
				for(int m=0;m<3;m++) {
				//    buttonArray[i][m].setBackground(new Color(188,111,187));  
					buttonArray[i][m].setBackground(new Color(241,186,242)); 
				}
			}
			this.add(game);
			this.add(game2);
		}
		
		if(e.getSource()==theme3) {
			try {
				image1= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/theme3.jpg"));
				System.out.println("in try");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Image dimg = image1.getScaledInstance(screenSize.width,screenSize.height, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			System.out.println("setting pane");
			//mainFrame.removeAll();
			setContentPane(new JLabel(imageIcon));
			System.out.println("before contentPane");
			this.repaint();
			this.revalidate();
			//mainFrame=this.getContentPane();
			for(int i=0;i<3;i++) {
				
				for(int m=0;m<3;m++) {
				   // buttonArray[i][m].setBackground(new Color(70,117,170));
					buttonArray[i][m].setBackground(new Color(84,141,206));
				}
			}
			this.add(game);
			this.add(game2);
		}
		
		if(e.getSource()==backToGame) {
			
			//leaderBoard.setVisible(true);
			//leaderBoard.setEnabled(true);
			playAgain.setVisible(true);
			playAgain.setEnabled(true);
			endGame.setVisible(true);
			endGame.setEnabled(true);
			theme.setVisible(true);
			theme.setEnabled(true);
			img1.setVisible(false);
			img2.setVisible(false);
			img3.setVisible(false);
			theme1.setVisible(false);
			theme2.setVisible(false);
			theme3.setVisible(false);
			backToGame.setVisible(false);
			switchLevel.setVisible(true);
			//switchGame.setVisible(true);
		}
		if(e.getSource()==playAgain)
		{
			if(gameType==1) {
				// resetting everything 
				for(int i=0;i<3;i++) {
					
					for(int m=0;m<3;m++) {
					   // buttonArray[i][m].setBackground(new Color(70,117,170));
						buttonArray[i][m].setText("");
					}
				}
				gameOver=false;
				count=0;
		        flagCount=0;
		    	
				for(int i=0;i<9;i++) {
					    flags.set(i,0); 
				}
			}
			if(gameType==2) {
				//sending request to opponent for play again request
				try {
					System.out.println("Sending play again request");
					cObOut.writeObject(this.flags);
					cOut.writeInt(winner);
					cObOut.writeObject("Play Again?");
					String play=(String)cObIn.readObject();
					if(play.equals("Yes")||play.equals("yes")||play.equals("Y")) {
						// resetting everything 
						System.out.println("Here resetting things");
						for(int i=0;i<3;i++) {
							
							for(int m=0;m<3;m++) {
							   // buttonArray[i][m].setBackground(new Color(70,117,170));
								buttonArray[i][m].setText("");
							}
						}
						gameOver=false;
						count=0;
				        flagCount=0;
				    	
						for(int i=0;i<9;i++) {
							    flags.set(i,0); 
						}
						
					}
					else {
						JOptionPane.showMessageDialog(this,"Opponent not interested....");
					}
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			
			
		
		}
		
		if(e.getSource()==endGame)
		{
			JOptionPane.showMessageDialog(this, "Thank You for Playing");
			System.exit(0);
		}
		this.flagCount=0;
		//gameOver();
		
		for(int i=0;i<3;i++) {
			
			for(int m=0;m<3;m++) {
				
				if(e.getSource()==buttonArray[i][m]) {
					
					this.myMove=true;
                     
					//USER vs USER
					
					if(gameType==2) {
											
						if(gameOver()==false) {
							if(alpha==1) {
								buttonArray[i][m].setForeground(Color.RED); 
								buttonArray[i][m].setText("0");
								this.flags.remove(flagCount);
								this.flags.add(flagCount, 1);
							}
							else {

								buttonArray[i][m].setForeground(Color.BLUE);
								buttonArray[i][m].setText("X"); 
								this.flags.remove(flagCount);
								this.flags.add(flagCount, 2);
								
							}
							try {
								//JOptionPane.showMessageDialog(this,"Winner is: "+winner);
								System.out.println("Writing to Server ");
								cObOut.writeObject(this.flags);
								cOut.writeInt(winner);
								cObOut.writeObject("Empty Message");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						}
						else {
							try {
							//	JOptionPane.showMessageDialog(this,"Winner is: "+winner);
								cObOut.writeObject(this.flags);
								cOut.writeInt(winner);
								cObOut.writeObject("Empty Message");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(this, "SCORE :");
							if(winner==-1) {
								JOptionPane.showMessageDialog(this,"DRAW!!!");
							}
							if((winner==1 && alpha==1)||(winner==2 && alpha==2)) {
								JOptionPane.showMessageDialog(this,"YOU WIN!!!");
							}
							else if((winner==2 && alpha==1)||(winner==1 && alpha==2)) {
								JOptionPane.showMessageDialog(this,"OPPONENT WINS!!!");
							}
							
						}
					}
					 //Comp vs User
					else {

						buttonArray[i][m].setForeground(Color.RED); 
						buttonArray[i][m].setText("0");
						this.flags.remove(flagCount);
						this.flags.add(flagCount, 1);
						
						if(!gameOver())
						{
						compMove();
						}
					}
					
				}
				this.flagCount++;
				
			}
	
	    }
			
		
	}
	public void readChoice() {
		
		//USER VS USER
		
		try {
			alpha=cIn.readInt();
			System.out.println("Player id is: "+alpha);
			//JOptionPane.showMessageDialog(this,alpha);
		} catch (HeadlessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readGame() {
		// USER VS USER
		try {
			JOptionPane.showMessageDialog(this,alpha);
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
    @SuppressWarnings("unchecked")
	public void read() {
   
	         try {
	        	
	        	//read all the data sent from server
	        	System.out.println("Reading data from server"); 
	        	winner=cIn.readInt();
	        	ArrayList<Integer> c =new ArrayList<Integer>();
				c=(ArrayList<Integer>)cObIn.readObject();
	        	//String play=(String)cObIn.readObject();

	        	System.out.println("Before gameOver "); 
				if(gameOver()==false) {
					//&& !play.equals("Play Again?")) {
					
					System.out.println("Game Over = false ");
					int flags=0;
					for(int i=0;i<3;i++) {
						int done=0;
						for(int m=0;m<3;m++) {
						//	 JOptionPane.showMessageDialog(this, "inside for");
							 if((c.get(flags)==1 && this.flags.get(flags)==0)) {
								     
							// JOptionPane.showMessageDialog(this, "inside if");
								 if(alpha==2) {
									buttonArray[i][m].setForeground(Color.RED); 											
									buttonArray[i][m].setText("0");											
								 }
								
								 if(alpha==1) {
								    buttonArray[i][m].setForeground(Color.BLUE); 
									buttonArray[i][m].setText("X");	
										
							     }
								this.flags=c;
								//JOptionPane.showMessageDialog(this, ""+this.flags.toString());
								done=1;
								javax.swing.SwingUtilities.isEventDispatchThread();
								break;
							}
							 else if((c.get(flags)==2 && this.flags.get(flags)==0)) {
                           //JOptionPane.showMessageDialog(this, "inside if");
								 if(alpha==2) {
									 
									buttonArray[i][m].setForeground(Color.RED); 											
									buttonArray[i][m].setText("0");	
								
								 }
								
								 if(alpha==1) {
									 
									 buttonArray[i][m].setForeground(Color.BLUE); 
								     buttonArray[i][m].setText("X");	
										
							     }
									this.flags=c;
						    //JOptionPane.showMessageDialog(this, ""+this.flags.toString());
									done=1;
									javax.swing.SwingUtilities.isEventDispatchThread();
									break;
									}
									  
							flags++;
						}
				      if(done==1) {
				    	  break;
				      }
				    }
				}
				else if(gameOver==true) {
				//	&& !play.equals("Play Again?")) {
				
					//System.out.println("Game over and play again = "+play);
					JOptionPane.showMessageDialog(this, "SCORE ");
					if(winner==-1) {
						JOptionPane.showMessageDialog(this,"DRAW!!!");
					}
					if((winner==1 && alpha==1)||(winner==2 && alpha==2)) {
						JOptionPane.showMessageDialog(this,"YOU WIN!!!");
					}
					else if((winner==2 && alpha==1)||(winner==1 && alpha==2)) {
						JOptionPane.showMessageDialog(this,"OPPONENT WINS!!!");
					}
					
				}
			/*	if(play.equals("Play Again?")) {
					System.out.println("Play again requested");
					String reply=JOptionPane.showInputDialog(this,"Would you like to play again? Yes/No");
					if(reply.equals("Yes")||reply.equals("yes")||reply.equals("Y")){
						cObOut.writeObject("Yes");
					}
				}*/
				//else {
					//    cObOut.writeObject("No");
				//}
				System.out.println("Here done with read function"); 	
			} 
	        
	        catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       
	
	}
    public void compMove() {
    	
    	//easy level
    	if(level==0) {
    		System.out.println("At level Easy");
    		ArrayList<Integer> available =new ArrayList<Integer>();
    		for(int i=0;i<9;i++) {
    			if(flags.get(i)==0) {
    				available.add(i);
    			}
    		}
    		Random rand = new Random(); 
            int value=available.get(rand.nextInt(available.size()));
            
            if(value==0) {
            	buttonArray[0][0].setForeground(Color.BLUE);
				buttonArray[0][0].setText("X");
				flags.remove(0);
				flags.add(0,2);
            }
			if(value==1) {
				buttonArray[0][1].setForeground(Color.BLUE);
				buttonArray[0][1].setText("X");
				flags.remove(1);
				flags.add(1,2);     	
			}
			if(value==2) {
				buttonArray[0][2].setForeground(Color.BLUE);
				buttonArray[0][2].setText("X");
				flags.remove(2);
				flags.add(2,2);
			}
			if(value==3) {
				buttonArray[1][0].setForeground(Color.BLUE);
				buttonArray[1][0].setText("X");
				flags.remove(3);
				flags.add(3,2);
			}
			if(value==4) {
				buttonArray[1][1].setForeground(Color.BLUE);
				buttonArray[1][1].setText("X");
				flags.remove(4);
				flags.add(4,2);
			}
			if(value==5) {
				buttonArray[1][2].setForeground(Color.BLUE);
				buttonArray[1][2].setText("X");
				flags.remove(5);
				flags.add(5,2);
			}
			if(value==6) {
				buttonArray[2][0].setForeground(Color.BLUE);
				buttonArray[2][0].setText("X");
				flags.remove(6);
				flags.add(6,2);
			}
			if(value==7) {
				buttonArray[2][1].setForeground(Color.BLUE);
				buttonArray[2][1].setText("X");
				flags.remove(7);
				flags.add(7,2);
			}
			if(value==8) {
				buttonArray[2][2].setForeground(Color.BLUE);
				buttonArray[2][2].setText("X");
				flags.remove(8);
				flags.add(8,2);
			}
			gameOver();
    	}
    	// hard level
    	if(level==1) {
    		System.out.println("Playing level Hard");
	    	pturn=0;
	   
	    	if(pturn==0) {
	    		doublefun(0);
	    	}
	    	
	    	if(pturn==0) {
	    		doublefun(3);
	    	}
	    	
	    	if(pturn==0) {
	    		doublefun(6);
	    	}
	    	
	    	if(pturn==0) {
	    		doublefun(1);
	    	}
	    	
	    	if(pturn==0) {
	    		doublefun(2);
	    	}
	    	
	    	if(pturn==0) {
	    		corner();
	    	}
	    	
	    	if(pturn==0) {
	    		anyempty();
	    	}
	    	gameOver();
    	}
    }
    public boolean isempty()
    {
    	

    	for(int i=0;i<9;i++)
    	{
    		
    			if(flags.get(i)==0)
    			{
    				continue;
    			}
    			else if(flags.get(i)==1)
    			{
    				return false;
    				
    			}
    			
    		
    	}
    	return true;
    	
    }
	public void doublefun(int n)
    {
    	if(n%3==0)
    	{   
    		if((flags.get(n)==1 && flags.get(n+1)==1 && flags.get(n+2)==0) || (flags.get(n+1)==1 && flags.get(n+2)==1 && flags.get(n)==0) || (flags.get(n)==1 && flags.get(n+2)==1  && flags.get(n+1)==0))
    		{
    			
    			if(flags.get(n)==0)
    			{
    				buttonArray[n/3][0].setForeground(Color.BLUE); 
    				buttonArray[n/3][0].setText("X");
    				flags.remove(n);
    				flags.add(n,2);
    				pturn=1;
    			}
    			if(flags.get(n+1)==0)
    			{

    				buttonArray[n/3][1].setForeground(Color.BLUE);
    				buttonArray[n/3][1].setText("X");
    				flags.remove(n+1);
    				flags.add(n+1,2);
    		
    			}
    			if(flags.get(n+2)==0)
    			{

    				buttonArray[n/3][2].setForeground(Color.BLUE);
    				buttonArray[n/3][2].setText("X");
    				flags.remove(n+2);
    				flags.add(n+2,2);
    	
    			}
    			pturn=1;
    		}
    		
    		
    	}
    	
    	if(n%3!=0 || n==0)
    	{   
    		if((flags.get(n)==1 && flags.get(n+3)==1  && flags.get(n+6)==0 ) || (flags.get(n+3)==1 && flags.get(n+6)==1  && flags.get(n)==0) || (flags.get(n)==1 && flags.get(n+6)==1  && flags.get(n+3)==0))
    		{
    		
    			if(flags.get(n)==0)
    			{

    				buttonArray[0][n].setForeground(Color.BLUE);
    				buttonArray[0][n].setText("X");
    				flags.remove(n);
    				flags.add(n,2);
    		
    			}
    			if(flags.get(n+3)==0)
    			{

    				buttonArray[1][n].setForeground(Color.BLUE);
    				buttonArray[1][n].setText("X");
    				flags.remove(n+3);
    				flags.add(n+3,2);
    		
    			}
    			if(flags.get(n+6)==0)
    			{

    				buttonArray[2][n].setForeground(Color.BLUE);
    				buttonArray[2][n].setText("X");
    				flags.remove(n+6);
    				flags.add(n+6,2);
    		
    			}
    			
    			pturn=1;
    		}
    		
    		
    	}
    	else 
    	{   
    		for(int i=0;i<9;i++)
    		{
    			if(i%2==0)
    			{
    				if(i%4==0)
    				{
    					if(i==0) {
	    					if((flags.get(i)==1 && flags.get(i+4)==1 && flags.get(i+8)==0) || (flags.get(i+4)==1 && flags.get(i+8)==1  && flags.get(i)==0) || (flags.get(i)==1 && flags.get(i+8)==1  && flags.get(i+4)==0))
	    		    		{
	    		    		
	    		    			if(flags.get(i)==0)
	    		    			{

	    		    				buttonArray[i/4][i/4].setForeground(Color.BLUE);
	    		    				buttonArray[i/4][i/4].setText("X");
	    		    				flags.remove(i);
	    		    				flags.add(i,2);
	    		    			}
	    		    			if(flags.get(4)==0)
	    		    			{

	    		    				buttonArray[1][1].setForeground(Color.BLUE);
	    		    				buttonArray[1][1].setText("X");
	    		    				flags.remove(i+4);
	    		    				flags.add(i+4,2);
	    		    	
	    		    			}
	    		    			if(flags.get(8)==0)
	    		    			{

	    		    				buttonArray[2][2].setForeground(Color.BLUE);
	    		    				buttonArray[2][2].setText("X");
	    		    				flags.remove(i+8);
	    		    				flags.add(i+8,2);
	    		    			}
	    		    			
	    		    			pturn=1;
	    		    		}
    					}
    				}
    				
    			}
    		}	
    		
    	}
    
    }
    public void corner()
    {
    	for(int i=0;i<9;i++)
    	{
    		if(i%2==0 )
    		{
    			
    			if(flags.get(0)==0 && i==0)
    			{

    				buttonArray[0][0].setForeground(Color.BLUE);
    				buttonArray[0][0].setText("X");
    				//turn=client;
                    flags.remove(0);
                    flags.add(0,2);
                    pturn=1;
                    break;
    				
    			}
    			else if(flags.get(2)==0 && i==2)
    			{

    				buttonArray[0][2].setForeground(Color.BLUE);
    				buttonArray[0][2].setText("X");
                    flags.remove(2);
                    flags.add(2,2);
                    pturn=1;
                    break;
    	
    			}
    			else if(flags.get(6)==0 && i==6)
    			{

    				buttonArray[2][0].setForeground(Color.BLUE);
    				buttonArray[2][0].setText("X");
                    flags.remove(6);
                    flags.add(6,2);
                    pturn=1;
                    break;
   
    			}
    			else if(flags.get(8)==0 && i==8)
    			{

    				buttonArray[2][2].setForeground(Color.BLUE);
    				buttonArray[2][2].setText("X");
                    flags.remove(8);
                    flags.add(8,2);
                    pturn=1;
                    break;
 
    			}
    			
    		}
   
    	}
 	
    }
    public void anyempty() 
    {
		
    		int m=0;
    		
    		for(int j=0;j<3;j++)
    		{
    			int done=0;
    			
    			for(int k=0;k<3;k++)
    			{
    				
    				if(flags.get(m)==0)
    				{

        				buttonArray[j][k].setForeground(Color.BLUE);
    					buttonArray[j][k].setText("X");
    					flags.remove(m);
    					flags.add(m, 2);
    					done=1;
    					break;
    				}
    				m++;
    			}
    			
    			
    			if(done==1)
    				break;
    		}
    		
    	}
   	public boolean gameOver() {
    		
    		
    			if((flags.get(0)==2 &&  flags.get(1)==2 && flags.get(2)==2 ) || 
    					(flags.get(3)==2 &&  flags.get(4)==2 && flags.get(5)==2 ) ||
    					(flags.get(6)==2 &&  flags.get(7)==2 && flags.get(8)==2 ) ||
    					(flags.get(0)==2 &&  flags.get(3)==2 && flags.get(6)==2 ) ||
    					(flags.get(1)==2 &&  flags.get(4)==2 && flags.get(7)==2 ) ||
    					(flags.get(2)==2 &&  flags.get(5)==2 && flags.get(8)==2 ) ||
    					(flags.get(0)==2 &&  flags.get(4)==2 && flags.get(8)==2 ) ||
    					(flags.get(2)==2 &&  flags.get(4)==2 && flags.get(6)==2 ) 
    					
    					)
    			{
    				if(gameType==1) {
    					
    					JOptionPane.showMessageDialog(this, "COMPUTER WINS");
    				}
    				else {
    					winner=2;
    				}
    				return true;
    				
    			}
    			
    			if((flags.get(0)==1 &&  flags.get(1)==1 && flags.get(2)==1 ) || 
    					(flags.get(3)==1 &&  flags.get(4)==1 && flags.get(5)==1 ) ||
    					(flags.get(6)==1 &&  flags.get(7)==1 && flags.get(8)==1 ) ||
    					(flags.get(0)==1 &&  flags.get(3)==1 && flags.get(6)==1 ) ||
    					(flags.get(1)==1 &&  flags.get(4)==1 && flags.get(7)==1 ) ||
    					(flags.get(2)==1 &&  flags.get(5)==1 && flags.get(8)==1 ) ||
    					(flags.get(0)==1 &&  flags.get(4)==1 && flags.get(8)==1 ) ||
    					(flags.get(2)==1 &&  flags.get(4)==1 && flags.get(6)==1 ) 
    					
    					)
    			{
    				if(gameType==1) {
    					JOptionPane.showMessageDialog(this, "YOU ARE THE WINNER");
    				}
    				else {
    					winner=1;
    				}
    				return true;
    				
    			}
    			
    			else if(flags.get(0)==0 ||  flags.get(1)==0 || flags.get(2)==0  || 
    					flags.get(3)==0 ||  flags.get(4)==0 || flags.get(5)==0  ||
    					flags.get(6)==0 ||  flags.get(7)==0 || flags.get(8)==0  ) 
    			{
    				return false;
    				
    			}
    			
    			
    			
    			else {
    				if(gameType==1) {
    					JOptionPane.showMessageDialog(this, "DRAW");
    				}
    				else {
    					winner=-1;
    				}
    				return true;
    			}
    		
		}
	

}

