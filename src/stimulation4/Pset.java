package stimulation4;

import java.util.*;

public class Pset {
	// Pset�������û����ϣ�ά��һ���û�����
	public ArrayList<Parti> list =new ArrayList<Parti>();

	// ����û�
	public void addP(Parti p) {
		this.list.add(p);
	}

	// Pset���û����ܼ�ֵ
	public double total_vlaue() {
		double v = 0;
		for (Parti p : list) {
			v += p.value;
		}
		return v;
	}

	// Pset���û����ܻ���
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
