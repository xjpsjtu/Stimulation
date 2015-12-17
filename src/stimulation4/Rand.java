package stimulation4;

public class Rand {
	public static double P_rand(double lambda){
		double x = 0, b = 1, c = Math.exp(-lambda),u;
		do{
			u = Math.random();
			b *= u;
			if(b >= c)
				x++;
		}while(b>=c);
			return x;
	}
	public static double Bur_rand(double p){	//pΪ��Ŭ��ʵ�鷢���ĸ���
		double u = Math.random();
		if(u <= p)return 1;
		else return 0;
	}
	
	public static double G_rand(double p){	//���ηֲ�������
		double x = 0,u;
		do{
			u = G_rand(p);
			if(u == 0)
				x++;
		}while(u == 0);
		return x+1;
	}
	
//	public void main(String[] args){
//		for(int i = 0 ; i < 10; i++){
//			System.out.print(Bur_rand(0.3)+" ");
//		}
//	}
}
