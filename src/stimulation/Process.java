package stimulation;

import java.lang.Math;
import java.util.Collections;

public class Process {
	int T, U, L, n, l,ll; // ������̵�Ԥ�㣬��ʱ�䣬Ч�ʵ����½磬��������stage�ĸ���
	double B;
	double ep; // ÿ���˵�cost���ܹ���budget�ı���
	double rho;
	int[] ST = new int[100]; // ���е�stage
	int total_value = 0; // �ܼ�ֵ����ʼ��Ϊ0
	Pset N = new Pset();
	SelectP S = new SelectP();
	ArrivedP A = new ArrivedP();
	SampleS Sample = new SampleS();

	// ������
	public Process(double B, int T, int U, int L, int n, int l, double ep,
			double rho) {
		this.B = B;
		this.T = T;
		this.U = U;
		this.L = L;
		this.n = n;
		this.l = l;
		this.ll = l;
		this.ep = ep;
		this.rho = rho;
		int i;

		// �����stage
		for (i = l; i >= 1; i--) {
			ST[i] = (int) Math.floor(T * Math.pow(2, -i));
		}
	}

	// ��ʼ�����������һ��process
	public void start() {
		int i;
		for (i = 0; i < n; i++) {
			Parti p = new Parti(T, B, ep, L, U);
			p.id = i + 1;
			this.N.addP(p);
		}

		// Ԥ��Ϊ10000��ʱ��Ϊ2000��Ч��Ϊ1��2��������Ϊ200��stageΪ10��cost��budget�ı���ΪС��0.01����ʼ�ٽ�ֵΪ0.9
		// Process pro = new Process(10000, 2000, 2, 1, 200, 10, 0.01 , 0.9);
	}
	
	
	public void showInfo(){
		System.out.println("----------------ģ�����Ϣ-----------------");
		System.out.println("��Ԥ��Ϊ��"+this.B);
		System.out.println("��ʱ��Ϊ"+this.T);
		System.out.println("������Ϊ��"+this.n);
		System.out.print("�ֳɣ� "+this.l+" ���׶Σ�ʱ��ֱ��ǣ� ");
		for(int i = 1; i <= l; i++)
			System.out.print(ST[i]+",");
		System.out.println();
		
		for(int i=l;i>=1;i--){
			System.out.print(ST[i]+",");
		}
		System.out.println("�������û�����Ϣ: (����ʱ�䣬�뿪ʱ�䣬��ֵ������)");
		for(Parti p:this.N.list){
			System.out.println("�û�: "+p.id+" ����ϢΪ"+"("+p.arrival_time+","+p.departure_time+","+p.value+","+p.cost+")");
		}
		System.out.println("----------------------------------------");
	}
	
	public static double disc(int t, int T){
		double x = Math.pow(Math.E, (double)(-t)/T);
		return x;
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Process pro = new Process(1500, 50, 2, 1, 300, 4, 0.0015, 0.9);
//		int t;
//		for(t = 1; t < 300; t++)
//			System.out.println("T = "+pro.T+" "+"t = "+t+" "+"disc = "+disc(t,pro.T));
//		System.out.println("discount: "+disc(100,pro.T));
		pro.start();
		pro.showInfo();
		
		double budget = Math.pow(2, -pro.l) * pro.B;
		int t;
		int l = pro.l;
		double value = 0;
		for (t = 1; t <= pro.T; t++) {
			System.out.println("-----------------ʱ��"+t+"----------------------");
			System.out.println("����׶ε�Ԥ��Ϊ��"+budget+","+"�ٽ�ֵΪ: "+pro.rho);
			long t1 = System.currentTimeMillis();
			System.out.println("t1 = "+t1);
			for (Parti p : pro.N.list) {
				if (p.arrival_time == t){
					p.show();
					pro.A.addP(p);
					System.out.println("��ʱ��"+t+"�����һ���û�"+p.id);
				}
			}
			Collections.sort(pro.A.list);
			for (int i =0;i<pro.A.list.size();i++) {
				Parti p = pro.A.list.get(i);
				System.out.println("*******���û���"+p.id+"�����ж�********");
				p.show();
				System.out.println(t);
				System.out.println(p.value * disc(t,pro.T) / p.cost+"##"+pro.rho);
				System.out.println(p.value * disc(t,pro.T) / pro.rho
								+ pro.S.totalPay()+"##"+budget);
			
				if (p.value * disc(t,pro.T) / p.cost >= pro.rho
						&& p.value * disc(t,pro.T) / pro.rho
								+ pro.S.totalPay() <= budget) {
					System.out.println("��ѡ����һ����");
					p.pay = p.value * disc(t,pro.T) / pro.rho;
					pro.Sample.addP(p);
					pro.S.addP(p);
					p.select = true;
					value += p.value * disc(t,pro.T);
					pro.A.list.remove(p);
				}
			}
			for (int i =0;i<pro.A.list.size();i++) {
				Parti p = pro.A.list.get(i);
				if (p.departure_time == t) {
					pro.A.list.remove(p);
					pro.Sample.addP(p);
				}
			}
			if (t == pro.ST[pro.l]) {
				System.out.println("&&&&&��Ҫ�����ٽ�ֵ��&&&&&");
				if(pro.Sample.list.isEmpty()==false){
					pro.rho = pro.Sample.getThreshold(budget, l, pro.ll, pro.U, pro.L);
				}
				pro.l--;
				budget = budget * 2;
			}
			long t2 = System.currentTimeMillis();
			System.out.println("t2 = "+t2);
			System.out.println("���ʱ����ʱ__"+(t2-t1));
			System.out.print("��ѡ�е�idΪ");
			pro.S.Pshow();
			System.out.println();
			System.out.print("Sample��Ϊ");
			pro.Sample.Pshow();
			System.out.println();
			System.out.print("��ѡ����Ϊ");
			pro.A.Pshow();
			System.out.println();
			
		}
		System.out.println(value);
	}

}
