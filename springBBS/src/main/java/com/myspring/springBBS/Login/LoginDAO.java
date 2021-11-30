package com.myspring.springBBS.Login;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.myspring.springBBS.Account.AccountDTO;

@Repository
@Component
public class LoginDAO {

@Autowired
SqlSessionTemplate temp;
	
	 public boolean loginCheck(String id, String pw) {
		 AccountDTO dto = new AccountDTO();
		 dto.setS_id(id);
		 dto.setS_pw(pw);
		 int ck = (Integer)temp.selectOne("login.check", dto);
		 return ck>0?true:false;
	 }
 	 
 	 public LoginDTO login(String id, String pw){
 		  AccountDTO dto = new AccountDTO();
		  dto.setS_id(id);
		  dto.setS_pw(pw);
		  LoginDTO ldto=temp.selectOne("login.selectone",dto);
		  //temp.insert("login.insert", ldto);		 
 		 return ldto;
	 }
 	 
 	 public void logout(String id) {
 		 temp.delete("login.logout", id);
 	 }
}//class EDN
