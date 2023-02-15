<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="./header.jsp" %>
<!DOCTYPE html>
<body>
	<div id="main-container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><span class="glyphicon glyphicon-user" id="name">${name}</span></a></li>
						<li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<h3 class="text-info">하둡 대용량 파일 저장소</h3>
		<span>저장소 사용률: %  총 사용율 </span>
			<a href="/user"><button type="button" class="btn btn-info" id="btn">파일업로드하기</button></a>
		<hr>
		<c:forEach var="boardList" items="${boardList}">
			<div class="main-container">
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#collapse${boardList.id}" onclick="getFileList('${boardList.boardid}',' ${boardList.id}');">${boardList.boardnm}</a>
								<button type="button" onclick="delBtn('${boardList.boardid}');" class="btn btn-warning" id="btn">삭제</button>
							</h4>
						</div>
						<div id="collapse${boardList.id}" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row">
									<div class="grid col-sm-2" id="box">파일수</div>
									<div class="grid col-sm-2">${boardList.filecnt}</div>
									<div class="grid col-sm-2" id="box">파일크기</div>
									<div class="grid col-sm-2">${boardList.filesum}</div>
									<div class="grid col-sm-2" id="box">등록일</div>
									<div class="grid col-sm-2">${fn:substring(boardList.createdate,0,10)}</div>
								</div>
							</div>
							<div class="panel-footer">
								<ul id="fileList${boardList.id}">
								</ul>					
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>

<script>
//파일 다운로드 
function downloadFile(name, dir) {
	axios({
		method: 'POST', //통신 방식
		url: "http://52.78.34.69:3000/download/", //통신할 페이지
		data: {
			fileNm : name,
			dir: dir
		}
	}).then(resData=>{
		window.location.href =resData.data;
	}).catch(error => {  
        console.log(error);
    });
	
}
function getFileList(boardid, id){
	var elementId ='#fileList'+id;
	var ele =elementId.split(' ').join('');
	axios.post('/getFileList', {
		boardid:boardid,
		}).then(res => {
 			for(var i = 0; i<=res.data.length; i++){
 				var name = res.data[i].filenm;
 				var dir = res.data[i].hdfsdir;
 				$(ele).append("<a href=javascript:downloadFile('"+name+"','"+dir+"'\);><li>"+name+"</li></a>")
			}
		}).catch(error => {
			console.log(error);
		});
};

function delBtn(boardid){
	axios.post('/delFileList', {
		boardid:boardid,
		}).then(res => {
			alert("성공적으로 삭제되었습니다.")
			location.reload();
		}).catch(error => {
			console.log(error);
		});
	
	//하둡지우기 
	axios.get('http://52.78.34.69:3000/delete/', {
		boardid:boardid,
		}).then(res => {
			alert("성공적으로 삭제되었습니다.")
		}).catch(error => {
			console.log(error);
	});
}


</script>