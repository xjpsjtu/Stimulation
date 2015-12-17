package stimulation1;

import java.util.*;

public class Parti implements Comparable<Parti> {
	int id; // �û���ID
	int arrival_time, departure_time; // ������뿪ʱ��
	double cost, value, pay; // ���ѣ���ֵ������
	boolean select; // ��־�Ƿ�ѡ��

	// ��ʼ��һ���û�������������뿪ʱ�䣬���ѣ���ֵ�����棬Ĭ��û�б�ѡ��
	public Parti(int T, double B, double ep, int L, int U) {
		int t1 = (int) (1+Math.random() * (T-1));
		int t2 = (int) (1+Math.random() * (T-1));
		if (t1 < t2) {
			this.arrival_time = t1;
			this.departure_time = t2;
		} else {
			this.arrival_time = t2;
			this.departure_time = t1;
		}
		this.cost = (int) (0.0001*B+Math.random() * ep * B);
		this.value = (int) (L * this.cost + Math.random()
				* (U * this.cost - L * this.cost));
		this.select = false;
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
		System.out.println("�û�: "+this.id+" ����ϢΪ"+"("+this.arrival_time+","+this.departure_time+","+this.value+","+this.cost+")");
	}
}
