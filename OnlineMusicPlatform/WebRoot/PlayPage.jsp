<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>play page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/PlayPage.css">
		<link rel="stylesheet" type="text/css" href="style/Theme.css">
	
  </head>
  
  <body>
   <div class = "playPage">
     <div class="titleBar">
       <div class="center">
         <a href="${pageContext.servletContext.contextPath}/main.jsp" class="titleBar">RankoMusic</a>
         <a href="${pageContext.servletContext.contextPath}/signup.jsp" class="signright">Sign up</a>
         <a class="signor">or</a>
         <a href="${pageContext.servletContext.contextPath}/signin.jsp" class="signleft">Sign in</a>
         <form action="/OnlineMusicPlatform/Search" method="post">
           <input type="submit" name="SearchSubmit" class="searchSub" value="">
           <input type="text" name="Search" class="searchBox" placeholder="Search" autocomplete="on">
         </form>
       </div>
     </div>
     <div class = "wrapper">
     	<div class = "control">
     		<img alt="AlbumCover" src="${pageContext.servletContext.contextPath}/images/hotest.png" class = "albumCover">
     		<div class = "btn-audio">
     			<audio id = "mp3Btn" >
     				<source src="${pageContext.servletContext.contextPath}/upload/201807060919.mp3" type = "audio/mpeg" />
     			</audio>
     		</div>
     		<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.10.0/jquery.min.js"></script>
     		<script type="text/javascript">
   				 $(function(){
      				  //播放完毕
     		   	 	$('#mp3Btn').on('ended', function() {
                		console.log("音频已播放完成");
                		$('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/style/voice_stop.png) no-repeat center bottom','background-size':'cover'});
        				$('.albumCover').css({
               				 '-webkit-animation-play-state': 'paused',
   							 '-moz-animation-play-state': 'paused',
               				 });
        				})
                	//播放器控制
                	var audio = document.getElementById('mp3Btn');
                	audio.volume = 1.0;
                	$('.btn-audio').click(function() {
               			 event.stopPropagation();//防止冒泡
                		if(audio.paused){ //如果当前是暂停状态
               				 $('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/style/voice_play.png) no-repeat center bottom','background-size':'cover'});
               			
                		audio.play(); //播放
                			 $('.albumCover').css({
               				 '-webkit-transform': 'rotate(360deg)',
							 'animation': 'rotation 10s linear infinite',
							 '-moz-animation': 'rotation 10s linear infinite',
							 '-webkit-animation': 'rotation 10s linear infinite',
							 '-o-animation': 'rotation 10s linear infinite',
               				 });
                		return;
               			 }
               			else{//当前是播放状态
                			$('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/style/voice_stop.png) no-repeat center bottom','background-size':'cover'});
                			audio.pause(); //暂停
                			 $('.albumCover').css({
               				 '-webkit-animation-play-state': 'paused',
   							 '-moz-animation-play-state': 'paused',
               				 });
            			}
            			
        			});
    			})
   			</script>
     	</div>
     	<div class = "lyricWrapper">
     		<div class = "lyricContainer"></div>
     	</div>
     </div>
     <div class = "comment">
     
     </div>
   </div>
  </body>
</html>
