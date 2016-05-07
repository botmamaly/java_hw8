import java.io.*;
import java.net.*;

class ServerThread extends Thread{
	private Socket socket;
	private Database data;
	private Integer clientNum;
    private Integer clientA;
    private Integer clientB;
	public ServerThread(Database inData,Integer clientNum,Socket socket) {
        this.socket=socket;
        this.clientNum=clientNum;
        this.data=inData;
    }
	private void getClientRequest(){
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			clientA=Integer.parseInt(br.readLine());
        	clientB=Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	private void respond(int type){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			if(type==1)
				bw.write("[Error] : Resource quantity insufficient!");
			else
				bw.write("Service finished!");
			bw.newLine();
			bw.flush();
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void transcation(){
		int responseType;
		System.out.print("[Client_"+clientNum+"] :");
		if(data.getA()>=clientA&&data.getB()>=clientB){
			System.out.println("取走商品 - > A :"+clientA+" B :"+clientB);
			data.take(clientA,clientB);
			System.out.println("                商品個數 : <A : "+data.getA()+" , B : "+data.getB()+" >");	
			responseType=0;
		}
		else{
			System.out.println("resource insufficient!Error...");
			responseType=1;//Error response
			clientA=0;
			clientB=0;
		}
		System.out.println("捕貨 - > A:"+(clientA)+" B :"+(clientB));
		data.replenish();//replenish database
		System.out.println("                商品個數 : <A : "+data.getA()+" , B : "+data.getB()+" >");	
		respond(responseType);
	}
	public void run(){
	    getClientRequest();
	    transcation();
	}
}