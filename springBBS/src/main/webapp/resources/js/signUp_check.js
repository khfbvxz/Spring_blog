var flagId = false;
var flagPw = false;
var flagPhone = false;
var flagEmail = false;

function check(){
	
	var id = document.getElementById("s_id");
	var pw1 = document.getElementById("s_pw1");
	var pw2 = document.getElementById("s_pw2");
	var name = document.getElementById("s_name");
	var gender = document.getElementsByName("s_gender");
	var phone = document.getElementById("s_phone");
	var email = document.getElementById("s_email");
	
		
	if (id.value==null||id.value==""){
		label = document.getElementById("maskId");
		label.innerHTML = "<strong style='color:red'>ID를 입력해주세요.</strong>";
		id.focus();
		return;
	}
		
	if (pw1.value==null||pw1.value==""){
		label = document.getElementById("maskPw1");
		label.innerHTML = "<strong style='color:red'>패스워드를 입력해주세요.</strong>";
		pw1.focus();
		return;
	}
	
	if (pw2.value==null||pw2.value==""){
		label = document.getElementById("maskPw2");
		label.innerHTML = "<strong style='color:red'>패스워드를 재확인해주세요.</strong>";
		pw2.focus();
		return;
	}
	
	if (name.value==null||name.value==""){
		label = document.getElementById("maskName");
		label.innerHTML = "<strong style='color:red'>이름을 입력해주세요.</strong>";
		name.focus();
		return;
	}
	
	var gcheck = null;
	for(var i=0; i<gender.length; i++){
		if(gender[i].checked==true)
			gcheck = gender[i].value;
	}
	if(gcheck==null){
		label = document.getElementById("maskGender");
		label.innerHTML = "<strong style='color:red'>성별을 선택해주세요.</strong>";
		return;
	}
	
	if (phone.value==null||phone.value==""){
		label = document.getElementById("maskPhone");
		label.innerHTML = "<strong style='color:red'>연락처를 입력해주세요.</strong>";
		phone.focus();
		return;
	}
	
	if (email.value==null||email.value==""){
		label = document.getElementById("maskEmail");
		label.innerHTML = "<strong style='color:red'>이메일을 입력해주세요.</strong>";
		email.focus();
		return;
	}
	
	if(flagId==false){	//아이디중복체크 안됐을때
		label = document.getElementById("maskId");
		label.innerHTML = "<strong style='color:red'>ID 중복체크를 해주세요</strong>";
		id.focus();
		return;
	}
	
	if(flagPw==false){	//비번 확인 틀렸을때
		label = document.getElementById("maskPw2");
		label.innerHTML = "<strong style='color:red'>패스워드를 재확인해주세요.</strong>";
		pw2.focus();
		return;
	}
	
	if(flagPhone==false){
		label = document.getElementById("maskPhone");
		label.innerHTML = "<strong style='color:red'>연락처란을 다시 확인해주세요.</strong>";
		phone.focus();
		return;
	}
	
	if(flagPhone==false){
		label = document.getElementById("maskEmail");
		label.innerHTML = "<strong style='color:red'>이메일란을 다시 확인해주세요.</strong>";
		email.focus();
		return;
	}

	//위에 다 통과&체크 완료 후 submit
		if(flagId==true&&flagPw==true&&flagPhone==true&&flagEmail==true){
			//alert("11-29-월요일 가입성공했습니다");
			document.acct.submit();
		} else { return; }
}//end

function idCheck() {  
	var id = {s_id : $("#s_id").val()};
	$.ajax({
		type: "POST",
		url: "idCheck.do",
		data : id,
		success : function(data){
			if(data == 'f'){
				label = document.getElementById("maskId");
				label.innerHTML = "<strong style='color:green'>사용가능한 아이디입니다.</strong>";	
				flagId=true;
			}		
			else{
				label = document.getElementById("maskId");
				label.innerHTML = "<strong style='color:red'>이미 사용 중인 아이디입니다.</strong>";
				flagId=false;
			}
		}
	})
}

function pwCheck(){//비밀번호 확인 체크
	var pw1 = document.getElementById("s_pw1");
	var pw2 = document.getElementById("s_pw2");
	if(pw1.value == pw2.value) {
		label = document.getElementById("maskPw2");
		label.innerHTML = "<strong style='color:green'>비밀번호가 일치합니다.</strong>";
		flagPw = true;	//비번일치플래그 true로 해줌
	} else {
		label = document.getElementById("maskPw2");
		label.innerHTML = "<strong style='color:red'>비밀번호가 일치하지 않습니다.</strong>";
		flagPw = false;
	}
}

function phoneCheck(){
	var phone = document.getElementById("s_phone");
	var ph = phone.value.split('-');
	var ck1 = /\d{2,3}/;
	var ck2 = /\d{3,4}/;
	
	if(ph[0].match(ck1)==null || ph[1].match(ck2)==null ) {
		label = document.getElementById("maskPhone");
		label.innerHTML = "<strong style='color:red'>전화번호 형식을 맞춰주세요.</strong>";
		flagPhone = false;
		phone.focus();		
	} else {
		label = document.getElementById("maskPhone");
		label.innerHTML = "";
		flagPhone = true;
	}
}


function emailCheck(){
	var email = document.getElementById("s_email");
	var ck = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	if(email.value.match(ck)!=null) {
		label = document.getElementById("maskEmail");
		label.innerHTML = "";
		flagEmail = true;	
	} else {
		label = document.getElementById("maskEmail");
		label.innerHTML = "<strong style='color:red'>이메일 형식을 맞춰주세요.</strong>";
		flagEmail = false;
	}
}

