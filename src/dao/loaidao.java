package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.loaibean;

public class loaidao {
	public ArrayList<loaibean> getloai() {
		ArrayList<loaibean> ds = new ArrayList<loaibean>();
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from loai";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new loaibean(rs.getString("maloai"), rs.getString("tenloai")));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}
