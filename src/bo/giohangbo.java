package bo;

import java.util.ArrayList;

import bean.giohangbean;

public class giohangbo {
	ArrayList<giohangbean> ds = new ArrayList<giohangbean>();
	public ArrayList<giohangbean> getds(){
		return ds;
	}
	public void Add ( giohangbean hb) {
		boolean kt = false;
		for(giohangbean g : ds)
			if(g.getMasach().equals(hb.getMasach())) {
				g.setSoluong(1);
				kt = true;
				break;
			}
		if(kt==false) {
				ds.add(hb);
			}
	}
	public void Update ( String ma, long sl) {
		for(giohangbean g : ds) {
			if(g.getMasach().equals(ma)) {
				g.setSoluong(sl);
				break;
			}
		}
	}
	public void Delete ( String ma) {
		for(giohangbean g : ds)
			if(g.getMasach().equalsIgnoreCase(ma)) {
				ds.remove(g);
				break;
			}
	}
	public long Tong() {
		long tong = 0;
		for(giohangbean g: ds)
			tong +=g.getThanhTien();
		return tong;
	}
}
