package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.sachbean;

public class sachdao {
	public ArrayList<sachbean> getsach() {
		ArrayList<sachbean> ds = new ArrayList<sachbean>();
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from sach";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new sachbean(rs.getString("masach"), rs.getString("tensach"), rs.getString("tacgia"), rs.getLong("sotap"),rs.getLong("soluong"), 
						rs.getLong("gia"), rs.getDate("NgayNhap"), rs.getString("anh"), rs.getString("maloai")));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}
