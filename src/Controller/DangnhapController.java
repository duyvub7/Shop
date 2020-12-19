package Controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dungchung;

/**
 * Servlet implementation class DangnhapController
 */
@WebServlet("/DangnhapController")
public class DangnhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangnhapController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				request.setCharacterEncoding("utf-8");   
				response.setCharacterEncoding("utf-8");
				HttpSession session = request.getSession();
				
				String un = request.getParameter("unameDN");
				String pass= request.getParameter("passDN");
				String name = request.getParameter("nameDK");
				String mail = request.getParameter("mailDK");
				if(session.getAttribute("acc")==null) {
					session.setAttribute("acc", "not");
				}
				if(un!=null) {
					dungchung dc = new dungchung();
					dc.KetNoi();
					if(name==null) {	//dang nhap
						String sql ="select * from DangNhap where TenDangNhap like ? and MatKhau like ?";
						PreparedStatement stmt = dc.cn.prepareStatement(sql);
						stmt.setString(1, un);
						stmt.setString(2, pass);
						ResultSet rs = stmt.executeQuery();
						String ad = null;
						while(rs.next()) {
							ad = rs.getString("TenDangNhap");
						}
						if(ad!=null) {
							request.setAttribute("ad", ad);
							session.setAttribute("acc", "ad");
						}
						if(ad==null) {
							sql ="select * from KhachHang where tendn like ? and pass like ?";
							stmt = dc.cn.prepareStatement(sql);
							stmt.setString(1, un);
							stmt.setString(2, pass);
							rs = stmt.executeQuery();
							String kh = null;
							while(rs.next()) {
								kh = rs.getString("tendn");
							}
							if(kh!=null) {
								request.setAttribute("kh", kh);
								session.setAttribute("acc", "kh");
							}
						}
						rs.close();
					}else {				//name!=null=>dang ky
						un = request.getParameter("unameDK");
						pass = request.getParameter("passDK");
						String sql ="select * from KhachHang where tendn like ? and pass like ?";
						PreparedStatement stmt = dc.cn.prepareStatement(sql);
						stmt.setString(1, un);
						stmt.setString(2, pass);
						ResultSet rs = stmt.executeQuery();
						String kh = null;
						while(rs.next()) {
							kh = rs.getString("tendn");
						}
						//insert
						sql = "insert into KhachHang values(?,?,?,?,?,?,?)";
						stmt = dc.cn.prepareStatement(sql);
						stmt.setString(6, un);
						stmt.setString(7, pass);
						stmt.executeUpdate();
						if(kh!=null) {
							request.setAttribute("kh", kh);
							session.setAttribute("acc", "kh");
						}
						rs.close();
					}
					dc.cn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("trangchu.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
