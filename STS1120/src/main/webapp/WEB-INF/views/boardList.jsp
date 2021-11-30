<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[boardList.jsp]</title>
   <style type="text/css">
    *{font-size: 16pt; }
    a{text-decoration:none;font-size: 16pt;  color:blue ;}
    a:hover{font-size: 18pt;    text-decoration:underline; color:green ;  }
  </style>

</head>
<body>
 <font color=blue>[boardList.jsp]</font> <br>
    
  <table width=900 border=1   cellspacing="0" >
  	 <tr align="right">
  	 	<td colspan="6">
  	 	 <a href="boardWrite.sp">[board글쓰기]</a>
   		 <a href="boardList.sp">[board전체출력]</a>
   		 <a href="index.jsp">[index]</a>   
  	 	 레코드갯수: ${Gtotal}/${GGtotal} &nbsp;  
  	 	</td>
  	 </tr>
  
     <tr bgcolor=yellow height=50>
      <td>번호</td> <td>이름</td> 
      <td>제목</td> <td>성별</td> <td>취미</td>  <td>이미지</td> 
     </tr>  
     
     <c:forEach var="dto" items="${LG}">
       <tr>
        <td> ${dto.rn} </td> 
      	<td> ${dto.name} </td> 
        <td> <a href="boardDetail.sp?idx=${dto.hobby_idx}">${dto.title}</a><c:if test="${dto.rcnt>0}"><font style="color:red;font-size:14pt">[${dto.rcnt}]</font></c:if> </td> 
        <td> ${dto.gender}</td> 
        <td> ${dto.hobby}</td>  
        <td align="center"> 
          <img src="${pageContext.request.contextPath}/resources/upload/${dto.img_file_name}" width=130 height=30 border=0>
        </td>
        </tr> 
     </c:forEach>
     
    <tr align="center">
  	 	<td colspan="6">
  	 	  <!-- 이전  1 11 21 31 -->
  	 	  <c:if test="${startPage>10}">
  	 	     <a href="boardList.sp?pageNum=${startPage-10}">[이전]</a>
  	 	   </c:if>
  	 	
  	 	   <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
  	 	        <a href="boardList.sp?pageNum=${i}${returnpage}">[${i}]</a>
  	 	   </c:forEach>
  	 	   
  	 	   <!-- 다음  10 20 30 40 -->
  	 	   <c:if test="${endPage<pageCount}">
  	 	     <a href="boardList.sp?pageNum=${startPage+10}">[다음]</a>
  	 	   </c:if>
  	 	</td>
  	</tr>
  	
	<tr align="center">
	   <td colspan="6">
	    <form name="myform" action="boardList.sp">
 	    검색:
 	      <select name="keyfield">
 	      	<option value="">---검색필드---</option>
 	      	<option value="name">이름필드</option>
 	      	<option value="title">제목필드</option>
 	      	<option value="email">메일필드</option>
 	      </select>
 	      <input type="text" name="keyword">
 	      <input type="submit" value="hobby검색">
 	  </form>
	   </td>
	</tr>
</table>

</body>
</html>




