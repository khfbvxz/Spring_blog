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
 <title>boardEdit.jsp</title>
</head>

<body>
 <font color=blue>[boardEdit.jsp=수정화면폼]</font> <br>

  <form  method="post" enctype="multipart/form-data" action="boardEditSave.sp" >
      idx: <input type="text" name="hobby_idx" value="${dto.hobby_idx}"> <br>
      이름수정: <input type="text" name="name" value="${dto.name}"> <br>
      제목수정: <input type="text" name="title" value="${dto.title}"> <br>
      내용수정: <textarea rows="3" cols="20"  name="content">${dto.content}</textarea> <br>
      성별수정:
      <input type="radio" name="gender" value="man">남자 
      <input type="radio" name="gender" value="woman" checked>여자<span style='color:red'>&nbsp;구)${dto.gender}</span> <br>
      취미수정:
      <input type="checkbox" name="hobby" value="game">게임
      <input type="checkbox" name="hobby" value="study">공부
      <input type="checkbox" name="hobby" value="ski" checked>스키
      <input type="checkbox" name="hobby" value="movie">영화 <span style='color:red'>&nbsp;구)${dto.hobby}</span><br>
      파일수정: <input type="file" name="upload_f"> <span style='color:red'>&nbsp;구)${dto.img_file_name}</span><p>
      
      <input type="submit" value="스프링hobby테이블수정">
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
