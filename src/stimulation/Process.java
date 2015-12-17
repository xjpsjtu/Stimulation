package stimulation;

import java.lang.Math;
import java.util.Collections;

public class Process {
	int T, U, L, n, l,ll; // 这个过程的预算，总时间，效率的上下界，总人数，stage的个数
	double B;
	double ep; // 每个人的cost与总共的budget的比例
	double rho;
	int[] ST = new int[100]; // 所有的stage
	int total_value = 0; // 总价值，初始化为0
	Pset N = new Pset();
	SelectP S = new SelectP();
	ArrivedP A = new ArrivedP();
	SampleS Sample = new SampleS();

	// 构造器
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
	
	
	public void showInfo(){
		System.out.println("----------------模拟的信息-----------------");
		System.out.println("总预算为："+this.B);
		System.out.println("总时间为"+this.T);
		System.out.println("总人数为："+this.n);
		System.out.print("分成： "+this.l+" 个阶段，时间分别是： ");
		for(int i = 1; i <= l; i++)
			System.out.print(ST[i]+",");
		System.out.println();
		
		for(int i=l;i>=1;i--){
			System.out.print(ST[i]+",");
		}
		System.out.println("下面是用户的信息: (到达时间，离开时间，价值，花费)");
		for(Parti p:this.N.list){
			System.out.println("用户: "+p.id+" 的信息为"+"("+p.arrival_time+","+p.departure_time+","+p.value+","+p.cost+")");
		}
		System.out.println("----------------------------------------");
	}
	
	public static double disc(int t, int T){
		double x = Math.pow(Math.E, (double)(-t)/T);
		return x;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
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
			System.out.println("-----------------时刻"+t+"----------------------");
			System.out.println("这个阶段的预算为："+budget+","+"临界值为: "+pro.rho);
			long t1 = System.currentTimeMillis();
			System.out.println("t1 = "+t1);
			for (Parti p : pro.N.list) {
				if (p.arrival_time == t){
					p.show();
					pro.A.addP(p);
					System.out.println("在时刻"+t+"添加了一个用户"+p.id);
				}
			}
			Collections.sort(pro.A.list);
			for (int i =0;i<pro.A.list.size();i++) {
				Parti p = pro.A.list.get(i);
				System.out.println("*******对用户："+p.id+"进行判断********");
				p.show();
				System.out.println(t);
				System.out.println(p.value * disc(t,pro.T) / p.cost+"##"+pro.rho);
				System.out.println(p.value * disc(t,pro.T) / pro.rho
								+ pro.S.totalPay()+"##"+budget);
			
				if (p.value * disc(t,pro.T) / p.cost >= pro.rho
						&& p.value * disc(t,pro.T) / pro.rho
								+ pro.S.totalPay() <= budget) {
					System.out.println("又选择了一个！");
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
				System.out.println("&&&&&需要更新临界值了&&&&&");
				if(pro.Sample.list.isEmpty()==false){
					pro.rho = pro.Sample.getThreshold(budget, l, pro.ll, pro.U, pro.L);
				}
				pro.l--;
				budget = budget * 2;
			}
			long t2 = System.currentTimeMillis();
			System.out.println("t2 = "+t2);
			System.out.println("这个时段用时__"+(t2-t1));
			System.out.print("被选中的id为");
			pro.S.Pshow();
			System.out.println();
			System.out.print("Sample集为");
			pro.Sample.Pshow();
			System.out.println();
			System.out.print("候选集和为");
			pro.A.Pshow();
			System.out.println();
			
		}
		System.out.println(value);
	}

}
