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
    <title>My JSP 'signup.jsp' starting page</title>
     <link rel="stylesheet" type="text/css" href="style/Theme.css">
    
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
   <div class="titleBar">
      <div class="center">
        <a href="${pageContext.servletContext.contextPath}/main.jsp" class="titleBar">RuanKoMusic</a>
        <a href="${pageContext.servletContext.contextPath}/signup.jsp" class="signright">Sign up</a>
        <a class="signor">or</a>
        <a href="${pageContext.servletContext.contextPath}/signin.jsp" class="signleft">Sign in</a>
        <form action="" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub" value=" ">
          <input type="text" name="Search" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
    <body>
    <nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="./">RuanKoMusic</a>
        </div>
      
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">

            <form class="form-signin" action="reg-submit.jsp" method="post">
                <h2 class="form-signin-heading">SIGN UP</h2>
                <div id="info">

                </div>
                <label for="">Account</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Please Enter Account" required autofocus><br>
                <label for="">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Please Enter Password" required><br>
                <label for="">Confirm Password</label>
                <input type="password" name="password2" id="password2" class="form-control" placeholder="Please Confirm Password" required maxLength="16"><br>
                <button type="submit" class="btn btn-primary" id="btn-reg">Sign Up</button>
                <a href="login.jsp" class="btn btn-default" id="btn-reg">Return to Sign In</a>
            </form>
        </div>
        <div class="col-md-4">
        </div>
    </div>
  </body>
</html>
