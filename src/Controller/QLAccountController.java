package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.accountbean;
import bo.accountbo;

/**
 * Servlet implementation class QLAccountController
 */
@WebServlet("/QLAccountController")
public class QLAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			accountbo abo = new accountbo();
			request.setCharacterEncoding("utf-8"); 
			response.setCharacterEncoding("utf-8");	
			//HttpSession session = request.getSession();
			ArrayList<accountbean> dsacc;
			String typeacc = request.getParameter("type");
			if(typeacc!=null && typeacc.equals("ad")) {
					dsacc = abo.getAcc_AD();
					request.setAttribute("type", "ad");
			}else {
				dsacc = abo.getAcc_KH();
				request.setAttribute("type", "kh");
			}
			//dsacc = abo.getAcc();
			request.setAttribute("dsacc", dsacc);
			
			if(request.getParameter("action")!=null && request.getParameter("action").equals("edit")) {
				String un = request.getParameter("un");
				String pass = request.getParameter("pass");
				String ten = request.getParameter("ten");
				String dc = request.getParameter("dc");
				String sdt = request.getParameter("sdt");
				String email = request.getParameter("email");
				String ma = request.getParameter("ma");
				abo.UpdateKH(un, pass, ten, dc, sdt, email, ma);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("QL_Account.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
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
