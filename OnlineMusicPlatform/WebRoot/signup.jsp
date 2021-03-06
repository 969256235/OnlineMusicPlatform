<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>signup</title>
     <link rel="stylesheet" type="text/css" href="./style/Sign.css">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

  <body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <form class="form-signin" action="/OnlineMusicPlatform/Signup" method="post">
              <h2 class="form-signin-heading">SIGN UP</h2>
              <div id="info">
                <div class = "fill-one">
                  <label for="">Account    </label>
                  <input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名" required autofocus>
                </div>
                <div class = "fill-two">
                  <label for="">Nickname    </label>
                  <input type="text" name="nickname" id="nickname" class="form-control" placeholder="请输入昵称" required autofocus>
                </div>
                <div class = "fill-three">
                  <label for="">Password   </label>
                  <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required>
                </div>
                <div class = "fill-four">
                  <label for="">Repeat     </label>
                  <input type="password" name="password2" id="password2" class="form-control" placeholder="请再次输入密码" required maxLength="16">
                </div>
              </div>
              <button type="submit" class="btn btn-primary" id="btn-reg">Sign Up</button>
              <br />
              <a href="signin.jsp" class="btn btn-default" id="btn-reg">Return to Sign In</a>
            </form>
        </div>
    </div>
  </body>
</html>
