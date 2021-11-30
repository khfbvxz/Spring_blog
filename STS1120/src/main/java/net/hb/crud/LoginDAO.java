package net.hb.crud;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LoginDAO {
    
	@Autowired
	SqlSessionTemplate  temp;

}//class END
