<%@page import="bean.giohangbean"%>
<%@page import="bo.giohangbo"%>
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
  <title>Shop-Giỏ hàng</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div >
			<a href="SachController"><img src="image_sach/anhbia.jpg" style="width:100%; height:25vw;"></a>
	</div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-success"  style="position: sticky; top: -1px; z-index: 1;">
	    <a class="navbar-brand" href="SachController">SHOP</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContentLG" 
	     aria-controls="navbarSupportedContentLG" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarSupportedContentLG">
	        <ul class="navbar-nav mr-auto">
	            <li id="na" class="nav-item">
	                <a class="nav-link" href="SachController">Trang chủ<span class="sr-only">(current)</span></a>
	            </li>
	            <%
		        Object slgh2 = session.getAttribute("slgh");
		        if(slgh2!=null && (int)slgh2!=0){
		        %>
		      		  <li id="na" class="nav-item active"><a class="nav-link" href="DatmuaController">Giỏ hàng
		      			 <span class="badge badge-pill badge-warning" ><%=slgh2 %></span></a></li>
		        <% }else{ %>
		      		 <li id="na" class="nav-item active"><a class="nav-link" href="DatmuaController">Giỏ hàng</a></li>
			   <%} %>
	           <li id="na" class="nav-item"><a class="nav-link" href="ThanhtoanController">Thanh toán</a></li>
			   <li id="na" class="nav-item"><a class="nav-link" href="LsmuahangController">Lịch sử mua hàng</a></li>
	        </ul>
	        <form class="form-inline my-2 my-lg-0" method="post" action="DatmuaController" style="display: none;">
				<input class="form-control" type="text" name="txtsearch" placeholder="nhập tên" 
				value="<%=session.getAttribute("ten")%>" >
				<input type="submit" class="btn btn-secondary btn-outline-dark my-2 my-sm-0" name="but1" value="Tìm kiếm">
	
	        </form>
	        <%if(session.getAttribute("acc")==null || session.getAttribute("acc").equals("")) { %>
					    <div id="log" style="margin-left:15px;">
						      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1" >Đăng nhập</button>
							  <div class="modal fade" id="myModal1" role="dialog">
							    <div class="modal-dialog">
							      <div class="modal-content">
							     	 <form method="post" action="SachController">
								        <div class="modal-header">
								          <h3 class="modal-title" style="color: blue;">Thông tin đăng nhập</h3>
								        </div>
								        <div class="modal-body">
								          	  <div class="form-group">
								                <label for="unDN">User name</label>
								                <input type="text" class="form-control panel-info" name="unDN" id="unDN" placeholder="User name" required>
								              </div>
								              <div class="form-group">
								                <label for="passDN">Password</label>
								                <input type="password" class="form-control panel-info" name="passDN" id="passDN" placeholder="Password" required>
								              </div>
									          <a href="#">Quên mật khẩu?</a><br>
									          <a href="#" id="tab1">Đăng ký</a>
								        </div>
								        <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								        <input type="submit" class="btn btn-primary" name="btnDN" value="Đăng nhập">
								      </div>
							        </form>
							      </div>
							    </div>
							  </div>
							  <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal2">Đăng ký</button>
							  <div class="modal fade" id="myModal2" role="dialog">
							    <div class="modal-dialog">
							      <div class="modal-content">
							      	<form method="post" action="SachController">
								        <div class="modal-header">
								          <h3 class="modal-title" style="color: blue;">Thông tin đăng ký</h3>
								        </div>
								        <div class="modal-body">
								          	  <div class="form-group row">
												    <label for="unDK" class="col-sm-3 col-form-label">User Name</label>
												    <div class="col-sm-9">
												      	<input type="text" class="form-control" name="unDK" id="unDK" placeholder="User Name">
												    </div>
											  </div>
								              <div class="form-group row">
												    <label for="passDK" class="col-sm-3 col-form-label">Password</label>
												    <div class="col-sm-9">
												      	<input type="password" class="form-control" name="passDK" id="passDK" placeholder="Password">
												    </div>
											  </div>
								              <div class="form-group row">
												    <label for="nameDK" class="col-sm-3 col-form-label">Your Name</label>
												    <div class="col-sm-9">
												      	<input type="text" class="form-control" name="nameDK" id="nameDK" placeholder="Your Name">
												    </div>
											  </div>
								          	  <div class="form-group row">
												    <label for="dcDK" class="col-sm-3 col-form-label">Address</label>
												    <div class="col-sm-9">
												      	<input type="text" class="form-control" name="dcDK" id="dcDK" placeholder="Address">
												    </div>
											  </div>
											  <div class="form-group row">
												    <label for="sdtDK" class="col-sm-3 col-form-label">Phone</label>
												    <div class="col-sm-9">
												      	<input type="tel" class="form-control" name="sdtDK" id="sdtDK" placeholder="Phone number">
												    </div>
											  </div>
											  <div class="form-group row">
												    <label for="mailDK" class="col-sm-3 col-form-label">Email</label>
												    <div class="col-sm-9">
												      	<input type="email" class="form-control" name="mailDK" id="mailDK" placeholder="Email">
												      	<small id="emailHelp" class="form-text text-muted">    We'll never share your email with anyone else.</small>
												    </div>
											  </div>
								            <a href="#" id="tab2">Đăng nhập</a>
								        </div>
								        <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								        <input type="submit" class="btn btn-primary" name="btnDK" value="Đăng ký">
								      </div>
								     </form>
							      </div>
							    </div>
							  </div> 				  
						</div>
						<script >
							$("#tab1").on( "click", function() {
							    $('#myModal1').modal('hide'); 
							    $('#myModal2').modal('show');
							});
							$("#tab2").on( "click", function() {
							    $('#myModal2').modal('hide'); 
							    $('#myModal1').modal('show');
							});
						</script>
					<%}else{ 
						if(session.getAttribute("acc").equals("ad")){%>
							<div class="btn-group" style="margin-left:10px;">
							  <button type="button" class="btn btn-primary">
							  	<i class="fas fa-file"></i> <span class="glyphicon glyphicon-user"></span>
							    <%=session.getAttribute("ad") %>(i)
							  </button>
							  <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    <span class="sr-only">Toggle Dropdown</span>
							  </button>
							  <div class="dropdown-menu">
							    <a class="dropdown-item" href="SachController?action=dx">Đăng xuất</a>
							  </div>
							</div>
						<%}
						if(session.getAttribute("acc").equals("kh")){%>
							<div class="btn-group" style="margin-left:10px;">
							  <button type="button" class="btn btn-primary">
							    <%=session.getAttribute("kh") %>
							  </button>
							  <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    <span class="sr-only">Toggle Dropdown</span>
							  </button>
							  <div class="dropdown-menu">
							    <a class="dropdown-item" href="SachController?action=dx">Đăng xuất</a>
							  </div>
							</div>
					<%}
				}%>	
	     </div>
	</nav>
	<div class="container-fluit">
		<div class="row" style="background-color: palegreen; margin-right: 0px;">
			<div class="col-xs-8 col-sm-9 col-md-9">
				<table class="table table-sm" style="background-color: lightcyan;">
				<%
				giohangbo gh = (giohangbo)session.getAttribute("gh");
				if(gh!=null)
					for(giohangbean g: gh.getds()){
				%>
					<tr>
						<td>
							<div style="padding:5px 5px;">
								<div style="float: right; margin-right: 10px;">
									Giá: <%=g.getGia() %> <br>
									<%String sl = request.getParameter("txtsl"); %>
									<form method="post" action="DatmuaController?action=update&ma=<%=g.getMasach()%>">
										Số lượng: <input type="number" name="txtsl" value=<%=g.getSoluong() %> style="width:35px;"> 
										<input type="submit" name="but1" value="Sửa" class="btn btn-primary btn-sm navbtn">
									</form>
									Số tiền: <%=g.getThanhTien() %> <br>
								</div>
								<div style="margin-left: 10px;">
									<img src="<%=g.getAnh() %>" style="float: left; width :14%; height: 17%; padding: 5px;">
									<span ><%=g.getTensach() %></span> <br>
									<span style="color: red;"><%=g.getTacgia() %></span> <br> <br>
									<a href="DatmuaController?action=delete&ma=<%= g.getMasach() %>">
										<button type="button" class="btn btn-danger btn-sm navbtn">Trả lại</button>
									</a>
								</div>
							</div>
						</td>
					</tr>
				<%}%>
				</table>
			</div>
			<div class="col-xs-4 col-sm-3 col-md-3">
				<div style="width: 90%; height: 70px; margin-top: 10px; background-color: lightcyan;">
					Thành tiền: <%=gh.Tong() %> VNĐ
				</div>
				<div style="float: right; margin-right: 6vw;">
					<%if(session.getAttribute("acc")==null || session.getAttribute("acc").equals("")) { %>
						<button type="button" id="btn4" class="btn btn-info btn-sm" style="width: 110px;">Tạo đơn hàng</button>
					<%}
					if(session.getAttribute("acc").equals("kh")){ %>
						<a href="ThanhtoanController?action=addHD"><button type="button" class="btn btn-info btn-sm" style="width: 110px;">Tạo đơn hàng</button></a>
					<%} %>
				</div>
				<script>
						$(document).ready(function(){
						  $("#btn4").click(function(){
						    $("#myModal1").modal();
						  });
						});
				</script>
			</div>
		</div>
	</div>

</body>
</html>