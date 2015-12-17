<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="css/main.css" rel="stylesheet" type="text/css" />

<table cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
  <tr class="border">
    <td class="td_title">表头样式td_title</td>
  </tr>
  <tr>
    <td> 
	普通行样式 不需要额外定义 CSS中已经定义好了TR TD的样式
      </td>
  </tr>
   <tr><td>
          文本输入框：<input name="" type="text" class="textbox" value="22" maxlength="">
    </td></tr>
        
    <tr><td>
          多选框：<input name="" type="checkbox" class="checkbox" value="22" maxlength="">
    </td></tr>
    
    <tr><td>
          按钮:  <input name="" type="button" class="button" value="按钮"  >
    </td></tr>
    
    <tr><td>
          文件域:  <input type="file" name="" class="file"/>
    </td></tr>
    
     <tr><td>
      单选 <input type="radio" name="" class="radio" value="单选" />
    </td></tr>
    
     <tr><td>列表   <select name="" class="select">
          <option >值1</option>
          <option selected="selected">值2</option>
          <option >值3</option>
          </select> 
    </td></tr>
    <tr><td>链接： <a href="#" title="title样式">链接样式</a></td></tr>
    
    <tr><td>
    文本域：
    <textarea name="" class="textarea"></textarea>
    </td></tr>

</table>
