<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-success"  style="position: sticky; top: -1px; z-index: 1;">
	    <a class="navbar-brand" href="SachController">SHOP</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContentLG" 
	     aria-controls="navbarSupportedContentLG" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarSupportedContentLG">
	        <ul class="navbar-nav mr-auto">
	            <li id="na" class="nav-item active">
	                <a class="nav-link" href="SachController">Trang chủ<span class="sr-only">(current)</span></a>
	            </li>
		      	<li id="na" class="nav-item"><a class="nav-link" href="DatmuaController">Giỏ hàng</a></li>
	           <li id="na" class="nav-item"><a class="nav-link" href="thanhtoan.jsp">Thanh toán</a></li>
			   <li id="na" class="nav-item"><a class="nav-link" href="SachController">Lịch sử mua hàng</a></li>
	        </ul>
	        <form class="form-inline my-2 my-lg-0" method="post" action="SachController">
				<input class="form-control" type="text" name="txttacgia" placeholder="ten tac gia" value="<%=session.getAttribute("tacgia")%>" >
				<input type="submit" class="btn btn-secondary btn-outline-dark my-2 my-sm-0" name="but1" value="Tìm kiếm">
	        </form>
	        <button type="button" class="btn btn-primary">Đăng nhập</button>
	    </div>
	 </nav>
	 
	 
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
			        </div>
			        <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <input type="submit" class="btn btn-primary" name="btnDK" value="Đăng ký">
			      </div>
			     </form>
		      </div>
		    </div>
		  </div> 	
							  

		  <button type="button" class="btn btn-primary" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    Account
		  </button>
		  <div class="dropdown-menu">
		    <a class="dropdown-item" href="SachController?action=dx">Đăng xuất</a>
		  </div>					  		
</body>
</html>