package bo;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import bean.accountbean;
import dao.accountdao;
import dao.dungchung;

public class accountbo {
	accountdao adao = new accountdao();
	ArrayList<accountbean> ds = adao.getAcc();
	public ArrayList<accountbean> getAcc(){
		return ds;
	}
	public ArrayList<accountbean> getAcc_AD(){
		return adao.getAcc_AD();
	}
	public ArrayList<accountbean> getAcc_KH(){
		return adao.getAcc_KH();
	}
	public void UpdateKH(String un, String pass, String ten, String dc, String sdt, String email, String ma) {
		try {
			dungchung dcg = new dungchung();
			dcg.KetNoi();
			String sql = "update KhachHang\r\n" + 
					"  set hoten=?, diachi=?, sodt=?, email=?, tendn=?, pass=?\r\n" + 
					"  where makh=?";
			PreparedStatement stmt = dcg.cn.prepareStatement(sql);
			stmt.setString(1, ten);
			stmt.setString(2, dc);
			stmt.setString(3, sdt);
			stmt.setString(4, email);
			stmt.setString(5, un);
			stmt.setString(6, pass);
			stmt.setString(7, ma);
			stmt.executeUpdate();
			dcg.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
