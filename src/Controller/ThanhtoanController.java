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

import bean.giohangbean;
import bean.hoadonbean;
import bo.giohangbo;
import bo.hoadonbo;

/**
 * Servlet implementation class ThanhtoanController
 */
@WebServlet("/ThanhtoanController")
public class ThanhtoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThanhtoanController() {
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
		hoadonbo hdbo = new hoadonbo();
		String tendn = (String)session.getAttribute("kh");
		giohangbo gh = (giohangbo)session.getAttribute("gh");
		ArrayList<hoadonbean> ds = hdbo.getds(tendn);
		
		String ma = request.getParameter("ma");
		
		if(request.getParameter("action")!=null) {
			if(request.getParameter("action").equals("addHD")) {
				if(session.getAttribute("kh")!=null && session.getAttribute("kh")!="" && gh!=null) {
					String un = (String)session.getAttribute("kh");
					hdbo.AddHoaDon(un, 0);
					if(ds!=null) {
						for(giohangbean g: gh.getds()){
							hdbo.AddCTHoaDon(g.getMasach(), g.getSoluong());
						}
					}
				}
			}
			if(request.getParameter("action").equals("ttHD")) {
				hdbo.UpdateHoaDon(tendn);
			}
			if(request.getParameter("action").equals("update")) {
				if(ma!=null){
					String sltam = request.getParameter("txtsl");
					if(sltam!=null){
						int sl = Integer.parseInt(sltam);
						if(sl>0){
							hdbo.UpdateSL_CTHD(ma, sl);
						}
						else{
							hdbo.DeleteSach_CTHD(ma, tendn);
						}
					}
				}
			}
			if(request.getParameter("action").equals("delete")) {
				hdbo.DeleteSach_CTHD(ma, tendn);
			}
		}
		
		session.setAttribute("hd", hdbo);
		
		RequestDispatcher rd = request.getRequestDispatcher("thanhtoan.jsp");
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
