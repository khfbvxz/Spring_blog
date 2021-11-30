<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>[boardWrite.jsp]</title>
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
	    <h3>글쓰기</h3>
	    <form name="write" id="contactForm" enctype="multipart/form-data" method="post" action="insert.do">
		   <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>제목</label>
	           <input type="text" class="form-control" placeholder="제목" name="title" id="title" required>
	           <p class="help-block text-danger"></p>
	         </div>
	       </div>      
	       <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>첨부파일</label>
	           <input type="file" class="form-control" name="upload_f">
	           <p class="help-block text-danger"></p>
	         </div>
	       </div>
		   <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>내용</label>
	           <textarea rows="5" class="form-control" placeholder="내용" name="content" id="content" required></textarea>
	           <p class="help-block text-danger"></p>
	         </div>
	       </div>
		   <div class="form-group">
	         <button type="submit" class="btn btn-success" id="sendMessageButton">등록</button>
	         <input type="reset" class="btn btn-primary" value="초기화">
	         <input type="button" class="btn btn-primary" onclick="location.href='list.do'" value="돌아가기">
	       </div>
	    </form>
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



