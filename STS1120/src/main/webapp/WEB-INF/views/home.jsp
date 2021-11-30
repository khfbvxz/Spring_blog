<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world~~~  고길동!!! 홍길동!!!  
</h1>

<h2>  
   현재날짜및 시간 : ${serverTime} <br>
   <a href="boardWrite.sp">[board글쓰기]</a>
   <a href="boardList.sp">[board전체출력]</a>
   <a href="index.jsp">[index]</a> 
 </h2>

</body>
</html>
