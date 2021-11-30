function loginCheck() {
	var data = {id : $("#id").val() , pw : $("#pw").val()};
	$.ajax({
		type: "POST",
		url: "loginCheck.do",
		data : data,
		success : function(data){
			if(data == '' || data == null){
				alert("kimlee 로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요");		
			}		
			else{
				alert($("#id").val() ,"님 login success!");	
				location.reload(true);
			}
		}
	})
}

function loginChange(){
	var data = {req : "req"};
	$.ajax({
		type: "POST",
		url: "loginChange.do",
		data : data,
		success : function(data){
			if(data!='null'){
				login_s = document.getElementById("user_s");
				login_s.innerHTML = "<strong style='color:white'>접속자: "+data+" 님</strong><input type='button' class='btn btn-danger' style='padding-left:6px; padding-right:6px; margin-left:5px;' onclick=\"location.href='logout.do'\" value='logout'>";
			}
		}
	})
	
	
	/*$.ajax({
		type: "POST",
		url: "popup_check.do",
		success : function(data){
			if(data=='popup'){
				var popUrl = "popup.do";
				var popOption = "width=400%, height=400%,  left=300, top=300, resizable=no, scrollbars=no, status=no;";
				window.open(popUrl,"",popOption);
			}
		}
	})*/
}

window.onload=loginChange;

