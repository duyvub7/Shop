package bean;

public class accountbean {
	private String Ten;
	private String Diachi;
	private String Sdt;
	private String email;
	private String Tendn;
	private String Pass;
	private String Quyen;
	private String makh;
	
	public accountbean() {
		super();
	}	
	public accountbean(String tendn, String pass, String quyen) {
		super();
		Tendn = tendn;
		Pass = pass;
		Quyen = quyen;
	}
	public accountbean(String ten, String diachi, String sdt, String email, String tendn, String pass, String makh) {
		super();
		Ten = ten;
		Diachi = diachi;
		Sdt = sdt;
		this.email = email;
		Tendn = tendn;
		Pass = pass;
		this.makh = makh;
	}

	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public String getSdt() {
		return Sdt;
	}
	public void setSdt(String sdt) {
		Sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTendn() {
		return Tendn;
	}
	public void setTendn(String tendn) {
		Tendn = tendn;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getQuyen() {
		return Quyen;
	}
	public void setQuyen(String quyen) {
		Quyen = quyen;
	}
	public String getMakh() {
		return makh;
	}
	public void setMakh(String makh) {
		this.makh = makh;
	}
	
	
}
