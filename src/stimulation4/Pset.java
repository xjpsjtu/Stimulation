package stimulation4;

import java.util.*;

public class Pset {
	// Pset定义了用户集合，维护一个用户数组
	public ArrayList<Parti> list =new ArrayList<Parti>();

	// 添加用户
	public void addP(Parti p) {
		this.list.add(p);
	}

	// Pset内用户的总价值
	public double total_vlaue() {
		double v = 0;
		for (Parti p : list) {
			v += p.value;
		}
		return v;
	}

	// Pset内用户的总花费
	public double total_cost() {
		double c = 0;
		for (Parti p : list) {
			c += p.cost;
		}
		return c;
	}
	public void Pshow(){
		for(Parti p:list){
			System.out.print(p.id+",");
		}
	}
	
}
