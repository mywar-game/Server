function Ajax() {
    var XMLHttp = false;
    var ObjSelf;
    ObjSelf=this;
    try { XMLHttp=new XMLHttpRequest; }// 在 Mozilla 中创建 XMLHttpRequest 对象
    catch(e) {
        try { XMLHttp=new ActiveXObject("MSXML2.XMLHttp"); }//较新 IE 里
        catch(e2) {
            try { XMLHttp=new ActiveXObject("Microsoft.XMLHttp"); }//较旧IE 里
            catch(e3) { XMLHttp=false; }
        }
    }
    if (!XMLHttp) return false;
    this.method="POST";
    this.url=""
    this.url += (this.url.indexOf("?") >= 0) ? "&nowtime=" + new Date().getTime():"?nowtime=" + new Date().getTime();
    this.async=true;
    this.data="";
    ObjSelf.loadid=""
    this.backtext=true
    this.callback=function() {return;}

    this.send=function() {
        if(!this.method||!this.url||!this.async) return false;
        XMLHttp.open (this.method, this.url, this.async);
        if(this.method=="POST"){
            XMLHttp.setRequestHeader("Content-Length",(this.data).length); 
            XMLHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        }
//        var loadingDiv = document.getElementById("loading");
//    	if(loadingDiv!=null){
//    		alert(1);
//        	loadingDiv.style.display = "block";
//    	}
        XMLHttp.onreadystatechange=function() {
            if(XMLHttp.readyState==4) {
                //alert(ObjSelf.loadid);
                if (ObjSelf.loadid!="") $CS(ObjSelf.loadid,"none");
                //window.status="";
                if(XMLHttp.status==200) {
                    ObjSelf.callback();
//                    if(loadingDiv!=null){
//                		alert(2);
//                		loadingDiv.style.display = "none";
//                		loadingDiv.innerHTML = "";
//                	}
                }
            }
            else {
                if (ObjSelf.loadid!="") $CS(ObjSelf.loadid,"block");
//                window.status="状态：["+XMLHttp.readyState+"]正在加载......";
            }
        }

        if(this.method=="POST") XMLHttp.send(this.data);
        else XMLHttp.send(null);
    }

    this.gettext=function(){
        if(XMLHttp.readyState==4) {
            if(XMLHttp.status==200) {
                if (this.backtext==true){
                    return XMLHttp.responseText;
                }else{
                    return XMLHttp.responseXML;
                }     
            }
            }
    }
}