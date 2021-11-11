<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		
		
		
	</form>
	<button id ="btn-save" class="btn btn-primary">회원가입완료 </button>
	<!-- form 안에있으면 submit 이 된다. 밖으로 뺌  -->
</div>
<script src="/blog3/js/user.js"> </script>
<%@ include file="../layout/footer.jsp"%>




