package stimulation;

import java.util.*;

//�����Ѿ�������û����ϣ�δ��ѡ��ģ�
public class ArrivedP extends Pset {

	// �Ƴ����б�Sѡ����û�
	public void removeS() {
		for (int i=0;i<this.list.size();i++) {
			Parti p = this.list.get(i);
			if(p.select == true){
				this.list.remove(i);
			}
		}
	}

	// ��ʱ��t�Ƴ������뿪���û�
	public void rmoveD(int t) {
		for (int i=0;i<this.list.size();i++) {
			Parti p = this.list.get(i);
			if(p.departure_time == t){
				this.list.remove(i);
			}
		}
	}
}
