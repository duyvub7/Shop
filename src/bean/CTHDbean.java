package bean;

public class CTHDbean {
	private String maCTHD;
	private String masach;
	private long soluong;
	private String maHD;
	
	public CTHDbean(String maCTHD, String masach, long soluong, String maHD) {
		super();
		this.maCTHD = maCTHD;
		this.masach = masach;
		this.soluong = soluong;
		this.maHD = maHD;
	}

	public String getMaCTHD() {
		return maCTHD;
	}

	public void setMaCTHD(String maCTHD) {
		this.maCTHD = maCTHD;
	}

	public String getMasach() {
		return masach;
	}

	public void setMasach(String masach) {
		this.masach = masach;
	}

	public long getSoluong() {
		return soluong;
	}

	public void setSoluong(long soluong) {
		this.soluong = soluong;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	
	
}
