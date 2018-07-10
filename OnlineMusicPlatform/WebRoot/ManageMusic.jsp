<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManageMusic.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="style/MainStyle.css">
    <link rel="stylesheet" type="text/css" href="style/Theme.css">
    <link rel="stylesheet" type="text/css" href="style/Manage.css">
    <link rel="stylesheet" type="text/css" href="./style/Sign.css">

  </head>
  
  <body>
    <div class="titleBar">
      <div class="center">
        <a href="${pageContext.servletContext.contextPath}/GetRankList" class="titleBar">RuanKoMusic</a>
        <form action="/OnlineMusicPlatform/AdmSearch" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub2" value="">
          <input type="text" name="SearchText" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
     <div class="bodycenter">
        <form method="post" enctype="multipart/form-data" action="/OnlineMusicPlatform/ManageMusic">
          <input type="text" name="id" style="display: none" value="${mbmd.id}">
          <input type="text" name="name" value="${mbmd.name}"><br>
          <input type="text" name="realname" value="${mbmd.realname}"><br>
          <input type="text" name="artist" value="${mbmd.artist}"><br>
          <input type="text" name="album" value="${mbmd.album}"><br>
          <input type="text" name="zone" value="${mbmd.zone}"><br>
          <input type="text" name="publishdate" value="${mbmd.publishdate}"><br>
          <input type="submit">
        </form>
      </div>
  </body>
</html>
