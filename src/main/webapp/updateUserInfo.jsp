<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
</head>
<body>
<div>
	<form action="updateUserInfo" method="post">
		用户Id<input name="userId"><br>
		昵称<input name="userName"><br>
		密码<input type="password" name="userPassword"><br>
		性别<input name="userSex"><br>
		信息<input name="userInfo"><br>
		地址<input name="userAdress"><br>
		<input type="submit" value="提交">
	</form>
</div>
</body>
</html>