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
    <link rel="stylesheet" type="text/css" href="style/Theme.css">
    <link rel="stylesheet" type="text/css" href="style/MainStyle.css">
    
    <title>My JSP 'search.jsp' starting page</title>
    
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
        <a href="${pageContext.servletContext.contextPath}/" class="titleBar">RankoMusic</a>
        <a href="${pageContext.servletContext.contextPath}/signup.jsp" class="signright">Sign up</a>
        <a class="signor">or</a>
        <a href="${pageContext.servletContext.contextPath}/signin.jsp" class="signleft">Sign in</a>
        <form action="/OnlineMusicPlatform/Search" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub" value="">
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
      	    <td><c:out value="${result.name}"/><a href="/OnlineMusicPlatform/SingleMusic?musicname=${result}"><button class="musicbutton"></button></a></td>
      	  </tr>
      	  </c:forEach>
      	</table>
      </div>
    </div>
  </body>
</html>
