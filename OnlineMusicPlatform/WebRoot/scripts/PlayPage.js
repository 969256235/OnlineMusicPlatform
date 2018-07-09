/*
 * Selected | a collection of songs that I love
 * v0.3.0
 * also as a showcase that shows how to sync lyric with the HTML5 audio tag
 * Wayou  Apr 5th,2014
 * view on GitHub:https://github.com/wayou/selected
 * see the live site:http://wayou.github.io/selected/
 * songs used in this project are only for educational purpose
 */
window.onload = function() {
    new Selected().init();
};
var Selected = function() {
    this.lyricContainer = document.getElementById('lyricContainer');
    this.currentIndex = 0;
    this.lyric = null;
    this.lyricStyle = 0; //random num to specify the different class name for lyric
};
Selected.prototype = {
    constructor: Selected, //fix the prototype chain
    init: function() {
        var that = this,
            currentSong, 
            randomSong;

        //get the hash from the url if there's any.
        var songName = "Git Fresh - Booty Music";
        that.play(songName);
        currentSong = "${pageContext.servletContext.contextPath}/${music.musicurl}";

        //enable keyboard control , spacebar to play and pause
        

        currentSong.className = 'current-song';
        this.play(randomSong);
    },
    
    play: function(songName) {
        var that = this;
        //reset the position of the lyric container
        this.lyricContainer.style.top = '130px';
        //empty the lyric
        this.lyric = null;
        this.lyricContainer.textContent = 'loading...';
        this.audio.addEventListener('canplay', function() {
            that.getLyric(that.audio.src.replace('.mp3', '.lrc'));
            this.play();
        });
        //sync the lyric
        this.audio.addEventListener("timeupdate", function(e) {
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
                    line.className = 'current-line-' + that.lyricStyle;
                    that.lyricContainer.style.top = 130 - line.offsetTop + 'px';
                };
            };
        });
    },


    getLyric: function(url) {
        var that = this,
            request = new XMLHttpRequest();
       
        that.lyric = that.parseLyric(url);
        that.appendLyric(that.lyric);
        this.lyricContainer.textContent = 'loading lyric...';
       
    },
    parseLyric: function(text) {
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
    },
    appendLyric: function(lyric) {
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
    },
    getOffset: function(text) {
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
};
