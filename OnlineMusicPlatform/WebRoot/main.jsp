<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="MainStyle.css">
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
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
        <a href="/OnlineMusicPlatform/main.jsp" class="titleBar">RuanKoMusic</a>
        <a href="" class="signright">Sign up</a>
        <a class="signor">or</a>
        <a href="" class="signleft">Sign in</a>
        <form action="" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub" value=" ">
          <input type="text" name="Search" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
    <div class="playerBar">
    </div>
    <div class="bodycenter">
      <img src="${pageContext.servletContext.contextPath}/images/titleImg1.jpg" class="titleImg"/>
      <div>
        
      </div>
    </div>
  </body>
</html>
