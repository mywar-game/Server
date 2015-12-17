var Class = {

	create : function() {

		return function() {

			this.initialize.apply(this, arguments);

		}

	}

};

Object.extend = function(destination, source) {

	for ( var property in source)
		destination[property] = source[property];

	return destination;

};

Function.prototype.bind = function(object) {

	var __method = this;

	return function() {

		// 使用object对象替换__method中的this并且调用__method方法

		return __method.apply(object, arguments);

	}

};

// forEach方法：将this改为bind对象，并以参数this[i],i调用fn方法，this[i]是当前数组元素，i是当前索引

if (!Array.prototype.forEach) {

	Array.prototype.forEach = function(fn, bind) {

		for ( var i = 0; i < this.length; i++)
			fn.call(bind, this[i], i);

	};

}

// each方法同forEach

Array.prototype.each = Array.prototype.forEach;

// 返回字符串的骆驼写法.e.g. "font-color".camelize() -> fontColor,处理css属性时需要

String.prototype.camelize = function() {

	return this.replace(/-\D/gi, function(match) {

		return match.charAt(match.length - 1).toUpperCase();

	});

};

var $A = function(iterable) {

	var nArray = [];

	for ( var i = 0; i < iterable.length; i++)
		nArray.push(iterable[i]);

	return nArray;

};

/*
 * 
 * 如果传入的参数为一个则返回一个element，否者返回一个数组
 * 
 */

function $() {

	if (arguments.length == 1)
		return get$(arguments[0]);

	var elements = [];

	$c(arguments).each(function(el) {

		elements.push(get$(el));

	});

	return elements;

	function get$(el) {

		if (typeof el == 'string')
			el = document.getElementById(el);

		return el;

	}

};

if (!window.Element)
	var Element = {};

Object.extend(Element,
		{

			remove : function(element) {// 删除指定的元素

				element = $(element);

				element.parentNode.removeChild(element);

			},

			hasClassName : function(element, className) {// 判断指定的元素是否有相应的css
															// class

				element = $(element);

				return !!element.className.match(new RegExp("\\b" + className
						+ "\\b"));

			},

			addClassName : function(element, className) {// 给相应的元素加上指定的css

				element = $(element);

				if (!Element.hasClassName(element, className))
					element.className = (element.className + ' ' + className);

			},

			removeClassName : function(element, className) {// 给相应的元素删除指定的css

				element = $(element);

				if (Element.hasClassName(element, className))
					element.className = element.className
							.replace(className, '');

			}

		});

// 返回具有相同css class的元素

document.getElementsByClassName = function(className) {

	var elements = [];

	var all = document.getElementsByTagName('*');

	$A(all).each(function(el) {

		if (Element.hasClassName(el, className))
			elements.push(el);

	});

	return elements;

};