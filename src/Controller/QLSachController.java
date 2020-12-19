package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.sachbean;
import bo.loaibo;
import bo.sachbo;

/**
 * Servlet implementation class QLSachController
 */
@WebServlet("/QLSachController")
public class QLSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLSachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			loaibo lbo = new loaibo();
			sachbo sbo = new sachbo();
			request.setCharacterEncoding("utf-8"); 
			response.setCharacterEncoding("utf-8");	
			HttpSession session = request.getSession();
			request.setAttribute("dsloai", lbo.getloai());
			
			ArrayList<sachbean> dssach;
			
			String type = request.getParameter("type");
			String timkiem = request.getParameter("txtsearch");
			request.setAttribute("timkiem", "");
			if(type!=null) {
				dssach = sbo.TimTheoMa(type);     
			}else if(timkiem!=null && timkiem!="") {
				dssach = sbo.TimTheoTen_Tacgia(timkiem);
				request.setAttribute("timkiem", timkiem);
			}else dssach = sbo.getsach();
			
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
			
			String action = request.getParameter("action");
			if(action!=null) {
				String mas = null;
				String tens = null;
				String sls = null;
				String tacgias = null;
				String sotaps = null;
				String gias = null;
				String maloais = null;
				String ngaynhaps = null;
				String tenanhs = null;
				if(action.equals("add") || action.equals("edit")) {
					DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
					String dirUrl1 = request.getServletContext().getRealPath("") + File.separator + "files";
					response.getWriter().println(dirUrl1);
					try {
						List<FileItem> fileItems = upload.parseRequest(request);//Lấy về các đối tượng gửi lên
						//duyệt qua các đối tượng gửi lên từ client gồm file và các control
						for (FileItem fileItem : fileItems) {
							if (!fileItem.isFormField()) {//Nếu ko phải các control=>upfile lên
								// xử lý file
								tenanhs = fileItem.getName();
								if (!tenanhs.equals("")) {
									 //Lấy đường dẫn hiện tại, chủ ý xử lý trên dirUrl để có đường dẫn đúng
									String dirUrl = request.getServletContext().getRealPath("") + File.separator + "files";
									
									int vt1 = dirUrl.indexOf(".metadata");
									dirUrl = dirUrl.substring(0, vt1-1) + "\\Shop\\WebContent\\image_sach\\";
									
									File dir = new File(dirUrl);
									if (!dir.exists()) {//nếu ko có thư mục thì tạo ra
										dir.mkdir();
									}
									String fileImg = dirUrl + File.separator + tenanhs;
									File file = new File(fileImg);//tạo file
									try {
										fileItem.write(file);//lưu file
										System.out.println("UPLOAD THÀNH CÔNG...!");
										System.out.println("Đường dẫn lưu file là: "+dirUrl);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							else {   //Neu la control
								String tentk=fileItem.getFieldName();
								if(action.equals("add")) {
									if(tentk.equals("txtmasach")) {
										mas = fileItem.getString(); //lấy tên từ txtmasach
									}if(tentk.equals("txttensach")) {
										tens = fileItem.getString();
									}if(tentk.equals("txtslsach")) {
										sls = fileItem.getString();
									}if(tentk.equals("txttgsach")) {
										tacgias = fileItem.getString();
									}if(tentk.equals("txtsotapsach")) {
										sotaps = fileItem.getString();
									}if(tentk.equals("txtgiasach")) {
										gias = fileItem.getString();
									}if(tentk.equals("txtloaisach")) {
										maloais = fileItem.getString();
									}
								}
								if(action.equals("edit")) {
									if(tentk.equals("txtmasach1")) {
										mas = fileItem.getString(); //lấy tên từ txtmasach
									}if(tentk.equals("txttensach1")) {
										tens = fileItem.getString();
									}if(tentk.equals("txtslsach1")) {
										sls = fileItem.getString();
									}if(tentk.equals("txttgsach1")) {
										tacgias = fileItem.getString();
									}if(tentk.equals("txtsotapsach1")) {
										sotaps = fileItem.getString();
									}if(tentk.equals("txtgiasach1")) {
										gias = fileItem.getString();
									}if(tentk.equals("txtloaisach1")) {
										maloais = fileItem.getString();
									}if(tentk.equals("txtngaynhapsach1")) {
										ngaynhaps = fileItem.getString();
									}
								}
							}
					}
					} catch (FileUploadException e) {
						e.printStackTrace();
					}
				}
				tenanhs = "image_sach/" + tenanhs;
				if(action.equals("add")) {
					/*String mas = request.getParameter("txtmasach");
					String tens = request.getParameter("txttensach");
					String sls = request.getParameter("txtslsach");
					String tacgias = request.getParameter("txttgsach");
					String sotaps = request.getParameter("txtsotapsach");
					String gias = request.getParameter("txtgiasach");
					String maloais = request.getParameter("txtloaisach");
					String ngaynhaps = request.getParameter("txtngaynhapsach");
					String tenanhs = request.getParameter("txtanhsach");*/
					sbo.AddSach(mas, tens, sls, gias, maloais, sotaps, tenanhs, tacgias);
				}
				if(action.equals("edit")) {
					if(tenanhs.equals("image_sach/"))
						sbo.UpdateSach(mas, tens, tacgias, sls, gias, maloais, sotaps, ngaynhaps);
					else
						sbo.UpdateSach(mas, tens, tenanhs, tacgias, sls, gias, maloais, sotaps, ngaynhaps);
					request.setAttribute("tensach2", mas);
					//sbo.UpdateSach(mas, tens, tenanhs, tacgias, sls, gias, maloais, sotaps, ngaynhaps);
				}
				if(action.equals("delete")) {
					mas = request.getParameter("masach");
					sbo.DeleteSach(mas);
				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("QL_Sach.jsp");
			rd.forward(request, response);
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
