package stimulation;

import java.util.*;

//样品集合
public class SampleS extends Pset {

	// 返回sample集合中学习到的临界值
	public double getThreshold(double budget, int k, int l, int U, int L) {
		Pset tmp = new Pset();
		double totalV = 0;
		Collections.sort(list);
		for (Parti p : list) {
			totalV += p.value;
			if (p.cost <= 2 * p.value *budget/ totalV) {
				tmp.addP(p);
			} else
				break;
		}
		return (tmp.total_vlaue() / tmp.total_cost()) * Math.pow(U / L, -k/l);
	}
}
