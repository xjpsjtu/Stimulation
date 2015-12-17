package test;
import stimulation3.Rand;
class P{
	int id;
	P(int n){
		id = n;
	}
}
public class Test {
	public  static void handle(P p){
		p.id = 100;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		double p = 0.1;
		for(int k = 1; k < 100; k++)
//			System.out.println(Math.pow(1-p, k)*p+"   ");
			System.out.println(Rand.P_rand(20));
	}

}
