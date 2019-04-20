<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<form action="./updateMovieInfo" method="post">
	电影Id<input name="movieId"><br>
	电影名<input name="movieName"><br>
	上映日期<input name="releaseDate"><br>
	主演<input name="actor"><br>
	评分<input name="score"><br>
	电影简介<input name="movieInfo"><br>
	<input type="submit" value="提交"> 
	</form>
</div>
</body>
</html>