<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>[signUp.jsp]</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/signUp_check.js"></script> 
</head>
<body>
	<jsp:include page="./top.jsp"></jsp:include>

    <div class="container">
	  <div class="row">
	    <div class="col-lg-8 col-md-10 mx-auto">
	    <h3>회원가입</h3>
	    <form name="acct"  method="post" action="acct_insert.do">
		   <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>아이디</label>
	           <input type="text" class="form-control" placeholder="아이디" name="s_id" id="s_id" autofocus required>
	           <p class="help-block text-danger" id="maskId"></p>
	         </div><input type="button" class="btn btn-info" onclick="javascript:idCheck();" value="중복체크">
	       </div>      
		   <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>패스워드</label>
	           <input type="text" class="form-control" value="1234"  name="s_pw" id="s_pw1" required>
	           <p class="help-block text-danger" id="maskPw1"></p>
	         </div>
	       </div>
	       <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>패스워드 확인</label>
	           <input type="text" class="form-control" value="1234"  name="s_pw2" id="s_pw2" onkeyup="pwCheck();" required>
	           <p class="help-block text-danger" id="maskPw2"></p>
	         </div>
	       </div>
	       <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>이름</label>
	           <input type="text" class="form-control" value="blue" name="s_name" id="s_name" required>
	           <p class="help-block text-danger" id="maskName"></p>
	         </div>
	       </div>
	       <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label >성별</label>
	           <input type="radio" class="form-control" name="s_gender" value="male" checked>남자
	 	  	   <input type="radio" class="form-control" name="s_gender" value="female">여자	         
	           <p class="help-block text-danger" id="maskGender"></p>
	         </div>
	       </div>
	       <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>연락처</label>
	           <input type="tel" value="010-1234-9876" id="s_phone" name="s_phone" onblur="phoneCheck();" required>
	           <p class="help-block text-danger" id="maskPhone"></p>
	         </div>
	       </div>
	       <div class="control-group">
	         <div class="form-group floating-label-form-group controls">
	           <label>이메일</label>
	           <input type="email" class="form-control" value="bc@city.com" name="s_email" id="s_email" onkeyup="emailCheck();" required>
	           <p class="help-block text-danger" id="maskEmail"></p>
	         </div>
	       </div>
		   <div class="form-group">
	         <button type=button class="btn btn-success" id="sendMessageButton" onclick="check()">가입</button>
	         <input type="reset" class="btn btn-primary" value="초기화">
	         <input type="button" class="btn btn-primary" onclick="history.back();" value="돌아가기">
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



