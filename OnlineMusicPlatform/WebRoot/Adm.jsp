<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Adm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="style/Theme.css">
	<link rel="stylesheet" type="text/css" href="style/MainStyle.css">

  </head>
  
  <body>
    <div class="titleBar">
      <div class="center">
        <a href="${pageContext.servletContext.contextPath}/" class="titleBar">RuanKoMusic</a>
        <form action="/OnlineMusicPlatform/AdmSearch" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub2" value="">
          <input type="text" name="SearchText" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
    
    <div class="bodycenter">
      <div>
        <button>Upload Music</button>
        <button>Manage Music</button>
        <button>Delete Music</button>
      </div>
      <div>
        <form method="post" enctype="multipart/form-data" action="/OnlineMusicPlatfrom/FileUpload">
          <input type="file" name="FileName">
          <input type="text" name="name" placeholder="Name" autocomplete="on">
          <input type="text" name="realname" placeholder="Realname" autocomplete="on">
          <input type="text" name="artist" placeholder="Artist" autocomplete="on">
          <input type="text" name="album" placeholder="Album" autocomplete="on">
          <input type="file" name="Lrc">
          <input type="text" name="zone" placeholder="Zone" autocomplete="on">
          <input type="text" name="publishdate" placeholder="Publish-Date" autocomplete="on">
          <input type="file" name="Cover">
          <input type="file" name="Artist_Photo">
          <input type="submit">
        </form>
      </div>
      <div>
        <form method="post" action="/OnlineMusicPlatform/AdmSearch">
          <input type="text" name="search">
          <input type="submit">
        </form>
      </div>
    </div>
  </body>
</html>
