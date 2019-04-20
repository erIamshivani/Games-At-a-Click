import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.net.ServerSocket;

/*
 * A chat server that delivers public and private messages.
 */
public class Server {

  // The server socket.
  private static ServerSocket serverSocket = null;
  // The client socket.
  private static Socket clientSocket = null;

  // This chat server can accept up to maxClientsCount clients' connections.
  private static final int maxClientsCount = 10;
  private static final clientThread[] threads = new clientThread[maxClientsCount];

  public static void main(String args[]) {

    // The default port number.
    int portNumber = 12345;
      System.out
          .println("Usage: java MultiThreadChatServer <portNumber>\n"
              + "Now using port number=" + portNumber);
  
    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    /*
     * Create a client socket for each connection and pass it to a new client
     * thread.
     */
    while (true) {
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxClientsCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new clientThread(clientSocket, threads)).start();
            System.out.println("Connected to :"+i);
            break;
          }
        }
        if (i == maxClientsCount) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("Server too busy. Try later.");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}

class clientThread extends Thread {

  private DataInputStream is = null;
  private DataOutputStream os = null;
  private ObjectOutputStream out=null;
  private ObjectInputStream in=null;
  private Socket clientSocket = null;
  private final clientThread[] threads;
  private int maxClientsCount;

  public clientThread(Socket clientSocket, clientThread[] threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    maxClientsCount = threads.length;
  }

  @SuppressWarnings("unchecked")
public void run() {
    int maxClientsCount = this.maxClientsCount;
    clientThread[] threads = this.threads;

    try {
      /*
       * Create input and output streams for this client.
       */
      is = new DataInputStream(clientSocket.getInputStream());
      // os = new PrintStream(clientSocket.getOutputStream());
      os = new DataOutputStream(clientSocket.getOutputStream());
      out=new ObjectOutputStream(clientSocket.getOutputStream());
      in=new ObjectInputStream(clientSocket.getInputStream()); 

      // adding clients to the client queue
      for (int i = 0; i < maxClientsCount; i++) {
			if(threads[i]==this){
					
				 try{
								      
					   if(i%2==0) {
						   System.out.println("WRTITING TO PLAYER 0");
						   threads[i].os.writeInt(1) ;
		                }
		                
						if (i%2==1) {
							 System.out.println("WRTITING TO PLAYER 1");
						     threads[i].os.writeInt(2) ;
						} 
					}
					catch(Exception e) {} 
		              //   }
		      }   
      }
      
      while (true) {
  
       System.out.println("Here Reading arraylist,winner and play for : " + this);
	   ArrayList<Integer> c =new ArrayList<Integer>();
	   c=(ArrayList<Integer>)in.readObject();
       int winner=is.readInt();
       String play=(String)in.readObject();
       
       
       //exchanging winner id, initially set to 0 implies no one.
       for (int i = 0; i < maxClientsCount; i++) {
    		if(threads[i]==this){
				try{
			      if(i%2==0) {
	                if(threads[i+1]!=null) {  
          			 threads[i+1].os.writeInt(winner);
				    }
                  }
	                
				  if (i%2==1 && threads[i-1]!=null) {
          			threads[i-1].os.writeInt(winner);
				   } 
				}
				catch(Exception e) {
					System.out.println("Exception In Winner");
				} 
    		} 

        }
   
       //exchanging the move
        for (int i = 0; i < maxClientsCount; i++) {

        	System.out.println("Sending the move to opponent of : "+this);
        	// checking if it is the current client writing
			if(threads[i]==this){
					
				  try{
					   if(i%2==0 && threads[i+1]!=null) {
						   // sending the written value to opponent 
				           System.out.println("Here player 0");
		                   threads[i+1].out.writeObject(c);
		                   break;
					   }
		                
						if (i%2==1 && threads[i-1]!=null) {
							// sending the written value to opponent
							 System.out.println("Here player 1");
							 threads[i-1].out.writeObject(c);
							 break;
						} 
					}
					catch(Exception e) {} 
		            
		      }   
        }
        /*
        // writing request to play again
        for (int i = 0; i < maxClientsCount; i++) {

        	System.out.println("Play Again Message received from : " + this);
        	// checking if it is the current client writing
			if(threads[i]==this){
					
				  try{
					   if(i%2==0 && threads[i+1]!=null) {
						   // sending the written value to opponent 
				           System.out.println("Here player 0");
		                   threads[i+1].out.writeObject(play);
		                   break;
					   }
		                
						if (i%2==1 && threads[i-1]!=null) {
							// sending the written value to opponent
							 System.out.println("Here player 1");
							 threads[i-1].out.writeObject(play);
							 break;
						} 
					}
					catch(Exception e) {} 
		            
		      }   
        }
        
        
        if(play.equals("Play Again?")) {
        	System.out.println("Play is equal to Play Again");
        	
        	
        	// reading response to play again
	        for (int i = 0; i < maxClientsCount; i++) {
	
	        	System.out.println("Waiting for Play Again Response ");
	        	// checking if it is the current client writing
				if(threads[i]==this){
						
					  try{
						   if(i%2==0 && threads[i+1]!=null) {
							   // sending the written value to opponent 
					           System.out.println("Reading for player 0");
			                   play=(String)threads[i+1].in.readObject();
			                   break;
						   }
			                
							if (i%2==1 && threads[i-1]!=null) {
								// sending the written value to opponent
								 System.out.println("Reading for player 1");
								 play=(String)threads[i-1].in.readObject();
								 break;
							} 
						}
						catch(Exception e) {} 
			            
			      }   
	        }
        	
	        // writing response to play again
	        for (int i = 0; i < maxClientsCount; i++) {
	
	        	System.out.println("Here Write Play Again Response ");
	        	// checking if it is the current client writing
				if(threads[i]==this){
						
					  try{
						   if(i%2==0 && threads[i+1]!=null) {
							   // sending the written value to opponent 
					           System.out.println("Responded to player 0 ");
			                   threads[i+1].out.writeObject(play);
			                   break;
						   }
			                
							if (i%2==1 && threads[i-1]!=null) {
								// sending the written value to opponent
								 System.out.println("Responded to Player 1 ");
								 threads[i-1].out.writeObject(play);
								 break;
							} 
						}
						catch(Exception e) {} 
			            
			      }   
	        }
	        
        }*/
        
    }
      } catch (IOException e) {
    } catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  }
}