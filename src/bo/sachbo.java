package bo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import bean.sachbean;
import dao.dungchung;
import dao.sachdao;

public class sachbo {
	sachdao sdao = new sachdao();
	ArrayList<sachbean> ds = sdao.getsach();
	public ArrayList<sachbean> getsach(){
		return ds;
	}
	public String LoaiBoDau(String s) {
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("").replace("đ", "d");
	}
	public ArrayList<sachbean> TimTheoMa(String ma){
		ArrayList<sachbean> tam = new ArrayList<sachbean>();
		for(sachbean s: ds)
			if(s.getMaloai().toLowerCase().contains(ma.toLowerCase()))
				tam.add(s);
		return tam;
	}
	public ArrayList<sachbean> TimTheoTen(String ten){
		ArrayList<sachbean> tam = new ArrayList<sachbean>();
		for(sachbean s: ds)
			if(LoaiBoDau( s.getTensach().toLowerCase() ).contains( LoaiBoDau(ten.toLowerCase()) ))
				tam.add(s);
		return tam;
	}
	public ArrayList<sachbean> TimTheoTacgia(String ten){
		ArrayList<sachbean> tam = new ArrayList<sachbean>();
		for(sachbean s: ds)
			if(LoaiBoDau( s.getTacgia().toLowerCase() ).contains( LoaiBoDau(ten.toLowerCase()) ))
				tam.add(s);
		return tam;
	}
	public ArrayList<sachbean> TimTheoTen_Tacgia(String ten){
		ArrayList<sachbean> tam = new ArrayList<sachbean>();
		for(sachbean s: ds)
			if(LoaiBoDau( s.getTensach().toLowerCase()).contains(LoaiBoDau(ten.toLowerCase())) || LoaiBoDau(s.getTacgia().toLowerCase()).contains(LoaiBoDau(ten.toLowerCase())) )
				tam.add(s);
		return tam;
	}
	public ArrayList<sachbean> SXTheoTen(ArrayList<sachbean> ds){
		sachbean tam;
		for(int i=0;i<ds.size()-1;i++)
			for(int j=i+1;j<ds.size();j++) {
				if(ds.get(i).getTensach().compareToIgnoreCase(ds.get(j).getTensach())>0) {
					tam = ds.get(i);
					ds.set(i, ds.get(j));
					ds.set(j, tam);
				}
			}
		return ds;
	}
	public ArrayList<sachbean> SXTheoGia(ArrayList<sachbean> ds){
		sachbean tam;
		for(int i=0;i<ds.size()-1;i++)
			for(int j=i+1;j<ds.size();j++) {
				if(ds.get(i).getGia()>ds.get(j).getGia()) {
					tam = ds.get(i);
					ds.set(i, ds.get(j));
					ds.set(j, tam);
				}
			}
		return ds;
	}
	public void AddSach(String ma, String ten, String sl, String gia, String maloai, String sotap, String anh, String tacgia) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "insert into sach values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, ma);
			stmt.setString(2, ten);
			stmt.setLong(3, Long.parseLong(sl));
			stmt.setLong(4, Long.parseLong(gia));
			stmt.setString(5, maloai);
			stmt.setString(6, sotap);	
			stmt.setString(7, anh);
			LocalDate ld = LocalDate.now();
			Date d = Date.valueOf(ld);
			stmt.setDate(8, d);
			stmt.setString(9, tacgia);
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void UpdateSach(String ma, String ten, String anh, String tacgia, String sl, String gia, String maloai, String sotap, String ngaynhap) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "update sach set tensach=?, soluong=?, gia=?, maloai=?, \r\n" + 
					   "				  sotap=?, anh=?, NgayNhap = ?, tacgia = ?\r\n" + 
					   "  where masach = ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, ten);
			stmt.setLong(2, Long.parseLong(sl));;
			stmt.setLong(3, Long.parseLong(gia));
			stmt.setString(4, maloai);
			stmt.setLong(5, Long.parseLong(sotap));
			stmt.setString(6, anh);
			stmt.setDate(7, Date.valueOf(ngaynhap));
			stmt.setString(8, tacgia);
			stmt.setString(9, ma);
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void UpdateSach(String ma, String ten, String tacgia, String sl, String gia, String maloai, String sotap, String ngaynhap) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "update sach set tensach=?, soluong=?, gia=?, maloai=?, \r\n" + 
					   "				  sotap=?, NgayNhap = ?, tacgia = ?\r\n" + 
					   "  where masach = ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, ten);
			stmt.setLong(2, Long.parseLong(sl));;
			stmt.setLong(3, Long.parseLong(gia));
			stmt.setString(4, maloai);
			stmt.setLong(5, Long.parseLong(sotap));
			stmt.setDate(6, Date.valueOf(ngaynhap));
			stmt.setString(7, tacgia);
			stmt.setString(8, ma);
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void DeleteSach(String ma) {
		try {
			dungchung dc = new dungchung();
			dc.KetNoi();
			String sql = "delete from sach where masach = ?";
			PreparedStatement stmt = dc.cn.prepareStatement(sql);
			stmt.setString(1, ma);
			stmt.executeUpdate();
			dc.cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
