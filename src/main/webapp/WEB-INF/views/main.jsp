<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="./header.jsp" %>
<html>
<body>
	<div id="main-container">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="#"><span class="glyphicon glyphicon-user" id="name">${name}</span></a></li>
		        <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
  
		<h3 class="text-info">하둡 대용량 파일 업로드</h3>
			<span>하둡서버에 파일업로드 가능 
			 </span>
			 <span id="chk"></span>
			<a href="/list"><button type="button" class="btn btn-info" id="btn">파일저장소</button></a>
		<hr>
		<div style="margin-bottom: 20px">
			<input type="file" id="files" multiple style="margin-bottom: 20px"  enctype="multipart/form-data"/>
			<button class="btn btn-primary" type="button" onclick="startUploading()" ><i class="fa fa-upload"></i> Upload file</button>
		</div>
		<div id="upload-status-container">
			<div id="upload-header">
				<span id="upload-header-text"></span>
				<i class="action-icon fa fa-window-minimize pull-right" onclick="showHide(this)" title="minimize"></i>
			</div>
			<div id="progress-bar-container">
				<table class="table">
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>