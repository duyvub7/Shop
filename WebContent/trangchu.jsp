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
  <title>Shop-Trang chủ</title>
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
	ArrayList<loaibean> ds = (ArrayList<loaibean>)request.getAttribute("dsloai");
	int n = ds.size();
	
	ArrayList<sachbean> dssach = (ArrayList<sachbean>)request.getAttribute("dssach");
	int m = dssach.size();
	
	%>
	<div >
			<a href="SachController"><img class="img-head" src="image_sach/anhbia.jpg" style="width:100%; height:25vw;"></a>
	</div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-success"  style="position: sticky; top: -1px; z-index: 1;">
	    <a class="navbar-brand navh" href="SachController">SHOP</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContentLG" 
	     aria-controls="navbarSupportedContentLG" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarSupportedContentLG">
	        <ul class="navbar-nav mr-auto">
	            <li id="na" class="nav-item active">
	                <a class="nav-link" href="SachController">Trang chủ<span class="sr-only">(current)</span></a>
	            </li>
	            <%
		        Object slgh2 = session.getAttribute("slgh");
		        if(slgh2!=null && (int)slgh2!=0){
		        %>
		      		  <li id="na" class="nav-item"><a class="nav-link" href="DatmuaController">Giỏ hàng
		      			 <span class="badge badge-pill badge-warning" ><%=slgh2 %></span></a></li>
		        <% }else{ %>
		      		 <li id="na" class="nav-item"><a class="nav-link" href="DatmuaController">Giỏ hàng</a></li>
			   <%} %>
	           <li id="na" class="nav-item"><a class="nav-link" href="ThanhtoanController">Thanh toán</a></li>
			   <li id="na" class="nav-item"><a class="nav-link" href="LsmuahangController">Lịch sử mua hàng</a></li>
	        </ul>
	        <form class="form-inline my-2 my-lg-0" method="post" action="SachController">
	        	<div style="display:none;">
					<h3>Tìm kiếm sách:</h3>
					Nhập mã loại: <input class="panel-info navbtn" type="text" name="txtmaloai" id="txtmaloai" 
						placeholder="ma loai" value="<%=request.getAttribute("maloai")%>">
				</div>
				<input class="form-control navbtn" type="text" name="txtsearch" placeholder="nhập tên" 
				value="<%=session.getAttribute("timkiem")%>" >
				<input type="submit" class="btn btn-secondary btn-outline-dark my-2 my-sm-0 navbtn" name="but1" value="Tìm kiếm">
	
	        </form>
	        <%if(session.getAttribute("acc")==null || session.getAttribute("acc").equals("")) { %>
					    <div class="log-area" id="log">
						      <button type="button" class="btn btn-primary navbtn" data-toggle="modal" data-target="#myModal1" >Đăng nhập</button>
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
								                <input type="text" class="form-control panel-info navbtn" name="unDN" id="unDN" placeholder="User name" required>
								              </div>
								              <div class="form-group">
								                <label for="passDN">Password</label>
								                <input type="password" class="form-control panel-info navbtn" name="passDN" id="passDN" placeholder="Password" required>
								              </div>
									          <a href="#">Quên mật khẩu?</a><br>
									          <a href="#" id="tab1">Đăng ký</a>
								        </div>
								        <div class="modal-footer">
								        <button type="button" class="btn btn-secondary navbtn" data-dismiss="modal">Close</button>
								        <input type="submit" class="btn btn-primary navbtn" name="btnDN" value="Đăng nhập">
								      </div>
							        </form>
							      </div>
							    </div>
							  </div>
							  <button type="button" class="btn btn-info navbtn" data-toggle="modal" data-target="#myModal2">Đăng ký</button>
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
												      	<input type="text" class="form-control navbtn" name="unDK" id="unDK" placeholder="User Name" required>
												    </div>
											  </div>
								              <div class="form-group row">
												    <label for="passDK" class="col-sm-3 col-form-label">Password</label>
												    <div class="col-sm-9">
												      	<input type="password" class="form-control navbtn" name="passDK" id="passDK" placeholder="Password" required>
												    </div>
											  </div>
								              <div class="form-group row">
												    <label for="nameDK" class="col-sm-3 col-form-label">Your Name</label>
												    <div class="col-sm-9">
												      	<input type="text" class="form-control navbtn" name="nameDK" id="nameDK" placeholder="Your Name" required>
												    </div>
											  </div>
								          	  <div class="form-group row">
												    <label for="dcDK" class="col-sm-3 col-form-label">Address</label>
												    <div class="col-sm-9">
												      	<input type="text" class="form-control navbtn" name="dcDK" id="dcDK" placeholder="Address">
												    </div>
											  </div>
											  <div class="form-group row">
												    <label for="sdtDK" class="col-sm-3 col-form-label">Phone</label>
												    <div class="col-sm-9">
												      	<input type="tel" class="form-control navbtn" name="sdtDK" id="sdtDK" placeholder="Phone number">
												    </div>
											  </div>
											  <div class="form-group row">
												    <label for="mailDK" class="col-sm-3 col-form-label">Email</label>
												    <div class="col-sm-9">
												      	<input type="email" class="form-control navbtn" name="mailDK" id="mailDK" placeholder="Email">
												      	<small id="emailHelp" class="form-text text-muted">    We'll never share your email with anyone else.</small>
												    </div>
											  </div>
								            <a href="#" id="tab2">Đăng nhập</a>
								        </div>
								        <div class="modal-footer">
								        <button type="button" class="btn btn-secondary navbtn" data-dismiss="modal">Close</button>
								        <input type="submit" class="btn btn-primary navbtn" name="btnDK" value="Đăng ký">
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
						<%}
						if(session.getAttribute("acc").equals("kh")){%>
							<div class="btn-group navbtn">
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
	
	<%if(session.getAttribute("loi")!=null && session.getAttribute("loi")!=""){
		if(session.getAttribute("loi").equals("dn")){ %>
			<div class="alert alert-danger alert-dismissible" id="loi" role="alert">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h3>Thông tin đăng nhập sai!</h3>
			</div>
		<%}if(session.getAttribute("loi").equals("dk")){%>
			<div class="alert alert-danger alert-dismissible" id="loi" role="alert">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h3>Tên đăng nhập đã tồn tại!</h3>
			</div>
	<%} } %>
	<script >
		$("#btnX").on( "click", function() {
		    $('#loi').hide(); 
		});
	</script>
	
	<div class="container-fluit" >
			<div class="row">
				<div class="col-md-2 col-lg-2 col-xl-2 ds">
					<div class="list-group">
				      <a class="list-group-item list-group-item-action list-head" href="SachController"><span style="color: khaki;">SÁCH (<%=m %>)</span></a>
					  <%for(int i=0;i<n;i++) {%>
					      <a class="list-group-item list-group-item-action" href="SachController?type=<%=ds.get(i).getMaloai()%>"><%=ds.get(i).getTenloai()%></a>
				      <%} %>	
				    </div>
				</div>
				<div class=" col-md-10 col-lg-10 col-xl-10 sach">
						<div >	
							<div class="type-area">
								<p>Loại sách:</p>
								<select class="form-control form-control-sm" style="width: 15vw;margin-top: -1vw;" onchange="gotype(this.value);">
									<option>Loại</option>
									<%for(int i=0;i<n;i++) {%>
								        <option value="<%=ds.get(i).getMaloai()%>"><%=ds.get(i).getTenloai()%></option>
								    <%} %>
								</select> 
							</div>
							<div class="sort-area">
								<p>Sort by:</p>
								<select class="form-control form-control-sm" style="width: 15vw;" name="sorttype" onchange="gosort(this.value);">
									<option <%if(session.getAttribute("sort").equals("default")){ %>selected="selected"<%}%>value="default">Default</option>							
									<option <%if(session.getAttribute("sort").equals("ten")){ %>selected="selected"<%}%> value="ten">Tên sách</option>															
									<option <%if(session.getAttribute("sort").equals("gia")){ %>selected="selected"<%}%> value="gia">Giá</option>			
								</select>  
							</div>
							<script>
								function gosort(url) {						
								  window.location.href = "SachController?sort="+url;
								 }
								function gotype(url) {						
									  window.location.href = "SachController?type="+url;
									 }
							</script>
						</div>
						
						<hr style="padding-top: 1px;width:100%;clear: left;">
						<div>
						<%for(int i=0;i<m;i++){ %>	
								<div class="item">
									<img class="img-sach" src="<%=dssach.get(i).getAnh() %>" title="<%=dssach.get(i).getTensach() %>"> <br>
									<span style="color: red;"><%=dssach.get(i).getTensach() %></span> <br>
									Tác giả: <%=dssach.get(i).getTacgia() %> <br>
									<span style="color: blue;"><%=dssach.get(i).getGia() %> đ</span> <br>
									<a href="DatmuaController?ms=<%=dssach.get(i).getMasach()%>&ts=<%=dssach.get(i).getTensach()%>&tg=<%=dssach.get(i).getTacgia()%>&
										anh=<%=dssach.get(i).getAnh()%>&gia=<%=dssach.get(i).getGia()%>">
											<img class="img-buy" src="image_sach/buynow.jpg">
									</a>
									
								</div>
						<%} %>
						</div>	
					</div>	
			</div>
	</div>
</body>
</html>
