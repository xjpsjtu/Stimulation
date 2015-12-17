package stimulation4;

import java.lang.Math;
import java.util.Collections;
import java.io.*;

//����ʵ���Ŀ���ǣ��ı�budget,�۲�value�ı仯

public class Process {
	int T, U, L, n, l, ll; // ������̵�Ԥ�㣬��ʱ�䣬Ч�ʵ����½磬��������stage�ĸ���
	double B;
	double beta; // discount����
	double ep; // ÿ���˵�cost���ܹ���budget�ı���
	double rho; // ��ʼ���ٽ�ֵ
	double lam; // �����ٽ�ֵ��ʱ��ķ�ĸ
	int[] ST = new int[100]; // ���е�stage
	double total_value = 0; // �ܼ�ֵ����ʼ��Ϊ0
	Pset N = new Pset();
	SelectP S = new SelectP();
	ArrivedP A = new ArrivedP();
	SampleS Sample = new SampleS();

	// ������, ��ʼ��Ԥ�㣬��ʱ�䣬Ч�ʽ磬��������stage������costռbudget�������ޣ���ʼ�ٽ�ֵ
	public Process(int T, int U, int L, int n, int l, double ep, double rho, double beta) {
//		this.B = B;
		this.T = T;
		this.U = U;
		this.L = L;
		this.n = n;
		this.l = l;
		this.ll = l;
		this.ep = ep;
		this.rho = rho;
		this.beta = beta;
		// this.lam = Math.pow((U/L), l-1) /ep;
		int i;

		// �����stage
		for (i = l; i >= 1; i--) {
			ST[i] = (int) Math.floor(T * Math.pow(2, -i));
		}
	}
	public Process(double B, int T, int U, int L, int n, int l, double ep, double rho, double beta) {
		this.B = B;
		this.T = T;
		this.U = U;
		this.L = L;
		this.n = n;
		this.l = l;
		this.ll = l;
		this.ep = ep;
		this.rho = rho;
		this.beta = beta;
		// this.lam = Math.pow((U/L), l-1) /ep;
		int i;

		// �����stage
		for (i = l; i >= 1; i--) {
			ST[i] = (int) Math.floor(T * Math.pow(2, -i));
		}
	}
	public void setB(double B){
		this.B = B;
	}
	public void setN(Pset N){
		for(int i = 0; i < N.list.size(); i++){
			Parti p = N.list.get(i);
			Parti q = new Parti();
			q.id = p.id;
			q.arrival_time = p.arrival_time;
			q.departure_time = p.departure_time;
			q.cost = p.cost;
			q.value = p.value;
			q.pay = 0;
			q.select = false;
			this.N.addP(q);
		}
	}
	// ��ʼ�����������һ��process
	public void start() {
		int i;
		for (i = 0; i < n; i++) {
			Parti p = new Parti(T,  ep, L, U);
			p.id = i + 1;
			this.N.addP(p);
		}

		// Ԥ��Ϊ10000��ʱ��Ϊ2000��Ч��Ϊ1��2��������Ϊ200��stageΪ10��cost��budget�ı���ΪС��0.01����ʼ�ٽ�ֵΪ0.9
		// Process pro = new Process(10000, 2000, 2, 1, 200, 10, 0.01 , 0.9);
	}

	public static Pset CreateN(int n, int T, double ep, int L, int U){
		Pset N = new Pset();
		int i;
		for(i = 0; i < n; i++){
			Parti p = new Parti(T,ep,L,U);
			p.id = i + 1;
			N.addP(p);
		}
		return N;
	}
	public void showInfo() {
		System.out.println("----------------ģ�����Ϣ-----------------");
		System.out.println("��Ԥ��Ϊ��" + this.B);
		System.out.println("��ʱ��Ϊ" + this.T);
		System.out.println("������Ϊ��" + this.n);
		System.out.print("�ֳɣ� " + this.l + " ���׶Σ�ʱ��ֱ��ǣ� ");
		for (int i = 1; i <= l; i++)
			System.out.print(ST[i] + ",");
		System.out.println();

		// for(int i=l;i>=1;i--){
		// System.out.print(ST[i]+",");
		// }
		System.out.println("�������û�����Ϣ: (����ʱ�䣬�뿪ʱ�䣬��ֵ������)");
		for (Parti p : this.N.list) {
			System.out.println("�û�: " + p.id + " ����ϢΪ" + "(" + p.arrival_time + "," + p.departure_time + "," + p.value
					+ "," + p.cost + ")");
		}
		System.out.println("----------------------------------------");
	}

	public double calcuLam1() {
		return 2 * Math.pow(beta, T) * Math.pow(U / L, ll) / (1 - ep);
	}

	public double calcuLam2() {
		return Math.pow(U / L, ll - 1) / ep;
	}

	public static double disc(double x, int t) {
		return Math.pow(x, t);
	}

	public static double handle(Process pro){
		Collections.sort(pro.N.list);
		double value = 0;
		double bud  = 0;
		for(int i = 0; i < pro.N.list.size(); i++){
			bud+=pro.N.list.get(i).cost;
			if(bud < pro.B)value += pro.N.list.get(i).value;
			else break;
		}
		return value;
	}
	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		
		PrintWriter out = new PrintWriter("NumOfAgent.txt");
		Process pro = new Process( 50, 2, 1, 300, 4, 0.0015, 0.1, 0.9); 	//T = 300, beta = 0.9
//		pro.start();
		Pset N = CreateN(pro.n, pro.T, pro.ep, pro.L,pro.U);
		double x = 0.001;
		for(int j = 1; j < 50; j++){
//			System.out.println("------------------");
//			Process pro = new Process(10000+j*500, 50, 2, 1, 300, 4, 0.0015, 0.1, 0.9); 	//T = 50, beta = 0.9
			pro.N = new Pset();
			pro.Sample = new SampleS();
			pro.A = new ArrivedP();
			pro.S = new SelectP();
			pro.l = pro.ll;
			pro.rho = 0.9;
			pro.setB(1000+j*20);
			pro.setN(N);
			double optValue = handle(pro);
			//optValue *= disc(pro.beta, pro.T/2);
//			System.out.println(pro.N);
//			for(int l = 0; l < 5; l++){
//				pro.N.list.get(l).show();
//			}
//			System.out.println(pro.B);
//			System.out.println(pro.B+"  "+pro.ep+"  "+pro.rho+"  "+pro.n+"  "+pro.beta+" "+pro.T+" "+pro.U+" "+pro.L+" "+"  "+pro.l+"  "+pro.ll+" "+pro.ep);

			double lam1 = pro.calcuLam1();
			double lam2 = pro.calcuLam2();
			
			

//			
			pro.lam =1*x*lam2;
//			System.out.println("lam1 = "+lam1);
//			System.out.println("lam2 = "+lam2);
//			System.out.println("lam = "+pro.lam);
			int t;
//			for (t = 1; t < pro.T; t++)
//				System.out.println("T = " + pro.T + " " + "t = " + t + " " + "disc = " + disc(pro.beta, t));
			
			//pro.showInfo();

			double budget = Math.pow(2, -pro.ll) * pro.B;
//			System.out.println("budget = "+budget);
			// int t;
			int l = pro.l;
			double value = 0;
			for (t = 1; t <= pro.T; t++) {
//				System.out.println("-----------------ʱ��" + t + "----------------------");
//				System.out.println("����׶ε�Ԥ��Ϊ��" + budget + "," + "�ٽ�ֵΪ: " + pro.rho);
				for (Parti p : pro.N.list) {
					if (p.arrival_time == t) {
//						p.show();
						pro.A.addP(p);
//						System.out.println("��ʱ��" + t + "�����һ���û�" + p.id);
					}
				}
				Collections.sort(pro.A.list);
				for (int i = 0; i < pro.A.list.size(); i++) {
					Parti p = pro.A.list.get(i);
//					System.out.println("*******���û���" + p.id + "�����ж�********");
//					p.show();
					// System.out.println(t);
//					System.out.println(p.value * disc(pro.beta, t) / p.cost + "##" + pro.rho);
//					System.out.println(p.value * disc(pro.beta, t) / pro.rho + pro.S.totalPay() + "##" + budget);

					if (p.value * disc(pro.beta, t) / p.cost >= pro.rho
							&& p.value * disc(pro.beta, t) / pro.rho + pro.S.totalPay() <= budget) {
//						p.show();
//						System.out.println("��ѡ����һ����");
						p.pay = p.value * disc(pro.beta, t) / pro.rho;
						pro.Sample.addP(p);
						pro.S.addP(p);
						p.select = true;
						value += p.value * disc(pro.beta, t);
						//pro.A.list.remove(p);
					}
				}
				for (int i = 0; i < pro.A.list.size(); i++) {
					Parti p = pro.A.list.get(i);
					if (p.departure_time == t) {
//						pro.A.list.remove(p);
						pro.Sample.addP(p);
					}
				}
				pro.A = pro.A.removeS();
				pro.A = pro.A.removeD(t);
				if (t == pro.ST[pro.l]) {
//					System.out.println("&&&&&��Ҫ�����ٽ�ֵ��&&&&&");
					if (pro.Sample.list.isEmpty() == false) {
						pro.rho = pro.Sample.getThreshold(budget, l, pro.ll, pro.U, pro.L, pro.lam);
					}
					pro.l--;
					budget = budget * 2;
				}
//				System.out.print("��ѡ�е�idΪ");
//				pro.S.Pshow();
//				System.out.println();
//				System.out.print("Sample��Ϊ");
//				pro.Sample.Pshow();
//				System.out.println();
//				System.out.print("��ѡ����Ϊ");
//				pro.A.Pshow();
//				System.out.println();

			}
//			System.out.print("��ѡ�е�idΪ");
//			pro.S.Pshow();
//			System.out.println();
			System.out.println(value);
			System.out.println(pro.S.totalPay());
			out.println(j+"     "+pro.B+"     "+value+"      "+optValue);
			
			}
		out.close();
	}

}
