<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>
  </head>
  
  <body>
  <!--  
  表单中enctype="multipart/form-data"的意思，是设置表单的MIME编码。
  默认情况，这个编码格式是application/x-www-form-urlencoded，不能用于文件上传；
  只有使用了multipart/form-data，才能完整的传递文件数据，进行下面的操作.
  -->
    <form action="${pageContext.request.contextPath }/servlet/FileUpLoadServlet" enctype="multipart/form-data" method="post">
   
    上传文件:<input type="file" name="file1"><br>
    <input type="submit" value="确认">
    </form>
  </body>
</html>
