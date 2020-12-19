package bean;

import java.sql.Date;

public class sachbean {
	private String masach;
	private String tensach;
	private String tacgia;
	private long sotap;
	private long soluong;
	private long gia;
	private Date ngaynhap;
	private String anh;
	private String maloai;
	
	public sachbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public sachbean(String masach, String tensach, String tacgia, long soluong, long gia, String anh, String maloai) {
		super();
		this.masach = masach;
		this.tensach = tensach;
		this.tacgia = tacgia;
		this.soluong = soluong;
		this.gia = gia;
		this.anh = anh;
		this.maloai = maloai;
	}
	public sachbean(String masach, String tensach, String tacgia, long sotap, long soluong, long gia, Date ngaynhap, String anh, String maloai) {
		super();
		this.masach = masach;
		this.tensach = tensach;
		this.tacgia = tacgia;
		this.sotap = sotap;
		this.soluong = soluong;
		this.gia = gia;
		this.ngaynhap = ngaynhap;
		this.anh = anh;
		this.maloai = maloai;
	}
	public String getMasach() {
		return masach;
	}
	public void setMasach(String masach) {
		this.masach = masach;
	}
	public String getTensach() {
		return tensach;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	public String getTacgia() {
		return tacgia;
	}
	public void setTacgia(String tacgia) {
		this.tacgia = tacgia;
	}
	public long getSoluong() {
		return soluong;
	}
	public void setSoluong(long soluong) {
		this.soluong = soluong;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public long getSotap() {
		return sotap;
	}
	public void setSotap(long sotap) {
		this.sotap = sotap;
	}
	public Date getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	
}
