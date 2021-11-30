<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>[boardList.jsp]</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/paging.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="./top.jsp"></jsp:include>

    <div class="container">
	  <div class="row">
	    <div class="col-lg-8 col-md-10 mx-auto">
    	<c:choose>
	    	<c:when test="${empty list}">
	    		<div class="post-preview">            
	              <h2 class="post-title">
	              	[현재 작성된 글이 없습니다.]
	              </h2>
		        </div>
	    	</c:when>
    		<c:otherwise>   
    		  <c:forEach var="dto" items="${list}">
	          <div class="post-preview">
	            <a href="detail.do?cnt=t&idx=${dto.no}">
	              <h2 class="post-title">
	                ${dto.title}
	              </h2>
	              <h3 class="post-subtitle">
	                Posted by <strong>"${dto.id}"</strong>
	              </h3>
	            </a>
	            <p class="post-meta">on ${dto.b_date}<br><strong>views ${dto.count}</strong></p>
	            
	          </div>
	          <hr>
	          </c:forEach> 
	    	</c:otherwise>
    	</c:choose>
    		<!-- Pager -->
    		<div style="width:100%;  text-align: center;">
    		 <div class="pagination">
    		  <c:if test="${pager.startPage>10}">
		   	    <a href="list.do?curPage=${pager.startPage-10}${pager.returnpage}">&laquo;</a>
		   	  </c:if>
		   	     	  
		   	  <c:forEach var="i" begin="${pager.startPage}" end="${pager.endPage}">
		   	   <c:choose>
		   	   	<c:when test="${i==pager.curPage}">
		   	   		<a href="#" class="active">${i}</a>
		   	   	</c:when>
		   	   	<c:otherwise>
		   	   	  <a href="list.do?curPage=${i}${pager.returnpage}">${i}</a>
		   	   	</c:otherwise>
		   	   </c:choose>
		   	 </c:forEach>	
			  <c:if test="${pager.endPage<pager.pagecount}">
		   	    <a href="list.do?curPage=${pager.startPage+10}${pager.returnpage}">&raquo;</a>
		   	  </c:if>
			 </div><hr>
			 
			 <form name="search" action="list.do">
		  	 	<select name="keyfield">
		  	 	   <option value="all" <c:if test="${pager.skey=='all'}">selected</c:if> selected>전체검색</option>
		  	 	   <option value="name" <c:if test="${pager.skey=='name'}">selected</c:if> >이름조회</option>
		  	 	   <option value="title" <c:if test="${pager.skey=='title'}">selected</c:if> > 제목검색 </option>
		  	 	   <option value="content" <c:if test="${pager.skey=='content'}">selected</c:if> > 내용검색 </option>
		  	 	</select>
		  	 	<input type="text" name="keyword" size=10  value="${pager.sval}">
		  	 	<input type="submit" class="btn btn-info" value="검색">
		  	 	<input type="button" class="btn btn-info" onclick="location.href='list.do'" value="최신목록"> 
		  	 	<c:if test="${session == 'ok'}">
		  	 		<input type="button" class="btn btn-success" onclick="location.href='write.do'" value="글쓰기">
		  	 	</c:if>		  	 	
		  	 </form> 		  	 		
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



