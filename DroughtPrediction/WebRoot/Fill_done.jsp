<%@page import="org.apache.log4j.chainsaw.Main"%>
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
    
    <title>Fill Complete</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<%
	int id= Integer.parseInt(request.getParameter("station"));
	int year=Integer.parseInt(request.getParameter("year"));
	int month=Integer.parseInt(request.getParameter("month"));
	int spi=Integer.parseInt(request.getParameter("spi"));
	String sql_select="select SPI"+spi+" from rain where 站点="+id+" and 年="+year+" and 月="+month;
	ResultSet rs=db.select(sql_select);
	String tmp0="SPI"+spi;
	rs.next();
	tmp0=rs.getString(tmp0);
	double spi_value= Double.parseDouble(tmp0);
	
	int x[]=new int[28];
	for(int i=1;i<=28;i++)
	{
		String tmp="v"+String.valueOf(i);
		x[i-1]=Integer.parseInt(request.getParameter(tmp));
	}
	int c1=0,c2=0;
	if(spi_value>-0.5)
		c1=1;
	if(spi_value>-1 && spi_value<=-0.5)
		c1=2;
	if(spi_value>-1.5 && spi_value<=-1)
		c1=3;
	if(spi_value>-2 && spi_value<=-1.5)
		c1=4;
	if(spi_value<=-2)
		c1=5;
	
	if(spi_value<0.5)
		c2=1;
	if(spi_value>=0.5 && spi_value<1)
		c2=2;
	if(spi_value>=1 && spi_value<1.5)
		c2=3;
	if(spi_value>=1.5 && spi_value<2)
		c2=4;
	if(spi_value>=2)
		c2=5;
	String sql_insert="insert infofill values (null,"+id+","+year+","+month+","+spi+","+c1+","+c2;
	for(int i=1;i<=28;i++)
	{
		sql_insert=sql_insert+","+x[i-1];
	}
	int []A=new int[30];
	A[0]=c1;A[2]=c2;
	A[1]=x[0];A[3]=x[1];
	for(int i=3;i<=28;i++){
		A[i+1]=x[i-1];
	}
 	db.insert_to_excel(A);
	db.read_insert(sql_insert);
%>
  <body>
  	<br><br><br>
  	<center>
	<font size="2"> <strong> 成功插入数据！<a href="InfoFill.jsp">返回继续</a><br>  </center>
  </body>
</html>
