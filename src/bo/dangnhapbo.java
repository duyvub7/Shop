package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.dungchung;

public class dangnhapbo {
	public boolean KtraDN_AD(String un, String pass) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from DangNhap where TenDangNhap like ? and MatKhau like ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, un);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			String kt = null;
			while(rs.next()) {
				kt = rs.getString("TenDangNhap");
			}
			rs.close();
			dc.cn.close();
			if(kt!=null) {
				return true;
			}else return false;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean KtraDN_KH(String un, String pass) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from KhachHang where tendn like ? and pass like ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, un);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			String kt = null;
			while(rs.next()) {
				kt = rs.getString("tendn");
			}
			rs.close();
			dc.cn.close();
			if(kt!=null) {
				return true;
			}else
				return false;
			
		} catch (Exception e) {
			return false;
		}	
	}
	public String GetMaKH(String un) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from KhachHang where tendn like ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, un);
			ResultSet rs = stmt.executeQuery();
			String id = null;
			while(rs.next()) {
				id = rs.getString("makh");
			}
			rs.close();
			dc.cn.close();
			return id;
		} catch (Exception e) {
			return null;
		}	
	}
	public boolean KtraDK(String un) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String kt = null;
			String sql ="select * from KhachHang where tendn like ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, un);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				kt = rs.getString("tendn");
			}
			if(kt==null) {
				sql ="select * from DangNhap where TenDangNhap like ?";
				stmt = dc.cn.prepareStatement(sql);
				stmt.setString(1, un);
				rs = stmt.executeQuery();
				while(rs.next()) {
					kt = rs.getString("TenDangNhap");
				}
			}
			rs.close();
			dc.cn.close();
			if(kt==null) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}	
	}
	public void AddKH(String name, String dc, String sdt, String mail, String un, String pass) {
		try {
			dungchung dcg = new dungchung();
			dcg.KetNoi();
			String sql = "insert into KhachHang values(?,?,?,?,?,?)";
			PreparedStatement stmt = dcg.cn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, dc);
			stmt.setString(3, sdt);
			stmt.setString(4, mail);
			stmt.setString(5, un);
			stmt.setString(6, pass);
			stmt.executeUpdate();
			dcg.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
