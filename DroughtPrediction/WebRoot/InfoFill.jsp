<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="db" class="com.xpy.db.BeanTest" scope="page">  
</jsp:useBean>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
 	<title>信息填写</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  
 
  </head>
    <script  src="js/jquery-1.7.2.min.js">//引入jquery框架
  </script>
<%
   List<String> list=new ArrayList<String>();
   List<String> list1=new ArrayList<String>();
   String sql="select distinct 站点 from rain";
   ResultSet rs=db.select(sql);
   String sql1="select distinct 年 from rain where 站点=";
   


	while(rs.next())
	{
		list.add(rs.getString("站点"));	    
	}   
   
%>

  <body>
<center>
	<font size="22"><strong>风险参数选择</strong></font><br><br>
	
   <form action="Fill_done.jsp" method="post" name="myform" id="myform" onsubmit="return validate()">  
   
    <select name="station" id="station" onchange="yearchoose()">
     <option selected value="-1">=请选择站点=</option>
	<%for(int i=0;i<list.size();i++){ %>
	<option value=<%=list.get(i)%>><%=list.get(i)%></option>
 
	<%}%>
	</select>站点

	
<% 

%>
	<select name="year" id="year" onchange="monthchoose()">

 	<option selected value="-1">=请选择年份=</option>

	</select>年
		<select name="month" id="month">
		<option selected value="-1">=请选择月份=</option>
	</select>月
	<br>
	<select name="spi" id="spi">
	<br>
 	<option selected value="-1">=请选择SPI=</option>
		<option value="1">SPI 1</option>
		<option value="3">SPI 3</option>
		<option value="6">SPI 6</option>
		<option value="9">SPI 9</option>
		<option value="12">SPI 12</option>
	</select>	
	干旱严重程度
	<select name="v1" id="v1">
	<option value="-1">=请选择干旱严重程度=</option>
		<option  selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	洪涝严重程度
	<select name="v2" id="v2">
	<option  value="-1">=请选择洪涝严重程度=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>

	<br>
	<br>
		<font size="4"><a href="1.1.3.2风险参数显示表格.htm">(风险参数填写说明)</a><br>


	<table  align="center">
	
	
	<tr>


	<td>
	<font size="6"><strong>选项名</strong></font>
	</td>

	<td>
	<font size="6"><strong>可能性</strong></font>
	</td>

	<td>
	<font size="6"><strong>严重程度</strong></font>
	</td>
	</tr>
	<tr>
	<td></td>
	<td>
	<font size="2">1到5可能性依次增大</font>
	</td>
	<td>
	<font size="2">1到5严重程度依次增加</font>
	</td>
	</tr>
	<tr>
	<td>
	<font size="4"><strong>水源</strong>
	</font>
	</td>
	
	</tr>
	<tr>
	<td>
	集水效率
	</td>
	<td>
	<select name="v3" id="v3">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v4" id="v4">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>
	
	</tr>
	<tr>
	<td>
	水窖总容积/户
	</td>
	<td>
	<select name="v5" id="v5">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v6" id="v6">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>
	
	<tr>
	<td>
	病原菌
	</td>
	<td>
	<select name="v7" id="v7">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v8" id="v8">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>
	
	<tr>
	<td>
	其它化学物质_坤
	</td>
	<td>
	<select name="v9" id="v9">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v10" id="v10">
	<option  value="-1">=请选择等级=</option>
		<option  selected  value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>
	</tr>
	
	
	<tr>
	<td>
	其它化学物质_氟化物
	</td>
		<td>
	<select name="v11" id="v11">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v12" id="v12">
	<option  value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>
	</tr>
	<tr>
	<td>
	<font size="4"><strong>水处理技术(水厂)</strong>
	</font>
	</td>
	
	</tr>
	<tr>
		<td>
	水厂处理技术可靠性
	</td>
	<td>
	<select name="v13" id="v13">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v14" id="v14">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>
	
	<tr>
	<td>
	监测设备可靠性
	</td>
	<td>
	<select name="v15" id="v15">
	<option value="-1">=请选择等级=</option>
		<option selected  value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v16" id="v16">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>

	<tr>
	<td>
	化学和消毒系统可靠性
	</td>
	<td>
	<select name="v17" id="v17">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v18" id="v18">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>
	</tr>
	
	<tr>
	<td>
	工作人员培训
	</td>
	<td>
	
	<select name="v19" id="v19">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v20" id="v20">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>
	<tr>
	<td>
	<font size="4"><strong>分配系统</strong>
	</font>
	</td>
	
	</tr>
	<tr>
	<td>
	分配系统完整性
	</td>
	<td>
	<select name="v21" id="v21">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v22" id="v22">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>
	</tr>
	
	<tr>
	<td>
	供电故障/水泵故障
	</td>
	<td>
	<select name="v23" id="v23">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v24" id="v24">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>
	</tr>

		<tr>
	<td>
	<font size="4"><strong>用户储水环节</strong>
	</font>
	</td>
	
	</tr>
	<tr>
	<td>
	用户家用储水设备情况
	</td>
	<td>
	<select name="v25" id="v25">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v26" id="v26">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>

	<tr>
	<td>
	取水容器清洁程度
	</td>
	<td>
	<select name="v27" id="v27">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td><p>
	<td>
	<select name="v28" id="v28">
	<option value="-1">=请选择等级=</option>
		<option selected value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select>
	</td>	
	</tr>

	
	</table>
	<br>
	<center>
	<input type="submit" value="插入" style="height=20px;width=100px;"  onclick="">
	</center>
	</form>
	


	<script>
	function getajaxHttp() {
			var xmlHttp;
			try {
				// Firefox, Opera 8.0+, Safari  
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				// Internet Explorer  
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("您的浏览器不支持AJAX！");
						return false;
					}
				}
			}
			return xmlHttp;
		}
	function ajaxrequest(url, methodtype, con, functionName) {
			//获取XMLHTTPRequest对象
			var xmlhttp = getajaxHttp();
			//设置回调函数（响应的时候调用的函数）
			xmlhttp.onreadystatechange = function() {
				//这个函数中的代码在什么时候被XMLHTTPRequest对象调用？
				//当服务器响应时，XMLHTTPRequest对象会自动调用该回调方法
				if (xmlhttp.readyState == 4) {
					if (xmlhttp.status == 200) {
						functionName(xmlhttp.responseText);
					}
				}
			};
						//创建请求
			xmlhttp.open(methodtype, url, con);
			//发送请求
			xmlhttp.send();
		}
	function yearchoose(){

			var s=document.getElementById("station").value; 
 			var y=document.getElementById("year"); 
			var m=document.getElementById("month"); 
			y.options.length=0; 
			m.options.length=0;
			y.options.add(new Option("=请选择年份=", "-1",true));
			m.options.add(new Option("=请选择月份=", "-1",true));
		if(s!=-1){
		ajaxrequest("addressSerlvet?method=year&station="+s,"POST",true,yearResponse);}
						/* 	window.location.reload(true); */
		}

		function yearResponse(responseContents){
			var jsonObj = new Function("return" + responseContents)();
			
			
			var y=document.getElementById("year"); 
			/* alert("receive!"); */
	
			for(var i = 0; i < jsonObj.yearList.length;i++){
/* 				document.getElementById('year').innerHTML += 
					"<option value='"+jsonObj.yearList.get(i)+"'>"
						+jsonObj.yearList.get(i)+
					"</option>" */
				y.options.add(new Option(jsonObj.yearList[i],jsonObj.yearList[i]));
			}
			
		}
		
		function monthchoose(){

			var s=document.getElementById("station").value; 
			var y=document.getElementById("year").value; 
			var m=document.getElementById("month"); 
			m.options.length=0;
			m.options.add(new Option("=请选择月份=", "-1",true));
			//y.options.add(new Option("黄冈", "0"));
		if(y!=-1){
		ajaxrequest("addressSerlvet?method=month&station="+s+"&year="+y,"POST",true,monthResponse);}
						/* 	window.location.reload(true); */
		}

		function monthResponse(responseContents){
			var jsonObj = new Function("return" + responseContents)();
			
			
			var m=document.getElementById("month"); 
			/* alert("receive!"); */
	
			for(var i = 0; i < jsonObj.monthList.length;i++){
/* 				document.getElementById('year').innerHTML += 
					"<option value='"+jsonObj.yearList.get(i)+"'>"
						+jsonObj.yearList.get(i)+
					"</option>" */
				m.options.add(new Option(jsonObj.monthList[i],jsonObj.monthList[i]));
			}
			
		}
		
		function validate(){
			if(document.myform.station.value=="-1")
			{
				alert("请选择站点！");
				document.myform.station.focus();
				return false;
			}
			if(document.myform.year.value=="-1")
			{
				alert("请选择年份！");
				document.myform.year.focus();
				return false;
			}
			if(document.myform.month.value=="-1")
			{
				alert("请选择月份！");
				document.myform.month.focus();
				return false;
			}
			if(document.myform.v1.value=="-1")
			{
				alert("请选择v1！");
				document.myform.v1.focus();
				return false;
			}			
			if(document.myform.v2.value=="-1")
			{
				alert("请选择v2！");
				document.myform.v2.focus();
				return false;
			}	
			if(document.myform.v3.value=="-1")
			{
				alert("请选择v3！");
				document.myform.v3.focus();
				return false;
			}	
			if(document.myform.v4.value=="-1")
			{
				alert("请选择v4！");
				document.myform.v4.focus();
				return false;
			}	
			if(document.myform.v5.value=="-1")
			{
				alert("请选择v5！");
				document.myform.v5.focus();
				return false;
			}	
			if(document.myform.v6.value=="-1")
			{
				alert("请选择v6！");
				document.myform.v6.focus();
				return false;
			}	
			if(document.myform.v7.value=="-1")
			{
				alert("请选择v7！");
				document.myform.v7.focus();
				return false;
			}	
			if(document.myform.v8.value=="-1")
			{
				alert("请选择v8！");
				document.myform.v8.focus();
				return false;
			}	
			if(document.myform.v9.value=="-1")
			{
				alert("请选择v9！");
				document.myform.v9.focus();
				return false;
			}	
			if(document.myform.v10.value=="-1")
			{
				alert("请选择v10！");
				document.myform.v10.focus();
				return false;
			}	
			if(document.myform.v11.value=="-1")
			{
				alert("请选择v11！");
				document.myform.v11.focus();
				return false;
			}	
			if(document.myform.v12.value=="-1")
			{
				alert("请选择v12！");
				document.myform.v12.focus();
				return false;
			}	
			if(document.myform.v13.value=="-1")
			{
				alert("请选择v13！");
				document.myform.v13.focus();
				return false;
			}	
			if(document.myform.v14.value=="-1")
			{
				alert("请选择v14！");
				document.myform.v14.focus();
				return false;
			}	
			if(document.myform.v15.value=="-1")
			{
				alert("请选择v15！");
				document.myform.v15.focus();
				return false;
			}	
			if(document.myform.v16.value=="-1")
			{
				alert("请选择v16！");
				document.myform.v16.focus();
				return false;
			}	
			if(document.myform.v17.value=="-1")
			{
				alert("请选择v17！");
				document.myform.v17.focus();
				return false;
			}	
			if(document.myform.v18.value=="-1")
			{
				alert("请选择v18！");
				document.myform.v18.focus();
				return false;
			}	
			if(document.myform.v19.value=="-1")
			{
				alert("请选择v19！");
				document.myform.v20.focus();
				return false;
			}	
			if(document.myform.v21.value=="-1")
			{
				alert("请选择v21！");
				document.myform.v21.focus();
				return false;
			}	
			if(document.myform.v22.value=="-1")
			{
				alert("请选择v22！");
				document.myform.v22.focus();
				return false;
			}	
			if(document.myform.v23.value=="-1")
			{
				alert("请选择v23！");
				document.myform.v23.focus();
				return false;
			}	
			if(document.myform.v24.value=="-1")
			{
				alert("请选择v24！");
				document.myform.v24.focus();
				return false;
			}	
			if(document.myform.v25.value=="-1")
			{
				alert("请选择v25！");
				document.myform.v25.focus();
				return false;
			}	
			if(document.myform.v26.value=="-1")
			{
				alert("请选择v26！");
				document.myform.v26.focus();
				return false;
			}	
			if(document.myform.v27.value=="-1")
			{
				alert("请选择v27！");
				document.myform.v27.focus();
				return false;
			}		
			if(document.myform.v28.value=="-1")
			{
				alert("请选择v28！");
				document.myform.v28.focus();
				return false;
			}								
			if(document.myform.spi.value=="-1")
			{
				alert("请选择v28！");
				document.myform.v28.focus();
				return false;
			}		
			return true;
		}
	</script>


</center>
	</body>
</html>

