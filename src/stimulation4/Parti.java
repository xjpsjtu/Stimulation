package stimulation4;

import java.util.*;

public class Parti implements Comparable<Parti> {
	public int id; // �û���ID
	public int arrival_time; // ������뿪ʱ��
	public int departure_time;
	public double cost; // ���ѣ���ֵ������
	public double value;
	public double pay;
	public boolean select; // ��־�Ƿ�ѡ��
//	boolean sample;
//	boolean arrived;

	// ��ʼ��һ���û�������������뿪ʱ�䣬���ѣ���ֵ�����棬Ĭ��û�б�ѡ��
	public Parti(){};
	public Parti(int T,  double ep, int L, int U) {
		double minB = 5000;
		int t1 = (int) (1+Math.random() * (T-1));
		int t2 = (int) (1+Math.random() * (T-1));
//		int t1 = (int)Rand.G_rand(0.1);
//		t1 = t1<=T?t1:T;
//		double u = Math.random();
//		int t2 =(int)(u * t1 + (1-u) * T);
		if (t1 < t2) {
			this.arrival_time = t1;
			this.departure_time = t2;
		} else {
			this.arrival_time = t2;
			this.departure_time = t1;
		}
		this.cost =  (0.0001*minB+Math.random() * ep * minB);
		this.value =  (L * this.cost + Math.random()
				* (U * this.cost - L * this.cost));
		this.select = false;
//		this.sample = false;
//		this.arrived = false;
		this.pay = 0;
	}

	// �����û���Ч��
	public double efficiency() {
		return value / cost;
	}

	public int compareTo(Parti p) {
		// TODO �Զ����ɵķ������
		if (this.efficiency() < p.efficiency()) {
			return -1;
		}
		if (this.efficiency() > p.efficiency()) {
			return 1;
		}
		return 0;
	}
	
	public void show(){
		System.out.println("�û�: "+this.id+" ����ϢΪ"+"("+this.arrival_time+","+this.departure_time+","+this.value+","+this.cost+","+this.select+")");
	}
}
