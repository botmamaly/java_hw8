import java.io.*;
import java.net.*;

public class Server{
	private ServerSocket myServerSocket;
	private Database database;
	private Integer clientNum;
	public Server(Integer A,Integer B,Integer serverPort) {
        try {
        	myServerSocket = new ServerSocket(serverPort);//build a TCP server
 
        } catch (java.io.IOException e) {
        	 e.printStackTrace();
        }
        database=new Database(A,B);
        clientNum=1;
	}
	public void run(){
		System.out.println("Listening");
        while(true){
        	Socket socket;
			try{
				socket = myServerSocket.accept();
				ServerThread st=new ServerThread(database,clientNum,socket);
				st.start();
				st.join();
				clientNum++;
			}catch (IOException e) {
				e.printStackTrace();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
	    }
	}
	public static void main(String[] args){
		if(args.length==3){
			Server server=new Server(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
			System.out.println("Port :"+args[2]);
			System.out.println("Resource: <A :"+args[0]+" B :"+args[1]+" >");
			server.run();
		}
		else
			System.out.println("Error!!Please enter <A> <B> <Port>");
	}
}