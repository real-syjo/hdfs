<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/login.css" rel="stylesheet">
</head>
<body>
<div class="login-wrap">
		<div class="login-html">
			<c:if test="${error == true}">
				<h4>${exception}</h4> <br>
			</c:if>
			<c:if test="${status == 'YY'}">
				<h4>이미 가입한 계정입니다 로그인해주세요!</h4> <br>
			</c:if>
			<c:if test="${status == 'Y'}">
				<h4>성공적으로 가입되었습니다 로그인해주세요!</h4> <br>
			</c:if>
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
				<label for="tab-1" class="tab">로그인</label> 
				<input id="tab-2" type="radio" name="tab" class="sign-up">
				<label for="tab-2" class="tab">회원가입</label>
			<div class="login-form">
				<div class="sign-in-htm">
					<form action="/login" method="POST">
						<div class="group">
								<label for="username" class="label">Email</label> 
								<input name="username" type="email" class="input" placeholder="abc@email.com">
						</div>
						<div class="group">
							<label for="password" class="label">Password</label> 
							<input name="password" type="password" class="input" placeholder="******">
						</div>
						<div class="group">
							<input type="submit" class="button" value="로그인">
						</div>
					</form>
						<div class="social-group"><br>
							<a  class="social" href="/oauth2/authorization/google">구글 로그인</a>
							<a  class="social" href="/oauth2/authorization/facebook">페이스북 로그인</a>
							<a  class="social" href="/oauth2/authorization/naver">네이버 로그인</a>
						</div><br><br>
						<div class="foot-lnk">
							<a href="#forgot">Forgot Password?</a>
						</div>
				</div>
				<div class="sign-up-htm">
					<form action="/join" method="post">
						<div class="group">
							<label for="username" class="label">Email</label> 
							<input type="email" name="username" id="chk" class="input" >
						</div>
						<div class="group">
							<label for="password" class="label">Password</label> 
							<input type="password" name="password" class="input" >
						</div>
						<div class="group">
							<input type="submit" class="button" onClick="join()" value="가입완료">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">Already Member?</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

