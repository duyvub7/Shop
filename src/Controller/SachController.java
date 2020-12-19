package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.sachbean;
import bo.dangnhapbo;
import bo.loaibo;
import bo.sachbo;

/**
 * Servlet implementation class SachController
 */
@WebServlet("/SachController")
public class SachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loaibo lbo = new loaibo();
	    sachbo sbo = new sachbo();
		try {
			request.setCharacterEncoding("utf-8");   //lấy dữ liệu lên bằng unicode
			response.setCharacterEncoding("utf-8");		//truyền dữ liệu đi bằng unicode
			HttpSession session = request.getSession();
			request.setAttribute("dsloai", lbo.getloai());
			String type = request.getParameter("type");
			String ml = request.getParameter("txtmaloai");
			String timkiem = request.getParameter("txtsearch"); 
			ArrayList<sachbean> dssach;
			if(type!=null)
				dssach = sbo.TimTheoMa(type);     
			else if(ml!=null && ml!="") 				
				dssach = sbo.TimTheoMa(ml);
			else if(timkiem!=null)
				dssach = sbo.TimTheoTen_Tacgia(timkiem);
			else 
				dssach = sbo.getsach();

			session.setAttribute("sort", "");

			if(request.getParameter("sort")!=null) {
				if(request.getParameter("sort").equals("default")) {
					dssach = sbo.getsach();
					session.setAttribute("sort", "default");
				}
				if(request.getParameter("sort").equals("ten")) {
					dssach = sbo.SXTheoTen(dssach);
					session.setAttribute("sort", "ten");
				}
				if(request.getParameter("sort").equals("gia")) {
					dssach = sbo.SXTheoGia(dssach);
					session.setAttribute("sort", "gia");
				}
			}
			
			request.setAttribute("dssach", dssach);
			
			if(ml==null)
				request.setAttribute("maloai", "");
			else
				request.setAttribute("maloai", ml);
			if(timkiem==null)
				session.setAttribute("timkiem", "");
			else
				session.setAttribute("timkiem", timkiem);
			
			if(session.getAttribute("acc")==null) {
				session.setAttribute("acc", "");
			}
			session.setAttribute("loi", "");
			
			
			
			dangnhapbo dnbo = new dangnhapbo();
			
			String undn = request.getParameter("unDN");
			String passdn= request.getParameter("passDN");
			String undk = request.getParameter("unDK");
			String passdk= request.getParameter("passDK");
			String namedk = request.getParameter("nameDK");
			String dcdk = request.getParameter("dcDK");
			String sdtdk = request.getParameter("sdtDK");
			String maildk = request.getParameter("mailDK");
			if(undn!=null) {					//Đăng nhập
				if(dnbo.KtraDN_AD(undn, passdn)) {	//Tồn tại trong admin
					session.setAttribute("ad", undn);
					session.setAttribute("acc", "ad");
				}else {
					if(dnbo.KtraDN_KH(undn, passdn)) {	//Tồn tại trong khách hàng
						session.setAttribute("kh", undn);
						session.setAttribute("acc", "kh");
					}else {								//Không tồn tại
						session.setAttribute("loi", "dn");
					}
				}
			}else if(namedk!=null) {						//name!=null=>dang ky
				if(dnbo.KtraDK(undk)) {				//tên đăng nhập chưa có trong khách hàng
					dnbo.AddKH(namedk, dcdk, sdtdk, maildk, undk, passdk);
					session.setAttribute("kh", undk);
					session.setAttribute("acc", "kh");
				}else {								//tên đăng nhập đã tồn tại
					session.setAttribute("loi", "dk");
				}
			}
			
			if(request.getParameter("action")!=null && request.getParameter("action").equals("dx")) {
				session.setAttribute("acc", "");
				session.setAttribute("ad", "");
				session.setAttribute("kh", "");
			}
			if(session.getAttribute("acc").equals("ad")) {
				RequestDispatcher rd = request.getRequestDispatcher("QLSachController");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("trangchu.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
