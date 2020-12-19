<%@page import="bean.accountbean"%>
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
	ArrayList<accountbean> ds = (ArrayList<accountbean>)request.getAttribute("dsacc");
	int n = ds.size();
	
	%>

	<nav class="navbar navbar-expand-lg navbar-dark bg-success"  style="position: sticky; top: -1px; z-index: 1;">
	    <a class="navbar-brand navh" href="QLAccountController">SHOP</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContentLG" 
	     aria-controls="navbarSupportedContentLG" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarSupportedContentLG">
	        <ul class="navbar-nav mr-auto">
	           <li id="na" class="nav-item active"><a class="nav-link" href="QLAccountController">Account</a></li>
			   <li id="na" class="nav-item"><a class="nav-link" href="QLSachController">Sách</a></li>
			   <li id="na" class="nav-item"><a class="nav-link" href="QLHoadonController">Hóa đơn</a></li>
	        </ul>
	        <form class="form-inline my-2 my-lg-0" method="post" action="QLAccountController">
	        	<div style="display:none;">
					<h3>Tìm kiếm sách:</h3>
					Nhập mã loại: <input class="panel-info navbtn" type="text" name="txtmaloai" id="txtmaloai" 
						placeholder="ma loai" value="<%=request.getAttribute("maloai")%>">
				</div>
				<input class="form-control navbtn" type="text" name="txtsearch" placeholder="nhập tên" 
				value="<%=session.getAttribute("ten")%>" >
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
			<div class="row sach" style="min-height: 720px;">
				<div class=" col-10" style="margin-left: 10%;">
						<div>	
							<div class="type-area" style="float: left;display:block;margin-top:0px;">
								<p>Loại sách:</p>
								<select class="form-control form-control-sm" style="width: 15vw;" onchange="gotype(this.value);">
									<option value="ad" <%if(request.getAttribute("type").equals("ad")){ %>selected="selected"<%}%>>Admin</option>
									<option value="kh" <%if(request.getAttribute("type").equals("kh")){ %>selected="selected"<%}%>>Khách hàng</option>	
								</select> 
							</div>
							<div class="sort-area" >
								<p>Sort by:</p>
								<select class="form-control form-control-sm" style="width: 15vw;" name="sorttype" onchange="gosort(this.value);">
									<%if(session.getAttribute("sort").equals("default")){ %>
										<option selected="selected" value="default">Default</option>
									<%}else{ %>
										<option value="default">Default</option>
									<%}if(session.getAttribute("sort").equals("ten")){ %>
										<option selected="selected" value="ten">Tên đăng nhập</option>
									<%}else{ %>
										<option value="ten">Tên đăng nhập</option>
									<%}%>
								</select> 
							</div>
							<script>
								function gosort(url) {						
								  window.location.href = "QLAccountController?sort="+url;
								 }
								function gotype(url) {						
									  window.location.href = "QLAccountController?type="+url;
									 }
							</script>
						</div>
						<hr style="padding-top: 1px;">
						
						<%if(request.getAttribute("type").equals("ad")) { %>
							<table class="table table-striped" style="border: 1px solid green;">
								<tr>
									<th>Tên đăng nhập</th>
									<th>Mật khẩu</th>
									<th>Quyền</th>
								</tr>
								<%for(int i=0;i<n;i++){ %>	
								<tr>
									<td><%=ds.get(i).getTendn() %></td>
									<td><%=ds.get(i).getPass() %></td>
									<td><%=ds.get(i).getQuyen() %></td>
								</tr>
							<%} %>
							</table>
						<%}else{ %>
							<table class="table table-striped" style="border: 1px solid green;">
								<tr>
									<th>Tên đăng nhập</th>
									<th>Mật khẩu</th>
									<th>Mã KH</th>
									<th>Họ tên</th>
									<th>Địa chỉ</th>
									<th>Số điện thoại</th>
									<th>Email</th>
									<th></th>
								</tr>
								<%for(int i=0;i<n;i++){ %>	
								<tr>
									<td><%=ds.get(i).getTendn() %></td>
									<td><%=ds.get(i).getPass() %></td>
									<td><%=ds.get(i).getMakh() %></td>
									<td><%=ds.get(i).getTen() %></td>
									<td><%=ds.get(i).getDiachi() %></td>
									<td><%=ds.get(i).getSdt() %></td>
									<td><%=ds.get(i).getEmail() %></td>
									<td>
										<button type="button" class="btn btn-info btn-sm navbtn edit<%=i%>" data-toggle="modal" data-target="#myModal1" >Sửa</button>
										
									</td>
									<script>
											$(document).ready(function(){
											  $(".edit<%=i%>").click(function(){
											    $("#myModal1").modal();
											    $("#un").val("<%=ds.get(i).getTendn()%>");
											    $("#pass").val("<%=ds.get(i).getPass()%>");
											    $("#ma").val("<%=ds.get(i).getMakh()%>");
											    $("#ten").val("<%=ds.get(i).getTen()%>");
											    $("#dc").val("<%=ds.get(i).getDiachi()%>");
											    $("#sdt").val("<%=ds.get(i).getSdt()%>");
											    $("#email").val("<%=ds.get(i).getEmail()%>");
											    
											  });
											});
									</script>
								</tr>
							<%} %>
							</table>
						<%} %>
						<div class="modal fade" id="myModal1" role="dialog">
										    <div class="modal-dialog">
										      <div class="modal-content">
										     	 <form method="post" action="QLAccountController?action=edit">
											        <div class="modal-header">
											          <h3 class="modal-title" style="color: blue;">Thông tin khách hàng</h3>
											        </div>
											        <div class="modal-body">
											          	  <div class="form-group">
											                <label for="un">Tên đăng nhập</label>
											                <input type="text" class="form-control panel-info navbtn" name="un" id="un" placeholder="Tên đăng nhập"  required>
											              </div>
											              <div class="form-group">
											                <label for="pass">Mật khẩu</label>
											                <input type="text" class="form-control panel-info navbtn" name="pass" id="pass" placeholder="Mật khẩu" required>
											              </div>
											              <div class="form-group">
											                <label for="ma">Mã KH</label>
											                <input type="text" class="form-control panel-info navbtn" name="ma" id="ma"  disabled="disabled">
											              </div>
												          <div class="form-group">
											                <label for="ten">Họ tên</label>
											                <input type="text" class="form-control panel-info navbtn" name="ten" id="ten" placeholder="Họ tên">
											              </div>
											              <div class="form-group">
											                <label for="dc">Địa chỉ</label>
											                <input type="text" class="form-control panel-info navbtn" name="dc" id="dc" placeholder="Địa chỉ">
											              </div>
											              <div class="form-group">
											                <label for="sdt">SDT</label>
											                <input type="text" class="form-control panel-info navbtn" name="sdt" id="sdt" placeholder="Số điện thoại">
											              </div>
											              <div class="form-group">
											                <label for="email">Email</label>
											                <input type="email" class="form-control panel-info navbtn" name="email" id="email" placeholder="Email" >
											              </div>
											        </div>
											        <div class="modal-footer">
											        <button type="button" class="btn btn-secondary navbtn" data-dismiss="modal">Close</button>
											        <input type="submit" class="btn btn-primary navbtn" name="btnDN" value="Sửa">
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
