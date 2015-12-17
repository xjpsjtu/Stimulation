package stimulation2;

import java.util.*;

//被选中的用户集合
public class SelectP extends Pset {

	// 返回在SelectP内用户的总花费
	public double totalPay() {
		double total = 0;
		for (Parti p : list) {
			total += p.pay;
		}
		return total;
	}
}
