package stimulation3;

import java.util.*;

//��Ʒ����
public class SampleS extends Pset {

	// ����sample������ѧϰ�����ٽ�ֵ
	public double getThreshold(double budget, int k, int l, int U, int L, double lam) {
		Pset tmp = new Pset();
		double totalV = 0;
		Collections.sort(list);
		for (Parti p : list) {
			totalV += p.value;
			if (p.cost <= 2*U/L * p.value *budget/ totalV) {
				tmp.addP(p);
			} else
				break;
		}
		return (tmp.total_vlaue() / tmp.total_cost()) * Math.pow(U / L, l+1-k)/lam;
	}
}
