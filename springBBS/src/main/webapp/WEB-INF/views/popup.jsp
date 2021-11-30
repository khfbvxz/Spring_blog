<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[popup.jsp]</title>
<script type="text/javascript">
	function closePop() {
		var ck = document.getElementById("check");		
		opener.popup(ck);
		self.close();
	}
</script>
</head>
<body>
<table width="" border="0" cellpadding="0" cellspacing="0">
  <thead>
  	<tr><th>[WELCOME TO MY SPRING BBS!!]</th></tr>
  </thead>
  <tbody>
  <tr>
    <td><img src="${pageContext.request.contextPath}/resources/img/about-bg.jpg" width="90%" heigth="90%" border="0"></td>
  </tr>
  <tr>
    <td height="30" align="right" >
    <table border="0" cellpadding="0" cellspacing="2">
        <tbody><tr>
         <td><input type="checkbox" name="check" id="check"></td>
         <td style="font-size:14px;">1일동안 이 창을 열지 않음</td>
            <td style="font-size:16px;"><a href="javascript:closePop();" onfocus="this.blur()">[닫기]</a></td>
        </tr>       
    </tbody></table>
    </td>
  </tr>
</tbody></table>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
</body>
</html>


