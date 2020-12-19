<%@page import="bean.sachbean"%>
<%@page import="bo.sachbo"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="bean.loaibean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.loaibo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Shop-Admin</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	ArrayList<loaibean> dsloai = (ArrayList<loaibean>)request.getAttribute("dsloai");
	int n = dsloai.size();
	ArrayList<sachbean> dssach = (ArrayList<sachbean>)request.getAttribute("dssach");
	int m = dssach.size();
	%>

	<nav class="navbar navbar-expand-lg navbar-dark bg-success"  style="position: sticky; top: -1px; z-index: 1;">
	    <a class="navbar-brand navh" href="QLSachController">SHOP</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContentLG" 
	     aria-controls="navbarSupportedContentLG" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarSupportedContentLG">
	        <ul class="navbar-nav mr-auto">
	           <li id="na" class="nav-item"><a class="nav-link" href="QLAccountController">Account</a></li>
			   <li id="na" class="nav-item active"><a class="nav-link" href="QLSachController">Sách</a></li>
			   <li id="na" class="nav-item"><a class="nav-link" href="QLHoadonController">Hóa đơn</a></li>
	        </ul>
	        <form class="form-inline my-2 my-lg-0" method="post" action="QLSachController">
				<input class="form-control navbtn" type="text" name="txtsearch" placeholder="nhập tên" 
				value="<%=request.getAttribute("timkiem")%>" >
				<input type="submit" class="btn btn-secondary btn-outline-dark my-2 my-sm-0 navbtn" name="but1" value="Tìm kiếm">
			</form>
			<div class="btn-group navbtn">
			  <button type="button" class="btn btn-primary ">
			  	<i class="glyphicon glyphicon-user"></i>
			  	 <%=session.getAttribute("ad") %>(i)
			  </button>
			  <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    <span class="sr-only">Toggle Dropdown</span>
			  </button>
			  <div class="dropdown-menu">
			    <a class="dropdown-item" href="SachController?action=dx">Đăng xuất</a>
			  </div>
			</div>
	     </div>
	</nav>
	
	<div class="container-fluit" >
			<div class="row">
				<div class="col-md-2 col-lg-2 col-xl-2 ds">
					<div class="list-group">
				      <a class="list-group-item list-group-item-action list-head" href="QLSachController"><span style="color: khaki;">SÁCH (<%=m %>)</span></a>
					  <%for(int i=0;i<n;i++) {%>
					      <a class="list-group-item list-group-item-action" href="QLSachController?type=<%=dsloai.get(i).getMaloai()%>"><%=dsloai.get(i).getTenloai()%></a>
				      <%} %>	
				    </div>
				</div>
				<div class=" col-md-10 col-lg-10 col-xl-10 sach">
						<%if(request.getAttribute("mas")!=null){%>
							<%=request.getAttribute("mas") %>
						<%} %>
						
						<div class="type-area">
							<p>Loại sách:</p>
							<select class="form-control form-control-sm" style="width: 15vw;" onchange="gotype(this.value);">
								<option>Loại</option>
								<%for(int i=0;i<n;i++) {%>
							        <option value="<%=dsloai.get(i).getMaloai()%>"><%=dsloai.get(i).getTenloai()%></option>
							    <%} %>
							</select> 
						</div>
						<div class="sort-area">
							<p>Sort by:</p>
							<select class="form-control form-control-sm" style="width: 15vw;" onchange="gosort(this.value);">
								<option <%if(session.getAttribute("sort").equals("default")){ %>selected="selected"<%}%>value="default">Default</option>							
								<option <%if(session.getAttribute("sort").equals("ten")){ %>selected="selected"<%}%> value="ten">Tên sách</option>															
								<option <%if(session.getAttribute("sort").equals("gia")){ %>selected="selected"<%}%> value="gia">Giá</option>			
							</select> 
						</div>
						<script>
							function gosort(url) {						
							  window.location.href = "QLSachController?sort="+url;
							 }
							function gotype(url) {						
								  window.location.href = "QLSachController?type="+url;
								 }
						</script>
						<button type="button" class="btn btn-info navbtn" data-toggle="modal" data-target="#myModal1" style="margin-top: 10px;">Thêm sách mới</button>
						  <div class="modal fade" id="myModal1" role="dialog">
						    <div class="modal-dialog">
						      <div class="modal-content">
						      	<form method="post" action="QLSachController?action=add" enctype="multipart/form-data">
							        <div class="modal-header">
							          <h3 class="modal-title" style="color: blue;">Thông tin sách</h3>
							        </div>
							        <div class="modal-body">
							          	  <div class="form-group row">
											    <label for="txtmasach" class="col-sm-3 col-form-label">Mã sách</label>
											    <div class="col-sm-9">
											      	<input type="text" class="form-control navbtn" name="txtmasach" id="txtmasach" placeholder="Mã sách" required>
											    </div>
										  </div>
							              <div class="form-group row">
											    <label for="txttensach" class="col-sm-3 col-form-label">Tên sách</label>
											    <div class="col-sm-9">
											      	<input type="text" class="form-control navbtn" name="txttensach" id="txttensach" placeholder="Tên sách" required>
											    </div>
										  </div>
										  <div class="form-group row">
											    <label class="col-sm-3 col-form-label">Loại Sách</label>
											    <div class="col-sm-9">
											    	<select name="txtloaisach" class="form-inline">
											    		<%for(int i=0;i<n;i++) {%>
											    			<option value="<%=dsloai.get(i).getMaloai()%>"><%=dsloai.get(i).getTenloai() %></option>
											    		<%} %>
											    	</select>
											    </div>
										  </div>
										  <div class="form-group row">
											    <label for="txtanhsach" class="col-sm-3 col-form-label">Ảnh</label>
											    <div class="col-sm-9">
											      	<input type="file" class="navbtn" name="txtanhsach" id="txtanhsach" required>
											    </div>
										  </div>
										  <div class="form-group row">
											    <label for="txttgsach" class="col-sm-3 col-form-label">Tác giả</label>
											    <div class="col-sm-9">
											      	<input type="text" class="form-control navbtn" name="txttgsach" id="txttgsach" placeholder="Tên tác giả" required>
											    </div>
										  </div>
										  <div class="form-group row">
											    <label for="txtsotapsach" class="col-sm-3 col-form-label">Số tập</label>
											    <div class="col-sm-9">
											      	<input type="number" class="form-control navbtn" name="txtsotapsach" id="txtsotapsach" value="0">
											    </div>
										  </div>
							              <div class="form-group row">
											    <label for="txtslsach" class="col-sm-3 col-form-label">Số lượng</label>
											    <div class="col-sm-9">
											      	<input type="number" class="form-control navbtn" name="txtslsach" id="txtslsach" value="0">
											    </div>
										  </div>
							          	  <div class="form-group row">
											    <label for="txtgiasach" class="col-sm-3 col-form-label">Giá</label>
											    <div class="col-sm-9">
											      	<input type="number" class="form-control navbtn" name="txtgiasach" id="txtgiasach" value="0">
											    </div>
										  </div>
							        </div>
							        <div class="modal-footer">
							        <button type="button" class="btn btn-secondary navbtn" data-dismiss="modal">Close</button>
							        <input type="submit" class="btn btn-primary navbtn" name="btnDK" value="Thêm">
							      </div>
							     </form>
						      </div>
						    </div>
						  </div>
						<hr>
						<div>
						<%for(int i=0;i<m;i++){ %>	
								<div class="item">
									<img class="img-sach" src="<%=dssach.get(i).getAnh() %>" title="<%=dssach.get(i).getTensach() %>"> <br>
									<span style="color: red;"><%=dssach.get(i).getTensach() %></span> <br>
									Tác giả: <%=dssach.get(i).getTacgia() %> <br>
									<span style="color: blue;"><%=dssach.get(i).getGia() %> đ</span> <br>
									<button type="button" class="btn btn-info navbtn edit<%=i %>">Sửa</button>
									  
									 <script>
											$(document).ready(function(){
											  $(".edit<%=i%>").click(function(){
											    $("#myModal2").modal();
											    $("#txtmasach1").val("<%=dssach.get(i).getMasach()%>");
											    $("#txttensach1").val("<%=dssach.get(i).getTensach()%>");
											    $('select[name=txtloaisach1').val("<%=dssach.get(i).getMaloai()%>");
											    //$('select[name=txtloaisach1').ea("<%=dssach.get(i).getMaloai()%>").prop('selected',true)
											   // $("#txtanhsach1").val("<%=dssach.get(i).getAnh()%>");
											    $("#txttgsach1").val("<%=dssach.get(i).getTacgia()%>");
											    $("#txtsotapsach1").val("<%=dssach.get(i).getSotap()%>");
											    $("#txtslsach1").val("<%=dssach.get(i).getSoluong()%>");
											    $("#txtgiasach1").val("<%=dssach.get(i).getGia()%>");
											    $("#txtngaynhapsach1").val("<%=dssach.get(i).getNgaynhap()%>");
											    
											  });
											});
									</script>	
									<a href="QLSachController?action=delete&masach=<%=dssach.get(i).getMasach()%>"><button class="btn btn-danger navbtn">Xóa</button></a>		
									<br><%=request.getAttribute("tensach2") %>			
								</div>
								

						<%} %>
						</div>	
						<div class="modal fade" id="myModal2" role="dialog">
									    <div class="modal-dialog">
									      <div class="modal-content">
									      	<form method="post" action="QLSachController?action=edit" enctype="multipart/form-data">
										        <div class="modal-header">
										          <h3 class="modal-title" style="color: blue;">Sửa thông tin sách</h3>
										        </div>
										        <div class="modal-body">
										          	  <div class="form-group row">
														    <label for="txtmasach1" class="col-sm-3 col-form-label">Mã sách</label>
														    <div class="col-sm-9">
														      	<input type="text" class="form-control navbtn" name="txtmasach1" id="txtmasach1"  readonly>
														    </div>
													  </div>
										              <div class="form-group row">
														    <label for="txttensach1" class="col-sm-3 col-form-label">Tên sách</label>
														    <div class="col-sm-9">
														      	<input type="text" class="form-control navbtn" name="txttensach1" id="txttensach1" >
														    </div>
													  </div>
													  <div class="form-group row">
														    <label class="col-sm-3 col-form-label">Loại Sách</label>
														    <div class="col-sm-9">
														    	<select name="txtloaisach1" id="txtloaisach1" class="form-inline">
														    		<%for(int k=0;k<n;k++) {%>
														    				<option <%if(dssach.get(k).getMaloai().equals(dsloai.get(k).getMaloai())){%>selected="selected"<%}%>
														    				 value="<%=dsloai.get(k).getMaloai() %>">
														    					<%=dsloai.get(k).getTenloai() %>
														    				</option>
														    		<%} %>
														    	</select>
														    </div>
													  </div>
													  <div class="form-group row">
														    <label for="txtanhsach1" class="col-sm-3 col-form-label">Ảnh</label>
														    <div class="col-sm-9">
														      	<input type="file" class="navbtn" name="txtanhsach1" id="txtanhsach1" placeholder="Anh">
														    </div>
													  </div>
													  <div class="form-group row">
														    <label for="txttgsach1" class="col-sm-3 col-form-label">Tác giả</label>
														    <div class="col-sm-9">
														      	<input type="text" class="form-control navbtn" name="txttgsach1" id="txttgsach1" >
														    </div>
													  </div>
													  <div class="form-group row">
														    <label for="txtsotapsach1" class="col-sm-3 col-form-label">Số tập</label>
														    <div class="col-sm-9">
														      	<input type="number" class="form-control navbtn" name="txtsotapsach1" id="txtsotapsach1" >
														    </div>
													  </div>
										              <div class="form-group row">
														    <label for="txtslsach1" class="col-sm-3 col-form-label">Số lượng</label>
														    <div class="col-sm-9">
														      	<input type="number" class="form-control navbtn" name="txtslsach1" id="txtslsach1" >
														    </div>
													  </div>
										          	  <div class="form-group row">
														    <label for="txtgiasach1" class="col-sm-3 col-form-label">Giá</label>
														    <div class="col-sm-9">
														      	<input type="number" class="form-control navbtn" name="txtgiasach1" id="txtgiasach1" >
														    </div>
													  </div>
													  <div class="form-group row">
														    <label for="txtngaynhapsach1" class="col-sm-3 col-form-label">Ngày nhập</label>
														    <div class="col-sm-9">
														      	<input type="date" class="form-control navbtn" name="txtngaynhapsach1" id="txtngaynhapsach1" >
														    </div>
													  </div>
										        </div>
										        <div class="modal-footer">
										        <button type="button" class="btn btn-secondary navbtn" data-dismiss="modal">Hủy</button>
										        <input type="submit" class="btn btn-primary navbtn" name="btnDK" value="Sửa">
										      </div>
										     </form>
									      </div>
									    </div>
									  </div>
					</div>	
			</div>
	</div>
</body>
</html>
