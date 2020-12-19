<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form class="form-inline my-2 my-lg-0" method="post" action="TestController" enctype="multipart/form-data">
		Tên: <input class="form-control" type="text" name="txtten" placeholder="nhập tên">
		Địa chỉ: <input class="form-control" type="text" name="txtdc" placeholder="nhập địa chỉ">
		File: <input class="form-control" type="file" name="txtfile" placeholder="nhập tên">
		<input type="submit">
       </form>
</body>
</html>