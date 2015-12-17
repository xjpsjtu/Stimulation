package stimulation2;

import java.util.*;

//所有已经到达的用户集合（未被选择的）
public class ArrivedP extends Pset {

	// 移除所有被S选择的用户
	public ArrivedP removeS() {
		ArrivedP A = new ArrivedP();
		for (int i=0;i<this.list.size();i++) {
			if(this.list.get(i).select == false){
				A.list.add(this.list.get(i));
			}
		}
		return A;
	}

	// 在时刻t移除所有离开的用户
	public ArrivedP removeD(int t) {
		ArrivedP A = new ArrivedP();
		for (int i=0;i<this.list.size();i++) {
			if(this.list.get(i).departure_time != t){
				A.list.add(this.list.get(i));
			}
		}
		return A;
	}
}
