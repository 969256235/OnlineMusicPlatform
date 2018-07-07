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
    var musicList = new Array();
    function addMusic(musicAddress){
    	musicList.push(musicAddress);
    	document.getElementById("mlLength").innerHTML = musicList.length;
    }
    function nextMusic(audio){
    	if (musicList.length > 0){
    		audio.setAttribute("src", "http://localhost:8080/OnlineMusicPlatform/upload/" + musicList.pop());
    		audio.play();
    		document.getElementById("mlLength").innerHTML = musicList.length;
    	}
    }
  </script>
  
  <body>
    <div class="titleBar">
      <div class="center">
        <a href="${pageContext.servletContext.contextPath}/main.jsp" class="titleBar">RuanKoMusic</a>
        <a href="${pageContext.servletContext.contextPath}/signup.jsp" class="signright">Sign up</a>
        <a class="signor">or</a>
        <a href="${pageContext.servletContext.contextPath}/signin.jsp" class="signleft">Sign in</a>
        <form action="/OnlineMusicPlatform/Search" method="post">
          <input type="submit" name="SearchSubmit" class="searchSub" value="">
          <input type="text" name="SearchText" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
      </div>
    </div>
    <div class="playerBar">
      <div class="center">
        <p id="mlLength" class="text">0</p>
        <audio controls style="display: block; height: 4em; margin: 0em 1.8em 0em 1.8em; width: 35em;" id="mp3" onended="nextMusic(this)" src="${pageContext.servletContext.contextPath}/upload/201807021545.mp3">
        </audio>
      </div>
    </div>
    <div class="bodycenter">
      <img src="${pageContext.servletContext.contextPath}/images/titleImg1.jpg" class="titleImg"/>
      <a href="" class="Lable">RANK</a>
      <p class="line">line1</p>
      <div>
      	<table id="rankTable">
      	  <tr>
      	    <th>HOTEST</th>
      	    <th>LATEST</th>
      	    <th>RECOMMEND</th>
      	  </tr>
      	  <tr>
      	    <td class="firstImgs"><img src="${pageContext.servletContext.contextPath}/images/hotest.png" class="firstImg"/></td>
      	    <td class="firstImgs"><img src="${pageContext.servletContext.contextPath}/images/newest.png" class="firstImg"/></td>
      	    <td class="firstImgs"><img src="${pageContext.servletContext.contextPath}/images/hotest.png" class="firstImg"/></td>
      	  </tr>
      	  <c:forEach var="rank" items="${RankList}">
      	  <tr>
      	    <td><c:out value="${rank[0].name}"/><button class="musicbutton" onclick="addMusic('${rank[0].realname}')"></button></td>
      	    <td><c:out value="${rank[1].name}"/><button class="musicbutton" onclick="addMusic('${rank[1].realname}')"></button></td>
      	    <td><c:out value="${rank[2].name}"/><button class="musicbutton" onclick="addMusic('${rank[2].realname}')"></button></td>
      	  </tr>
      	  </c:forEach>
      	</table>
      </div>
    </div>
  </body>
</html>
