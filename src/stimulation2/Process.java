package stimulation2;

import java.lang.Math;
import java.util.Collections;
import java.io.*;

//本次实验的目的是：固定beta，固定T，改变lam，观察花费的budget占总共Budget的比例的变化

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
		// this.lam = Math.pow((U/L), l-1) /ep;
		int i;

		// 构造出stage
		for (i = l; i >= 1; i--) {
			ST[i] = (int) Math.floor(T * Math.pow(2, -i));
		}
	}

	// 开始函数，构造出一个process
	public void start() {
		int i;
		for (i = 0; i < n; i++) {
			Parti p = new Parti(T, B, ep, L, U);
			p.id = i + 1;
			this.N.addP(p);
		}

		// 预算为10000，时间为2000，效率为1到2，总人数为200，stage为10，cost与budget的比例为小于0.01，初始临界值为0.9
		// Process pro = new Process(10000, 2000, 2, 1, 200, 10, 0.01 , 0.9);
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

	public double calcuLam1() {
		return 2 * Math.pow(beta, T) * Math.pow(U / L, l) / (1 - ep);
	}

	public double calcuLam2() {
		return Math.pow(U / L, l - 1) / ep;
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
		// TODO 自动生成的方法存根
		
		PrintWriter out = new PrintWriter("e1.txt");
		double x = 0.0005;
		for(int j = 1; j < 501; j++){
			
			Process pro = new Process(10000, 50, 2, 1, 300, 4, 0.0015, 0.1, 0.9); 	//T = 300, beta = 0.9
			pro.start();
			double lam1 = pro.calcuLam1();
			double lam2 = pro.calcuLam2();
			//pro.lam = (0.5*lam1 + 0.5*lam2);
			double optValue = handle(pro);
			
			int k = 1;
//			int j = 8;
			//pro.lam = (j*x*lam1 + (1- j*x)*lam2);
			pro.lam = k*x*lam2;
			System.out.println("lam1 = "+lam1);
			System.out.println("lam2 = "+lam2);
			System.out.println("lam = "+pro.lam);
			int t;
//			for (t = 1; t < pro.T; t++)
//				System.out.println("T = " + pro.T + " " + "t = " + t + " " + "disc = " + disc(pro.beta, t));
			
			//pro.showInfo();

			double budget = Math.pow(2, -pro.l) * pro.B;
			// int t;
			int l = pro.l;
			double value = 0;
			for (t = 1; t <= pro.T; t++) {
//				System.out.println("-----------------时刻" + t + "----------------------");
//				System.out.println("这个阶段的预算为：" + budget + "," + "临界值为: " + pro.rho);
				for (Parti p : pro.N.list) {
					if (p.arrival_time == t) {
//						p.show();
						pro.A.addP(p);
//						System.out.println("在时刻" + t + "添加了一个用户" + p.id);
					}
				}
				Collections.sort(pro.A.list);
				for (int i = 0; i < pro.A.list.size(); i++) {
					Parti p = pro.A.list.get(i);
//					System.out.println("*******对用户：" + p.id + "进行判断********");
//					p.show();
					// System.out.println(t);
//					System.out.println(p.value * disc(pro.beta, t) / p.cost + "##" + pro.rho);
//					System.out.println(p.value * disc(pro.beta, t) / pro.rho + pro.S.totalPay() + "##" + budget);

					if (p.value * disc(pro.beta, t) / p.cost >= pro.rho
							&& p.value * disc(pro.beta, t) / pro.rho + pro.S.totalPay() <= budget) {
//						System.out.println("又选择了一个！");
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
//					System.out.println("&&&&&需要更新临界值了&&&&&");
					if (pro.Sample.list.isEmpty() == false) {
						pro.rho = pro.Sample.getThreshold(budget, l, pro.ll, pro.U, pro.L, pro.lam);
					}
					pro.l--;
					budget = budget * 2;
				}
//				System.out.print("被选中的id为");
//				pro.S.Pshow();
//				System.out.println();
//				System.out.print("Sample集为");
//				pro.Sample.Pshow();
//				System.out.println();
//				System.out.print("候选集和为");
//				pro.A.Pshow();
//				System.out.println();

			}
			System.out.println(value);
			System.out.println(pro.S.totalPay());
			out.println(j+"     "+value+"      "+pro.S.totalPay()+"      "+optValue);
			
			}
		out.close();
	}

}
