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
    <link rel="stylesheet" type="text/css" href="./style/Sign.css">

    <title>signin</title>

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
            <form class="form-signin" method="post" action="/OnlineMusicPlatform/Signin">
                <h2 class="form-signin-heading">SIGN IN</h2>
                <div id="info">
                  <div class = "fill-one">
                    <label for="">Account</label>
                    <input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名" required autofocus>
                  </div>
                  <div class = "fill-two">
                    <label for="">Password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required>
                  </div>
                  <div class="checkbox">
                    <label>
                      <input type="radio" name="box" id=box1 value=admin/>Admin
                       <input type="radio" name="box"id=box2 value=user/>User
                    </label>
                  </div>
                  <button type="submit" class="btn btn-primary" id="btn-login">Sign In</button>
                  <a href="signup.jsp" class="btn btn-default">Sign Up</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
