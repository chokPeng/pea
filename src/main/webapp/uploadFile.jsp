<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
<form action="./uploadFile" method="post" enctype="multipart/form-data">
    <input type="file" name="img" accept="image/jpg,image/png">
    用户名<input name="userName">
    用户ID<input name="userId">
    <input type="submit" value="提交" >
</form>
</body>
</html>