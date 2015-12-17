package stimulation1;

import java.util.*;

public class Parti implements Comparable<Parti> {
	int id; // 用户的ID
	int arrival_time, departure_time; // 到达和离开时间
	double cost, value, pay; // 花费，价值和收益
	boolean select; // 标志是否被选中

	// 初始化一个用户，包括到达和离开时间，花费，价值和收益，默认没有被选中
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

	// 返回用户的效率
	public double efficiency() {
		return value / cost;
	}

	public int compareTo(Parti p) {
		// TODO 自动生成的方法存根
		if (this.efficiency() < p.efficiency()) {
			return -1;
		}
		if (this.efficiency() > p.efficiency()) {
			return 1;
		}
		return 0;
	}
	
	public void show(){
		System.out.println("用户: "+this.id+" 的信息为"+"("+this.arrival_time+","+this.departure_time+","+this.value+","+this.cost+")");
	}
}
