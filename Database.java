public class Database{
	private Integer A;
	private Integer B;
	private Integer maxA;
	private Integer maxB;
	public Database(int A,int B){
		this.A=A;
		this.B=B;
		maxA=A;
		maxB=B;
	}
	public int getA(){
		return A;
	}
	public int getB(){
		return B;
	}
	public void take(int requestA,int requestB){
		A=A-requestA;
		B=B-requestB;
	}
	public void replenish(){
		A=maxA;
		B=maxB;
	}
}