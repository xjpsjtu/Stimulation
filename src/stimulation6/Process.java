package stimulation6;
//Process.java  
//�ı�budget���۲�value�ı仯
import stimulation4.ArrivedP;
import stimulation4.Parti;
import stimulation4.Pset;
import stimulation4.SampleS;
import stimulation4.SelectP;

import java.lang.Math;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
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
		int i;

		// �����stage
		for (i = l; i >= 1; i--) {
			ST[i] = (int) Math.floor(T * Math.pow(2, -i));
		}
	}
	//�����û�����
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
	//�����û�����
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
	public void start() {
		int i;
		for (i = 0; i < n; i++) {
			Parti p = new Parti(T,  ep, L, U);
			p.id = i + 1;
			this.N.addP(p);
		}
	}
	
	public double calcuLam1() {
		return 2 * Math.pow(beta, T) * Math.pow(U / L, ll) / (1 - ep);
	}

	public double calcuLam2() {
		return Math.pow(U / L, ll - 1) / ep;
	}

	public static double disc(double x, double t) {
		return Math.pow(x, t);
	}

	public static double handle(Process pro){
		final double beta = pro.beta;
		Collections.sort(pro.N.list, new Comparator<Parti>(){

			@Override
			public int compare(Parti p1, Parti p2) {
				// TODO �Զ����ɵķ������
				if( p1.efficiency()*disc(beta, p1.arrival_time) > p2.efficiency()*disc(beta, p2.arrival_time))return -1;
				else if(p1.efficiency()*disc(beta, p1.arrival_time) < p2.efficiency()*disc(beta, p2.arrival_time)) return 1;
				else return 0;
			}
			
		});
		double value = 0;
		double bud  = 0;
		for(int i = 0; i < pro.N.list.size(); i++){
			bud+=pro.N.list.get(i).cost;
			if(bud < pro.B)value += pro.N.list.get(i).value*disc(pro.beta, pro.N.list.get(i).arrival_time);
			else break;
		}
		return value;
	}
	public static double handleRandom(Process pro){
		//System.out.println("RAMBUDGET = "+pro.B);
		double value = 0;
		double bud = 0;
		for(int t = 1; t < pro.T; t++){
			for(int i = 0 ; i < pro.N.list.size(); i++){
				if(pro.N.list.get(i).arrival_time == t){
					if(Math.random()>0.5){
						if(bud + pro.N.list.get(i).cost < pro.B){
							bud += pro.N.list.get(i).cost;
							double x = Math.random();
							double calt = pro.N.list.get(i).arrival_time * x + pro.N.list.get(i).departure_time * (1-x);
							value += pro.N.list.get(i).value * disc(pro.beta, calt);
						}
					}
				}
			}
		}
		return value;
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
	
	public static void main(String[] args) throws Exception{
		// TODO �Զ����ɵķ������
		PrintWriter out = new PrintWriter("BudgetChange&Random.txt");
		double x = 0.001;
		for(int j = 0; j < 200; j++){
			double value = 0;
			double optValue = 0;
			double ranValue = 0;
			for(int round = 1; round <= 200; round++){
				Process pro = new Process(j*100, 50, 2, 1, 500, 4, 0.0015, 0.1, 0.9);
				pro.start();
				//pro.showInfo();
				optValue += handle(pro);
				ranValue += handleRandom(pro);
				double lam1 = pro.calcuLam1();
				double lam2 = pro.calcuLam2();
				pro.lam =lam1*(1-4*x)+4*x*lam2;
				int t;
				double budget = Math.pow(2, -pro.ll) * pro.B;
				int l = pro.l;
				for(t = 1; t < pro.T; t++){
					for (Parti p : pro.N.list) {
						if (p.arrival_time == t) {
							pro.A.addP(p);
						}
					}
					Collections.sort(pro.A.list);
					for (int i = 0; i < pro.A.list.size(); i++) {
						Parti p = pro.A.list.get(i);
						if (p.value * disc(pro.beta, t) / p.cost >= pro.rho
								&& p.value * disc(pro.beta, t) / pro.rho + pro.S.totalPay() <= budget) {
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
							pro.Sample.addP(p);
						}
					}
					pro.A = pro.A.removeS();
					pro.A = pro.A.removeD(t);
					if (t == pro.ST[pro.l]) {
						if (pro.Sample.list.isEmpty() == false) {
							pro.rho = pro.Sample.getThreshold(budget, l, pro.ll, pro.U, pro.L, pro.lam);
						}
						pro.l--;
						budget = budget * 2;
					}
				}
				
			}
			value = value/500;
			optValue = optValue/500;
			ranValue /= 500;
			out.println(j+"     "+value+"      "+optValue+"      "+ranValue);
		}
		out.close();
	}

}