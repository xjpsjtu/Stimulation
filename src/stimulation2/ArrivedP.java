package stimulation2;

import java.util.*;

//�����Ѿ�������û����ϣ�δ��ѡ��ģ�
public class ArrivedP extends Pset {

	// �Ƴ����б�Sѡ����û�
	public ArrivedP removeS() {
		ArrivedP A = new ArrivedP();
		for (int i=0;i<this.list.size();i++) {
			if(this.list.get(i).select == false){
				A.list.add(this.list.get(i));
			}
		}
		return A;
	}

	// ��ʱ��t�Ƴ������뿪���û�
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
