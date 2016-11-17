package stimulation11;
//Process.java  
//固定预算，改变总人数，看被选中的人数比例
import stimulation4.ArrivedP;
import stimulation4.Parti;
import stimulation4.Pset;
import stimulation4.SampleS;
import stimulation4.SelectP;

import java.lang.Math;
import java.util.Collections;
import java.io.*;
public class Process {
	int T, U, L, n, l, ll; // 这个过程的预算，总时间，效率的上下界，总人数，stage的个数
	double B;
	double beta; // discount因子
	double ep; // 每个人的cost与总共的budget的比例
	double rho; // 初始的临界值
	double lam; // 计算临界值的时候的分母
	int[] ST = new int[100]; // 所有的stage
	double total_value = 0; // 总价值，初始化为0
	Pset N = new Pset();
	SelectP S = new SelectP();
	ArrivedP A = new ArrivedP();
	SampleS Sample = new SampleS();
	
	// 构造器, 初始化预算，总时间，效率界，总人数，stage个数，cost占budget比例上限，初始临界值
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

		// 构造出stage
		for (i = l; i >= 1; i--) {
			ST[i] = (int) Math.floor(T * Math.pow(2, -i));
		}
	}
	//创建用户集合
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
	//设置用户集合
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

	public static double disc(double x, int t) {
		return Math.pow(x, t);
	}

	public static double handle(Process pro){
		Collections.sort(pro.N.list);
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
							value += pro.N.list.get(i).value * disc(pro.beta, t);
						}
					}
				}
			}
		}
		return value;
	}
	public void showInfo() {
		System.out.println("----------------模拟的信息-----------------");
		System.out.println("总预算为：" + this.B);
		System.out.println("总时间为" + this.T);
		System.out.println("总人数为：" + this.n);
		System.out.print("分成： " + this.l + " 个阶段，时间分别是： ");
		for (int i = 1; i <= l; i++)
			System.out.print(ST[i] + ",");
		System.out.println();

		// for(int i=l;i>=1;i--){
		// System.out.print(ST[i]+",");
		// }
		System.out.println("下面是用户的信息: (到达时间，离开时间，价值，花费)");
		for (Parti p : this.N.list) {
			System.out.println("用户: " + p.id + " 的信息为" + "(" + p.arrival_time + "," + p.departure_time + "," + p.value
					+ "," + p.cost + ")");
		}
		System.out.println("----------------------------------------");
	}
	
	public static void main(String[] args) throws Exception{
		// TODO 自动生成的方法存根
//		PrintWriter out = new PrintWriter("LambdaChangeValue.txt");
		PrintWriter out = new PrintWriter("AgentchangeUti.txt");
		double x = 0.01;
		for(int B = 6000; B <= 15000; B+=1000){
		for(int n = 200; n <= 800; n+=20){
			double value = 0;
			double optValue = 0;
			double ranValue = 0;
			int SNum = 0;
			int BSum = 0;
				for(int round = 1; round <= 200; round++){
					Process pro = new Process(B, 50, 2, 1, n, 4, 0.0015, 0.1, 0.9);
					int j=1;
				pro.start();
				//pro.showInfo();
//				optValue += handle(pro);
//				ranValue += handleRandom(pro);
				double lam1 = pro.calcuLam1();
				double lam2 = pro.calcuLam2();
				pro.lam =lam1*(1-j*x)+j*x*lam2;
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
							BSum += p.pay;
							pro.Sample.addP(p);
							pro.S.addP(p);
							p.select = true;
							SNum++;
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
			value = value/200;
			SNum /= 200;
			BSum /= 200;
			double ratio = (double)BSum/B;
//			optValue = optValue/200;
//			ranValue /= 200;
			System.out.println(n);
			out.println(B + "		" +n+"		"+ratio);
		}
	}
		out.close();
	}

}
