import java.io.*;
import java.net.*;
  
public class Client{
	public static void main(String[] args){
		try {
			System.out.println("Ip: "+args[2]+" Port : "+args[3]);
			System.out.println("Resourse requirement : < A : "+args[0]+" B: "+args[1]+" >");
			System.out.println("Connecting...\n\n");
			Socket client=new Socket(args[2], Integer.parseInt(args[3]));//client socket
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			/*client write data to server*/
			bw.write(args[0]);//A nums
			bw.newLine();
			bw.flush();//send data
			bw.write(args[1]);//B nums
			bw.newLine();
			bw.flush();//send data
			BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println(br.readLine());
			client.close();
			bw.close();
			br.close();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			System.out.println("Socket連線有問題 !");
			System.out.println(e);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}