import java.io.IOException;
import java.util.Scanner;

public class MainGame {
 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        int choice;
        String ch=null;
        Scanner sc;
        
     do {
        //System.out.println("Choose: 1.Comp vs Player \n 2:Plyer vs Player");
        sc=new Scanner(System.in);
        //choice=sc.nextInt();
        
		Controller c=new Controller(-1);
		while(c.gameType==-1) {
			System.out.println("gameType is -1");
		}
		choice=c.gameType;
       // System.out.println("This "+javax.swing.SwingUtilities.isEventDispatchThread());
		
		if(choice == 2) {
				new Thread() {
					public void run() { 
						javax.swing.SwingUtilities.isEventDispatchThread();
						c.readChoice();
						while(true)	{	
						  javax.swing.SwingUtilities.isEventDispatchThread();
						  if(c.myMove==true) {
							c.myMove=false;
							c.read();
							System.out.println("Returning");
						
						  }
						  //if(c.gameType == 1) {
							//  break;
						  //}
						}
					}
				}.start();
		 }
	    System.out.println("Thread end");
		System.out.println("Do you want to continue");
		ch=sc.next();
		
	}while(ch.equals("y")) ;
     sc.close();
	}
	
}
