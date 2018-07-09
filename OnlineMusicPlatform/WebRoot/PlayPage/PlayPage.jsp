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
	<link rel="stylesheet" type="text/css" href="PlayPage/PlayPage.css">
	
  </head>
  
  <body>
   <div class = "playPage">
   	<div  class = "backgroundpicture"></div>
     <div class = "topBar">
        <a href="/OnlineMusicPlatform/main.jsp" class="titleBar">RuanKoMusic</a>
        <a href="" class="signright">Sign up</a>
        <a class="signor">or</a>
        <a href="" class="signleft">Sign in</a>
        <form action="" method="post">
          	<input type="submit" name="SearchSubmit" class="searchSub" value=" ">
          	<input type="text" name="Search" class="searchBox" placeholder="Search" autocomplete="on">
        </form>
     </div>
     <div class = "wrapper">
     	<div class = "control">
     		<img alt="AlbumCover" src="${pageContext.servletContext.contextPath}/${music.cover}" class = "albumCover">
     		<div class = "btn-audio">
     			<audio id = "mp3Btn" >
     				<source src="${pageContext.servletContext.contextPath}/${music.musicurl}" type = "audio/mpeg" />
     			</audio>
     		</div>
     		<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.10.0/jquery.min.js"></script>
     		
     	</div>
     	<div id = "lyric">
     		<div class = "songName">${music.name}</div>
     		<span class = "albumName">专辑名-${music.album}</span>
     		<span class = "singer">歌手-${music.artist}</span>
     		<div id = "lyricWrapper">
     			<div id = "lyricContainer"></div>
     		</div>
     	</div>
     	<script type="text/javascript">
     		var audio = document.getElementById('mp3Btn');
     		$(function(){
      				  //播放完毕
     		   	 	$('#mp3Btn').on('ended', function() {
                		console.log("音频已播放完成");
                		$('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/PlayPage/voice_stop.png) no-repeat center bottom','background-size':'cover'});
        				$('.albumCover').css({
               				 '-webkit-animation-play-state': 'paused',
   							 '-moz-animation-play-state': 'paused',
               				 });
        				})
                	//播放器控制
                	
                	audio.volume = 1.0;
                	$('.btn-audio').click(function() {
               			 event.stopPropagation();//防止冒泡
                		if(audio.paused){ //如果当前是暂停状态
                			audio.play(); //播放
               				 $('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/PlayPage/voice_play.png) no-repeat center bottom','background-size':'cover'});
               				
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
               				audio.pause(); //暂停
                			$('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/PlayPage/voice_stop.png) no-repeat center bottom','background-size':'cover'});
                			
                			 $('.albumCover').css({
               				 '-webkit-animation-play-state': 'paused',
   							 '-moz-animation-play-state': 'paused',
               				 });
            			}
            			
        			});
        			window.addEventListener('keydown', function(e) {
            			if (e.keyCode === 32) {
                			if (audio.paused) {
                    		audio.play();
                    		$('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/PlayPage/voice_play.png) no-repeat center bottom','background-size':'cover'});
               				
                			 $('.albumCover').css({
               				 '-webkit-transform': 'rotate(360deg)',
							 'animation': 'rotation 10s linear infinite',
							 '-moz-animation': 'rotation 10s linear infinite',
							 '-webkit-animation': 'rotation 10s linear infinite',
							 '-o-animation': 'rotation 10s linear infinite',
               				 });
               			 	}
               			 else {
                    		audio.pause();
                    		$('.btn-audio').css({'background':'url(${pageContext.servletContext.contextPath}/PlayPage/voice_stop.png) no-repeat center bottom','background-size':'cover'});
                			
                			 $('.albumCover').css({
               				 '-webkit-animation-play-state': 'paused',
   							 '-moz-animation-play-state': 'paused',
               				 });
                		}
            			}
        			}, false);
    			})
     		function parseLyric(text){
     			 //get each line from the text
        		var lines = text.split('\n'),
            	//this regex mathes the time [00.12.78]
            	pattern = /\[\d{2}:\d{2}.\d{2}\]/g,
            	result = [];

        		// Get offset from lyrics
        		var offset = this.getOffset(text);

        		//exclude the description parts or empty parts of the lyric
        		while (!pattern.test(lines[0])) {
           		lines = lines.slice(1);
        		};
        		//remove the last empty item
        		lines[lines.length - 1].length === 0 && lines.pop();
        		//display all content on the page
        		lines.forEach(function(v, i, a) {
            	var time = v.match(pattern),
                value = v.replace(pattern, '');
            	time.forEach(function(v1, i1, a1) {
                	//convert the [min:sec] to secs format then store into result
                	var t = v1.slice(1, -1).split(':');
                	result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]) + parseInt(offset) / 1000, value]);
            		});
        		});
        		//sort the result by time
        		result.sort(function(a, b) {
            		return a[0] - b[0];
        		});
				console.log(result);
        		return result;
     		}
     		function getOffset(text){
     			//Returns offset in miliseconds.
        		var offset = 0;
        		try {
           		 	// Pattern matches [offset:1000]
            		var offsetPattern = /\[offset:\-?\+?\d+\]/g,
                	// Get only the first match.
                	offset_line = text.match(offsetPattern)[0],
                	// Get the second part of the offset.
                	offset_str = offset_line.split(':')[1];
            		// Convert it to Int.
            		offset = parseInt(offset_str);
        		} catch (err) {
            	//alert("offset error: "+err.message);
            	offset = 0;
        		}
        		return offset;
     		}
     		function getLyric(url){
     			var that = this,
            	request = new XMLHttpRequest();
        		request.open('GET', url, true);
        		request.responseType = 'text';
        		request.onload = function() {
            		that.lyric = that.parseLyric(request.response);
            		//display lyric to the page
            		that.appendLyric(that.lyric);
        		};
        		request.onerror = request.onabort = function(e) {
            		that.lyricContainer.textContent = '!failed to load the lyric :(';
        		}
        		this.lyricContainer.textContent = 'loading lyric...';
        		request.send();
        		
        		audio.addEventListener("timeupdate", function(e) {
            	if (!that.lyric) return;
            	for (var i = 0, l = that.lyric.length; i < l; i++) {
                	if (this.currentTime > that.lyric[i][0] - 0.50 /*preload the lyric by 0.50s*/ ) {
                    	//single line display mode
                    	// that.lyricContainer.textContent = that.lyric[i][1];
                    	//scroll mode
                    	var line = document.getElementById('line-' + i),
                        	prevLine = document.getElementById('line-' + (i > 0 ? i - 1 : i));
                    	prevLine.className = '';
                    	//randomize the color of the current line of the lyric
                    	line.className = 'current-line-' + 1;
                    	that.lyricContainer.style.top = 37 - line.offsetTop + 'px';
                	};
            	};
        	});
     		}
     		function appendLyric(lyric){
        		var that = this,
            	lyricContainer = this.lyricContainer,
            	fragment = document.createDocumentFragment();
        		//clear the lyric container first
        		this.lyricContainer.innerHTML = '';
        		lyric.forEach(function(v, i, a) {
            		var line = document.createElement('p');
            		line.id = 'line-' + i;
            		line.textContent = v[1];
            		fragment.appendChild(line);
       			 });
        		lyricContainer.appendChild(fragment);
        	}
     		
     		getLyric("${pageContext.servletContext.contextPath}/upload/20180704000030.lrc")
     		
     	</script>
     </div>
     <div class = "comment">
     
     </div>
   </div>
  </body>
</html>
