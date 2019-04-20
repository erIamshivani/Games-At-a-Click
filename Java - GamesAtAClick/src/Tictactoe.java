
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tictactoe extends JFrame implements Serializable {
	
	JPanel choice,game,game2,leaderboard,wallpaper,radioPanel;
	BufferedImage image,image1;
	JButton[][] buttonArray;
	//cvp=comp vs player pvp=player vs player
	JButton getChoice,cvp,pvp,playAgain,theme,endGame,backToGame,chooseTheme,selectLevel,easy,hard;
	JButton theme1,theme2,theme3,switchLevel,switchGame;
	//JButton leaderBoard;
	ButtonGroup level;
	JLabel imageLabel,imageLabel1,img1,img2,img3;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	Container mainFrame;
	
	Tictactoe() throws IOException{

		//screenSize.width=900;
		//screenSize.height=900;
		this.setSize(screenSize.width,screenSize.height); 
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		image1= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/wall2.jpg"));
		Image dimg = image1.getScaledInstance(screenSize.width,screenSize.height, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));
	    mainFrame=this.getContentPane();
		
		//this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		//==========================================================================================
		
		//This is game board
		
		game=new JPanel();
		game.setBounds(0,0,screenSize.width/2+220,screenSize.height);
		game.setLayout(null);
		game.setBackground(new Color(255,255,255));
		game.setBorder(BorderFactory.createLineBorder(Color.black));
		game.setOpaque(false);
		
		game2=new JPanel();
		game2.setBounds(screenSize.width/2+220,0,screenSize.width/2-200,screenSize.height);
		game2.setLayout(null);
		game2.setBackground(new Color(255,255,255));
		game2.setOpaque(false);
		
		buttonArray = new JButton[3][3];
		
		int j=250; // x coordinate
		int k=200; // y coordinate
		
		for(int i=0;i<3;i++) {
			
			for(int m=0;m<3;m++) {
				
			    buttonArray[i][m]= new JButton();
			    buttonArray[i][m].setBounds(j,k,120,120);
			    buttonArray[i][m].setBackground(new Color(230,250,230));
			    //230,250,230
			    buttonArray[i][m].setFont(new Font("Tahoma", Font.BOLD, 25));
			    game.add(buttonArray[i][m]);
			    j=j+130;
			}
			j=250;
			k=k+130;
		}
		 
		//x coordinate starts from 0 since second JPanel
	   /* leaderBoard= new JButton("Leader Board");
	    leaderBoard.setFont(new Font("Tahoma", Font.BOLD, 15));
	    leaderBoard.setBounds(100,200,230,50);
	    game2.add(leaderBoard);*/
	    
	    playAgain= new JButton("Play Again");
	    playAgain.setFont(new Font("Tahoma", Font.BOLD, 15));
	    playAgain.setBounds(100,300,230,50);
	    game2.add(playAgain);
	    
	    theme= new JButton("Change Theme");
	    theme.setFont(new Font("Tahoma", Font.BOLD, 15));
	    theme.setBounds(100,400,230,50);
	    game2.add(theme);
	    
	    endGame= new JButton("End Game");
	    endGame.setFont(new Font("Tahoma", Font.BOLD, 15));
	    endGame.setBounds(100,500,230,50);
	    game2.add(endGame);
	    
	    switchLevel= new JButton("Switch Level");
	    switchLevel.setFont(new Font("Tahoma", Font.BOLD, 15));
	    switchLevel.setBounds(80,70,200,40);
	    switchLevel.setVisible(false);
	    game.add(switchLevel);
	    
	    switchGame= new JButton("Switch Game Type ");
	    switchGame.setFont(new Font("Tahoma", Font.BOLD, 15));
	    switchGame.setBounds(300,70,200,40);
	    switchGame.setVisible(false);
	    game.add(switchGame);
	    
	    this.add(game);
	    game.setVisible(false); 
	  
	    //=================================================================================================
	    // This is for images 
	
	    int length=screenSize.width/2-100;
	    int height=screenSize.height/4;
		
		image= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/theme1.jpg"));
	    Image dim1 = image.getScaledInstance(length-400,height+10, Image.SCALE_SMOOTH);
		ImageIcon imIcon1 = new ImageIcon(dim1);	   
		img1=new JLabel(imIcon1);
		img1.setBounds(-600,50,length+900,height+10);
		img1.setVisible(false);
        game2.add(img1); 
        
    	image= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/theme2.jpg"));
	    Image dim2 = image.getScaledInstance(length-400,height+10, Image.SCALE_SMOOTH);
		ImageIcon imIcon2 = new ImageIcon(dim2);	   
		img2=new JLabel(imIcon2);
		img2.setBounds(-600,height+50+20,length+900,height+10);
		img2.setVisible(false);
		game2.add(img2); 
	
        image= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/theme3.jpg"));
	    Image dim3 = image.getScaledInstance(length-400,height+10, Image.SCALE_SMOOTH);
		ImageIcon imIcon3 = new ImageIcon(dim3);	   
		img3=new JLabel(imIcon3);
		img3.setBounds(-600,height*2+40+50,length+900,height+10);
		img3.setVisible(false);
		game2.add(img3); 
        
        theme1= new JButton("Select");
	    theme1.setFont(new Font("Tahoma", Font.BOLD, 15));
	    theme1.setBounds(250,120,100,50);
		theme1.setVisible(false);
	    game2.add(theme1);
	    
	    theme2= new JButton("Select");
	    theme2.setFont(new Font("Tahoma", Font.BOLD, 15));
	    theme2.setBounds(250,120+height+20,100,50);
	    theme2.setVisible(false);
	    game2.add(theme2);
	    
	    theme3= new JButton("Select");
	    theme3.setFont(new Font("Tahoma", Font.BOLD, 15));
	    theme3.setBounds(250,120+height+50+height,100,50);
	    theme3.setVisible(false);
	    game2.add(theme3);
	    
	    //=================================================================================================
	    //Adding go back button
	    backToGame= new JButton("Back");
	    backToGame.setFont(new Font("Tahoma", Font.BOLD, 15));
	    backToGame.setBounds(80,70,80,40);
	    backToGame.setVisible(false);
	    game.add(backToGame);
	    
        this.add(game2);
	    game2.setVisible(false);
	    //=================================================================================================
	    //This is First page

		
		cvp=new JButton("Computer vs Player");
		cvp.setBounds(520,350,300,50);
	    cvp.setBackground(new Color(214,214,214));
	    cvp.setFont(new Font("Tahoma", Font.BOLD, 15));
	    mainFrame.add(cvp);
	    
		pvp=new JButton("Player vs Player");
		pvp.setBounds(520,450,300,50);
	    pvp.setBackground(new Color(214,214,214));
	    pvp.setFont(new Font("Tahoma", Font.BOLD, 15));
	    mainFrame.add(pvp);
		
	    easy=new JButton("Easy");
	    easy.setBounds(650,420,300,30);
	    easy.setBackground(new Color(137,141,147));
	    easy.setFont(new Font("Tahoma", Font.BOLD, 15));
	    mainFrame.add(easy);
	    easy.setVisible(false);
	    
	    hard=new JButton("Hard");
	    hard.setBounds(650,470,300,30);
	    hard.setBackground(new Color(137,141,147));
	    hard.setFont(new Font("Tahoma", Font.BOLD, 15));
	    mainFrame.add(hard);
	    hard.setVisible(false);
	    
	    //image= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/tictactoe.jpg"));
	    image= ImageIO.read(new File("/home/tanisha/Documents/SDL-master/SDL/src/tictactoe2.png"));
	    Image dim = image.getScaledInstance(720,200, Image.SCALE_SMOOTH);
		ImageIcon imIcon = new ImageIcon(dim);	   
		imageLabel=new JLabel(imIcon);
		imageLabel.setBounds(290,100,720,200);
        mainFrame.add(imageLabel);  
	    this.setVisible(true); //set visibility of frame to true
	    
    
    }
	
	public JLabel createLabel(JLabel l ,String name, int d) {

	    l = new JLabel(name);
	    l.setBounds(330,d,100,30);
	    l.setOpaque(true);
	    //change color of the JLabel
	    l.setBackground(Color.white);
	    l.setFont(new Font("Seriff",Font.BOLD+Font.ITALIC,15));
	    l.setVisible(true);
	    return l;
	}
	public JTextField createTextfield(JTextField t,int d) {
		t = new JTextField();
	    t.setBounds(440,d,300,30);
	    t.setVisible(true);
        return t;
	}
	


}