<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<style type="text/css">
body {
	color: #333;
	background-color: #fff;
}
.main {
	position: absolute; /*绝对定位*/
	top: 50%; /* 距顶部50%*/
	left: 50%; /* 距左边50%*/
	height: 200px;
	margin-top: -150px; /*margin-top为height一半的负值*/
	width: 400px;
	margin-left: -200px; /*margin-left为width一半的负值*/
}
</style>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>hadoop云盘-用户登陆</title>
</head>
<body>
	<div class="main">
		<div class="panel panel-primary ">
			<div class="panel-heading">
				<h3 class="panel-title">hadoop云盘-用户登陆</h3>
			</div>
			<div class="panel-body">
				<form role="form" action="LoginServlet" method="post">
					<div class="form-group">
						<label for="username">username</label> <input type="text" class="form-control" id="username"
						name="username"	placeholder="username">
					</div>
					<div class="form-group">
						<label for="password">password</label> <input type="password" class="form-control" id="password"
						name="password"	placeholder="password">
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" name="remember"> Check me out
						</label>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>