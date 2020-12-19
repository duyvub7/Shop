package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import bean.hoadonbean;

import java.sql.Date;

import dao.dungchung;

public class hoadonbo {
	ArrayList<hoadonbean> ds = new ArrayList<hoadonbean>();
	public ArrayList<hoadonbean> getds(String tendn){
		ArrayList<hoadonbean> ds = new ArrayList<hoadonbean>();
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select s.masach, s.tensach, s.tacgia, s.gia, SUM(ct.SoLuongMua) as soluong\r\n" + 
					"from ChiTietHoaDon ct\r\n" + 
					"	inner join sach s on ct.MaSach = s.masach\r\n" + 
					"	inner join hoadon h on ct.MaHoaDon = h.MaHoaDon\r\n" + 
					"	inner join KhachHang k on h.makh = k.makh \r\n" + 
					"where h.damua = '0' and k.tendn = ?\r\n" + 
					"group by s.masach, s.tensach, s.tacgia, s.gia";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, tendn);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new hoadonbean(rs.getString("masach"), rs.getString("tensach"), rs.getString("tacgia"), 
						Integer.parseInt(rs.getString("gia")), Integer.parseInt(rs.getString("soluong"))));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	public void Add ( hoadonbean hb) {
		boolean kt = false;
		for(hoadonbean g : ds)
			if(g.getMasach().equals(hb.getMasach())) {
				g.setSoluong(1);
				kt = true;
				break;
			}
		if(kt==false) {
				ds.add(hb);
			}
	}
	public void UpdateSL_CTHD ( String ma, int sl) {					//updateSL trong CTHD
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "update ChiTietHoaDon set SoLuongMua = '1'\r\n" + 
					"where MaSach = 'b17'\r\n" + 
					"  and MaHoaDon in (select ct.MaHoaDon\r\n" + 
					"					from hoadon h\r\n" + 
					"						inner join ChiTietHoaDon ct on ct.MaHoaDon = h.MaHoaDon\r\n" + 
					"						inner join KhachHang k on h.makh = k.makh \r\n" + 
					"					where h.damua = '0' and k.tendn = 'hh')";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, GetMaKH(ma));
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void Delete ( String ma) {
		for(hoadonbean g : ds)
			if(g.getMasach().equalsIgnoreCase(ma)) {
				ds.remove(g);
				break;
			}
	}
	public void DeleteSach_CTHD ( String ma, String tendn) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "delete from ChiTietHoaDon\r\n" + 
					"where MaSach = ?\r\n" + 
					"  and MaHoaDon in (select ct.MaHoaDon\r\n" + 
					"					from hoadon h\r\n" + 
					"						inner join ChiTietHoaDon ct on ct.MaHoaDon = h.MaHoaDon\r\n" + 
					"						inner join KhachHang k on h.makh = k.makh \r\n" + 
					"					where h.damua = '0' and k.tendn = ?)";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, ma);
			stmt.setString(2, tendn);
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public long Tong(String tendn) {
		long tong = 0;
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select s.masach, s.tensach, s.tacgia, s.gia, SUM(ct.SoLuongMua) as soluong\r\n" + 
					"from ChiTietHoaDon ct\r\n" + 
					"	inner join sach s on ct.MaSach = s.masach\r\n" + 
					"	inner join hoadon h on ct.MaHoaDon = h.MaHoaDon\r\n" + 
					"	inner join KhachHang k on h.makh = k.makh \r\n" + 
					"where h.damua = '0' and k.tendn = ?\r\n" + 
					"group by s.masach, s.tensach, s.tacgia, s.gia";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, tendn);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tong += Long.parseLong(rs.getString("gia"))*Long.parseLong(rs.getString("soluong"));
			}
			rs.close();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tong;
	}
	public String gettendn(String tendn) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from KhachHang where tendn like ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, tendn);
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
	public String GetMaKH(String tendn) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from KhachHang where tendn like ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, tendn);
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
	public void AddHoaDon(String tendn, int tt) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "insert into hoadon values(?,?,?)";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(GetMaKH(tendn)));
			LocalDate ld = LocalDate.now();
			Date d = Date.valueOf(ld);
			stmt.setDate(2, d);
			stmt.setInt(3, tt);
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public String GetMaHD() {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql ="select * from hoadon";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String id = null;
			while(rs.next()) {
				id = rs.getString("MaHoaDon");
			}
			rs.close();
			dc.cn.close();
			return id;
		} catch (Exception e) {
			return null;
		}	
	}
	public void AddCTHoaDon(String mas, long sl) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "insert into ChiTietHoaDon values(?,?,?)";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, mas);
			stmt.setInt(2, (int)sl);
			stmt.setInt(3, Integer.parseInt(GetMaHD()));
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void UpdateHoaDon(String tendn) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "update hoadon set damua = '1'\r\n" + 
					"where damua = '0'\r\n" + 
					"	and makh in (select KhachHang.makh\r\n" + 
					"			   from hoadon inner join KhachHang on hoadon.makh = KhachHang.makh\r\n" + 
					"			   where KhachHang.makh = ?)";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, GetMaKH(tendn));
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
