<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
  <style type="text/css">
   *{font-size: 16pt;  }
   a{font-size: 16pt;   text-decoration:none; color:blue ;}
   a:hover{font-size: 20pt; font-weight: bold;   text-decoration:underline; color:green ;  }
  </style>
 <title>boardWrite.jsp</title>
</head>

<body>
 <font color=blue>[boardWrite.jsp]</font> <br>

  <form  method="post" enctype="multipart/form-data" action="boardInsert.sp" >
      이름: <input type="text" name="name" value="aaa"> <br>
      제목: <input type="text" name="title" value="bbb"> <br>
      내용: <textarea rows="3" cols="20"  name="content">ccc</textarea> <br>
      성별:
      <input type="radio" name="gender" value="man">남자 
      <input type="radio" name="gender" value="woman" checked>여자 <br>
      취미:
      <input type="checkbox" name="hobby" value="game">게임
      <input type="checkbox" name="hobby" value="study">공부
      <input type="checkbox" name="hobby" value="ski" checked>스키
      <input type="checkbox" name="hobby" value="movie">영화 <br>
      파일: <input type="file" name="upload_f"><p>
      
      <input type="submit" value="스프링hobby테이블저장">
      <input type="reset" value="입력취소">
  </form>  
  
 <p>
 <h2>  
   <a href="boardWrite.sp">[board글쓰기]</a>
   <a href="boardList.sp">[board전체출력]</a>
   <a href="kakao.sp">[home]</a>
   <a href="index.jsp">[index]</a>
 </h2>
</body>
</html>
