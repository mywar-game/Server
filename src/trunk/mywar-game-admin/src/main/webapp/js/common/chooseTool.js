
// 清空获取道具
function clearTool(tableTd, idsColumn, showColumn, clearComfirm) {
	var mark = window.confirm(clearComfirm);
	if (!mark) {
		return;
	}
	
	var obj = tableTd.parentNode.parentNode;
	var childrenArr = obj.children;

	if (childrenArr.length > 4) {
		var children = childrenArr[idsColumn];
		var inputNode = children.children[0];
		inputNode.value = '';

		childrenArr[showColumn].innerHTML = '';
	}
}

// 去掉字符串的空格
function trim(str) {
	str = str.replace(/^(\s|\u00A0)+/, '');
	for (var i = str.length - 1; i >= 0; i--) {
		if (/\S/.test(str.charAt(i))) {
			str = str.substring(0, i + 1);
			break;
		}
	}
	return str;
}

// 添加获取道具
function addTool(tableTd, idsColumn, showColumn) {
	var hight = (screen.height - 300) / 2.8;
	var width = (screen.width - 500) / 2;
	var str = window.showModalDialog("addTool", "", "dialogLeft=" + width
			+ "; dialogTop=" + hight
			+ "; dialogWidth=500px; dialogHeight=300px; location=no");

	if (str == null || str == '') {
		return;
	}

	// 获取到的道具
	var strArr = str.split('*');
	var obj = tableTd.parentNode.parentNode; // tr
	var childrenArr = obj.children; // tdArr

	if (childrenArr.length < 4) {
		return;
	}

	// 获取原有的rewards
	var children = childrenArr[idsColumn]; // td
	var inputNode = children.children[0];
	var old = inputNode.value;
	trim(old);

	if (old != "") {
		var v = old + "|" + strArr[0];
		v = trim(v);
		inputNode.value = v;
	} else {
		inputNode.value = trim(strArr[0]);
	}

	children = childrenArr[showColumn];
	old = children.innerHTML;
	if (old != "") {
		children.innerHTML = old + "|" + strArr[1];
	} else {
		children.innerHTML = strArr[1];
	}
}