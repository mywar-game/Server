
	// 遮挡层
	// add by ps
	var W = screen.width; // 取得屏幕分辨率宽度
	var H = screen.height; // 取得屏幕分辨率高度
	
	// 判断浏览器是否为IE
	var isIE = function() {
		return (document.all && window.ActiveXObject && !window.opera) ? true : false;
	}
	
	// 取得页面的高宽
	function getBodySize() {
		var bodySize = [];
		with (document.documentElement) {
			bodySize[0] = (scrollWidth > clientWidth) ? scrollWidth : clientWidth; // 如果滚动条的宽度大于页面的宽度，取得滚动条的宽度，否则取页面宽度
        	bodySize[1] = (scrollHeight > clientHeight) ? scrollHeight : clientHeight; // 如果滚动条的高度大于页面的高度，取得滚动条的高度，否则取高度
		}
		return bodySize;
	}
	
	// 创建遮挡层
	var popCoverDiv = function() {
		if (document.getElementById("cover_div")) {
			document.getElementById("cover_div").style.display = 'block';
		} else {
			
			// 创建遮挡层
			var coverDiv = document.createElement('div');
			document.body.appendChild(coverDiv);
			coverDiv.id = 'cover_div';
			with (coverDiv.style) {
				position = 'absolute';
				background = '#CCCCCC';
				left = '0px';
				top = '0px';
				var bodySize = getBodySize();
				width = bodySize[0] + 'px';
				height = bodySize[1] + 'px';
				zIndex = 0;
				if (isIE()) {
					filter = 'Alpha(Opacity = 60)';
				} else {
					opacity = 0.6;
				}
			}
			
			document.getElementById('cover_div').innerHTML = '<div id="circularG" style="margin:0 auto"> <div id="circularG_1" class="circularG"></div>	<div id="circularG_2" class="circularG"></div>		<div id="circularG_3" class="circularG"></div>		<div id="circularG_4" class="circularG"></div>		<div id="circularG_5" class="circularG"></div>		<div id="circularG_6" class="circularG"></div>		<div id="circularG_7" class="circularG"></div>		<div id="circularG_8" class="circularG"></div>';
			var circularG = document.getElementById("circularG");
			with (circularG.style) {
				position = 'absolute';
				top = H / 4 + 'px'; // 居中显示
				left = W / 4 + 'px'; // 居中显示
			}
		}
	}
	
	// 去除遮挡层
	var removeCoverDiv = function() {
		//document.getElementById("cover_div").style.display = 'none';
		var coverDiv = document.getElementById("cover_div");
		if (coverDiv) {
			coverDiv.parentNode.removeChild(coverDiv);
		}
	}
	