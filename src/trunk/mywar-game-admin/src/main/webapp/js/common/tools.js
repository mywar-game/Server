//*******************************************************************************绘图相关************************************************************************************************************

/**
 * 发送ajax得到表中最大的数据
 * @return
 */
function sendAjax(url) {
	var tableMaxValue ;
	$.ajax( {
		type : "POST",
		url : url,
		dataType : 'json',
		async: false ,
		success : function(msg) {
			tableMaxValue = msg;
		}
	});
	//返回数据处理
	return tableMaxValue; 
}

/**
 * 根据columnIndex得到该列的
 * @param columnIndex 第几列
 * @param isConvertNumber 
 * @return
 */
function getColumnValue(columnIndex,isConvertNumber,isWord){
	var array = new Array();
	var table = document.getElementById('table');
	for(var i=2 ;i<table.rows.length-1;i++){
		var x = '';
		var tr = table.rows[i];
		var td = tr.cells[columnIndex];
		if(isConvertNumber){
			//统计表格里面格式化数字有逗号，在此消除
			var re=new RegExp(",","g");
			array.push(parseFloat(td.innerHTML.replace(re,'')));
		} else if(isWord){
			alert(td.innerHTML);
			var childNodes = td.childNodes;
			for(var j = 0; j<childNodes.length; j++){
				if(childNodes[j].nodeType==1 && childNodes[j].nodeName=="A"){
					//alert($.trim(childNodes[j].innerHTML));
					var s = $.trim(childNodes[j].innerHTML);
					for ( var k = 0; k < s.length; k++) {
						//alert(s.substring(i,i+1));
						x += s.substring(k,k+1)+'<br/>';
					}
				}
			}
			alert(x);
			array.push(x);
		}else{
			array.push(td.innerHTML);
		}
	}
	return 	array;
}

/**
 * 得到绘图数据
 * @param startColumn 
 * @return
 */
function getSeries(columnIndexArray){
	//绘图数据对象
	var series = [];
	//填充标题栏数组
	var tableTitle = [];
	var table = document.getElementById('table');
	var tr = table.rows[1];
	for(var i=0; i<tr.cells.length;i++){
		tableTitle.push(tr.cells[i].innerHTML);
	}
	//平凑绘图数据数组
	for(var i=0 ;i<columnIndexArray.length;i++){
		series.push({name:tableTitle[columnIndexArray[i]],data:getColumnValue(columnIndexArray[i],true)});
	}
	return series;
}