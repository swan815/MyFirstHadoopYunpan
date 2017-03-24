<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/include/head.jsp"></jsp:include>
</head>

<body>
	<div class="container-fluid">
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">hadoop云盘-上传文件</h3>
					</div>
					<div class="panel-body">

						<c:if test="${not empty flag }">
							<div class="alert alert-${flag } alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<strong>${flag }!</strong> ${msg }
							</div>
						</c:if>

						<form role="form" action="${ctx }/UploadServlet" method="post"  enctype="multipart/form-data"  >
							<div class="form-group">
								<label for="file">File input</label> <input name="file1" type="file" id="file">
								<p class="help-block">上传文件.</p>
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
						<form role="form" action="${ctx }/BackServlet" method="post"  enctype="multipart/form-data"  >
							<div class="form-group">
							</div>
							<button type="submit" class="btn btn-primary">Back</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>