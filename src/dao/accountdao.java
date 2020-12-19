package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.accountbean;

public class accountdao {
	public ArrayList<accountbean> getAcc(){
		ArrayList<accountbean> ds = new ArrayList<accountbean>();
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from DangNhap";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new accountbean(rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getString("Quyen")));
			}
			sql ="select * from KhachHang";
			stmt = dc.cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new accountbean(rs.getString("hoten"), rs.getString("diachi"), rs.getString("sodt"), rs.getString("email"), rs.getString("tendn"), rs.getString("pass"), rs.getString("makh")));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<accountbean> getAcc_AD(){
		ArrayList<accountbean> ds = new ArrayList<accountbean>();
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from DangNhap";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new accountbean(rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getString("Quyen")));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<accountbean> getAcc_KH(){
		ArrayList<accountbean> ds = new ArrayList<accountbean>();
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from KhachHang";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new accountbean(rs.getString("hoten"), rs.getString("diachi"), rs.getString("sodt"), rs.getString("email"), rs.getString("tendn"), rs.getString("pass"), rs.getString("makh")));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}
