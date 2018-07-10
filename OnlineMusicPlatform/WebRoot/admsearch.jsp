<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
    
    <title>My JSP 'admsearch.jsp' starting page</title>
    
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
        <a href="${pageContext.servletContext.contextPath}/" class="titleBar">RankoMusic</a>
        <form action="/OnlineMusicPlatform/AdmSearch" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub2" value="">
          <input type="text" name="SearchText" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
    <div class="bodycenter">
      <a href="" class="Lable">RESULT</a>
      <p class="line">line1</p>
      <div>
      	<table id="resultTable">
      	  <c:forEach var="result" items="${SearchList}">
      	  <tr>
      	    <td><c:out value="${result.name}"/><a href="/OnlineMusicPlatform/ShowManage?name=${result.name}"><button class="musicbutton"></button></a><a href="/OnlineMusicPlatform/DeleteMusic?name=${result.name}"><button class="deletemusic"></button></a></td>
      	  </tr>
      	  </c:forEach>
      	</table>
      </div>
    </div>
  </body>
</html>
