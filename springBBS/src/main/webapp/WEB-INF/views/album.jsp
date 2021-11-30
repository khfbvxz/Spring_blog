<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>[album.jsp]</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/half-slider.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="./top.jsp"></jsp:include>

    <header>
     <c:choose>
    	<c:when test="${empty list}">
    		<div class="post-preview" style="text-align: center">            
              <h2 class="post-title">
              	  
              </h2>
	        </div>
    	</c:when>
   		<c:otherwise>
	      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
	        <ol class="carousel-indicators">
	          <c:forEach var="i" begin="0" end="${size-1}" step="1">
	            <li data-target="#carouselExampleIndicators" data-slide-to="${i}" ></li>
	          </c:forEach>	   
	        </ol>
	        
	        <div class="carousel-inner" role="listbox">
              <!-- Slide One - Set the background image for this slide in the line below -->
	          <c:forEach var="list" items="${list}" varStatus="status">
	          <c:choose>  
	          	<c:when test="${status.first == true}">
	          	  <div class="carousel-item active" style="background-image: url('${pageContext.request.contextPath}/resources/upload/${list.b_file}'); width=1000 height=300">
		            <div class="carousel-caption d-none d-md-block">
		              <h3><a href="download.do?fidx=${list.b_file}&idx=${list.no}">${list.b_file}</a><br>
		              <h3><p>작성자:${list.id}</p><a href="detail.do?cnt=t&idx=${list.no}">상세문서이동</a></h3>
		            </div>
		          </div>
	          	</c:when>
	          	<c:otherwise>
	          	  <div class="carousel-item" style="background-image: url('${pageContext.request.contextPath}/resources/upload/${list.b_file}'); width=1000 height=300">   
		            <div class="carousel-caption">
		               <a href="download.do?fidx=${list.b_file}&idx=${list.no}"> </a>
		              <h3><p>작성자:${list.id}</p><a href="detail.do?cnt=t&idx=${list.no}">${list.content}</a> </h3>
		            </div>
		          </div>
	          	</c:otherwise>
	          </c:choose>   
	          </c:forEach>
	        
	          
	        </div>
	        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	          <span class="sr-only">Previous</span>
	        </a>
	        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
	          <span class="carousel-control-next-icon" aria-hidden="true"></span>
	          <span class="sr-only">Next</span>
	        </a>
	      </div>
        </c:otherwise>
      </c:choose>
    </header>

	<jsp:include page="./footer.jsp"></jsp:include>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="${pageContext.request.contextPath}/resources/js/clean-blog.min.js"></script>
</body>
</html>



