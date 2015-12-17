package stimulation;

import java.util.*;

//所有已经到达的用户集合（未被选择的）
public class ArrivedP extends Pset {

	// 移除所有被S选择的用户
	public void removeS() {
		for (int i=0;i<this.list.size();i++) {
			Parti p = this.list.get(i);
			if(p.select == true){
				this.list.remove(i);
			}
		}
	}

	// 在时刻t移除所有离开的用户
	public void rmoveD(int t) {
		for (int i=0;i<this.list.size();i++) {
			Parti p = this.list.get(i);
			if(p.departure_time == t){
				this.list.remove(i);
			}
		}
	}
}
