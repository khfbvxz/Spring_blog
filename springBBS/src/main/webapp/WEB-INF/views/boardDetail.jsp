<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>[boardDetail.jsp]</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="./top.jsp"></jsp:include>

    <div class="container">
	  <div class="row">
	    <div class="col-lg-8 col-md-10 mx-auto">
	    	<table>
			     <tr style="text-align: left">
			      <td>&nbsp;</td>
			      <td width="190"><h2 class="post-title">제목&nbsp;</h2></td>
			      <td width="500"><h2 class="post-title">${dto.title}</h2></td>
			      <td>&nbsp;</td>
			     </tr>
			     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
			     <tr style="text-align: left">
			      <td>&nbsp;</td>
			      <td width="190"><h2 class="post-title">작성자&nbsp;</h2></td>
			      <td width="500"><h2 class="post-title" id="board_writer">${dto.id}</h2></td>
			      <td>&nbsp;</td>
			     </tr>
			     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
			     <tr style="text-align: left">
			      <td>&nbsp;</td>
			      <td width="190"><h2 class="post-title">날짜&nbsp;</h2></td>
			      <td width="500"><h2 class="post-title"> ${dto.b_date} </h2></td>
			      <td>&nbsp;</td>
			     </tr>
			     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
			     <tr style="text-align: left">
			      <td>&nbsp;</td>
			      <td width="190"><h2 class="post-title">조회수&nbsp;</h2></td>
			      <td width="500"><h2 class="post-title">${dto.count}</h2></td>
			      <td>&nbsp;</td>
			     </tr>
			     
			     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
			     <tr style="text-align: left">
			      <td>&nbsp;</td>
			      <td width="190"><h2 class="post-title">첨부파일&nbsp;</h2></td>
			      <td width="500"><h2 class="post-title"><a href="download.do?fidx=${dto.b_file}&idx=${dto.no}">${dto.b_file}</a></h2></td>
			      <td>&nbsp;</td>
			     </tr>
					
				 <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>     	     
			     <tr style="text-align: left" height=120>
			      <td>&nbsp;</td>
			      <td width="190"><h2 class="post-title">내용&nbsp;</h2></td>
			      <td width="500"><h2 class="post-title">${dto.content}</h2></td>
			      <td>&nbsp;</td>
			     </tr>
			     
			     <tr height="2" bgcolor="#82B5DF"><td colspan="4"></td></tr>
			     <tr height="2" bgcolor="#82B5DF"><td colspan="4"></td></tr>
		    </table>
	       
	       
	        <p>
	        <c:import url="/reply_list.do" />
	        <p>
	        <div class="clearfix">
    		   <c:if test="${session == 'ok'}">
	            <a class="btn btn-success" href="edit_req.do?idx=${dto.no}">수정</a>
	            <a class="btn btn-danger" href="delete.do?idx=${dto.no}">삭제</a>    		   
    		   </c:if>
	         
	        </div>
    	</div>
      </div>
    </div>

	<jsp:include page="./footer.jsp"></jsp:include>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="${pageContext.request.contextPath}/resources/js/clean-blog.min.js"></script>
</body>
</html>



