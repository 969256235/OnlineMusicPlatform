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

  </head>
  
  <body>
    <div class="titleBar">
      <div class="center">
        <a href="${pageContext.servletContext.contextPath}/main.jsp" class="titleBar">RuanKoMusic</a>
      </div>
    </div>
    
    <div>
      <div>
        <button>Upload Music</button>
        <button>Manage Music</button>
        <button>Delete Music</button>
      </div>
      <div>
        <form method="post" enctype="multipart/form-data">
          <input type="file" name="">
          <input type="text">
        </form>
      </div>
    </div>
  </body>
</html>
