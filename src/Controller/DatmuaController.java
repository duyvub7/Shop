package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.giohangbean;
import bo.giohangbo;

/**
 * Servlet implementation class DatmuaController
 */
@WebServlet("/DatmuaController")
public class DatmuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatmuaController() {
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
			String ma = request.getParameter("ms");
			String ten = request.getParameter("ts");
			String tg = request.getParameter("tg");
			String anh = request.getParameter("anh");
			String gia = request.getParameter("gia");
			HttpSession session = request.getSession();
			giohangbo gh;
			if(session.getAttribute("gh")==null) {
				gh = new giohangbo();
				session.setAttribute("gh", gh);
			}else {
				gh = (giohangbo)session.getAttribute("gh");
			}
			if(ma!=null) {
				gh.Add(new giohangbean(ma, ten, tg, anh, Long.parseLong(gia), 1));
			}
				String ac = request.getParameter("action");
				if(ac!=null){
					gh=(giohangbo)session.getAttribute("gh");
					String ma2 = request.getParameter("ma");
					String sltam = request.getParameter("txtsl");
					if(ac.equals("update")){
						if(ma2!=null){
							if(sltam!=null){
								long sl = Long.parseLong(sltam);
								if(sl>0){
									gh.Update(ma2, sl);
								}
								else{
									gh.Delete(ma2);
								}
							}
						}
					}
					if(ac.equals("delete")){
						gh.Delete(ma2);
					}
				}
				if(gh!=null) {
					int n = gh.getds().size();
					session.setAttribute("slgh", n);
					session.setAttribute("gh", gh);
				}else { session.setAttribute("slgh", null);}
				
			RequestDispatcher rd = request.getRequestDispatcher("giohang.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
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
