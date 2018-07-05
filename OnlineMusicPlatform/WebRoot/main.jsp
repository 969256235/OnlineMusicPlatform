<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="style/MainStyle.css">
    <link rel="stylesheet" type="text/css" href="style/Theme.css">
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
  
  <script>
    function audioSrc(){
    	document.getElementById("mp3").pause();
    }
  </script>
  
  <body>
    <div class="titleBar">
      <div class="center">
        <a href="${pageContext.servletContext.contextPath}/main.jsp" class="titleBar">RuanKoMusic</a>
        <a href="${pageContext.servletContext.contextPath}/signup.jsp" class="signright">Sign up</a>
        <a class="signor">or</a>
        <a href="${pageContext.servletContext.contextPath}/signin.jsp" class="signleft">Sign in</a>
        <form action="" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub" value="">
          <input type="text" name="Search" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
    <div class="playerBar">
      <div class="center">
        <audio controls style="height: 4em; margin: 0em 1.8em 0em 1.8em; width: 35em;" id="mp3">
    	  <source src="${pageContext.servletContext.contextPath}/upload/201807021545.mp3" type="audio/mpeg" />
        </audio>
      </div>
    </div>
    <div class="bodycenter">
      <img src="${pageContext.servletContext.contextPath}/images/titleImg1.jpg" class="titleImg"/>
      <a href="" class="Lable">Classify</a>
      <p class="line">line1</p>
      <div>
      	<table id="rankTable">
      	  <tr>
      	    <th>HOTEST</th>
      	    <th>NEWEST</th>
      	    <th>
      	  </tr>
      	  <tr>
      	    <th>${RankList[0]}<button class="musicbutton" onclick="audioSrc()">add</button></th>
      	    <th class="middle">${RankList[1]}<button class="musicbutton">add</button></th>
      	    <th>${RankList[2]}<button class="musicbutton">add</button></th>
      	  </tr>
      	</table>
      </div>
    </div>
  </body>
</html>
