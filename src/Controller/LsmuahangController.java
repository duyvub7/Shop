package Controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.hoadonbean;
import dao.dungchung;

/**
 * Servlet implementation class LsmuahangController
 */
@WebServlet("/LsmuahangController")
public class LsmuahangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LsmuahangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");   
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String tendn = (String)session.getAttribute("kh");
		ArrayList<hoadonbean> ds = new ArrayList<hoadonbean>();
		if(tendn!=null) {
			try {
				dungchung dc = new dungchung();
				dc.KetNoi();
				String sql ="select s.masach, s.tensach, s.tacgia, s.gia, ct.SoLuongMua\r\n" + 
						"from ChiTietHoaDon ct\r\n" + 
						"	inner join sach s on ct.MaSach = s.masach\r\n" + 
						"	inner join hoadon h on ct.MaHoaDon = h.MaHoaDon \r\n" + 
						"	inner join KhachHang k on h.makh = k.makh\r\n" + 
						"where h.damua = '1' and k.tendn = ?\r\n";
				PreparedStatement stmt = dc.cn.prepareStatement(sql);
				stmt.setString(1, tendn);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					ds.add(new hoadonbean(rs.getString("masach"), rs.getString("tensach"), rs.getString("tacgia"), 
							Integer.parseInt(rs.getString("gia")), Integer.parseInt(rs.getString("SoLuongMua"))));
				}
				rs.close();
				dc.cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("lsmh", ds);
		
		RequestDispatcher rd = request.getRequestDispatcher("lsmuahang.jsp");
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
