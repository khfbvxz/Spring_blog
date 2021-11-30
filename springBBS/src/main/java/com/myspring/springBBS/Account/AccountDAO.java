package com.myspring.springBBS.Account;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.myspring.springBBS.Login.LoginDTO;

@Repository
@Component
public class AccountDAO {
	@Autowired
	SqlSessionTemplate temp;
	
	public String dbCheck(String id) {
		int ck=(Integer)temp.selectOne("acct.idCheck",id);
		return ck>0 ? "t":"f"; // t 중복 아이디 / f 사용 가능 
	}
	
	
	public void dbInsert(AccountDTO dto) {
		temp.insert("acct.insert", dto);
		System.out.println("다오AccountDAO.java  acct테이블 신규등록 success");
		temp.insert("login.insert", dto);
		System.out.println("다오AccountDAO.java login테이블 신규등록 success");
	}
	
}//class EDN
