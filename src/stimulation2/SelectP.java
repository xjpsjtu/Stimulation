package stimulation2;

import java.util.*;

//��ѡ�е��û�����
public class SelectP extends Pset {

	// ������SelectP���û����ܻ���
	public double totalPay() {
		double total = 0;
		for (Parti p : list) {
			total += p.pay;
		}
		return total;
	}
}
