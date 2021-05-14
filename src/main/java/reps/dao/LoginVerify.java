package reps.dao;

//import reps.dbs.DatabaseManager;
//import reps.dao.UserRegister;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import reps.dbs.ConnectionManager;
import reps.exceptions.InvalidUserNameException;
import static reps.dbs.DatabaseManager.getQuery;

public class LoginVerify {
	String username = "";
	String password = "";
	Connection conn = ConnectionManager.getConnection();
	public LoginVerify(String username) throws Exception{

		this.username = username;
	}
	public String getPasswordByUserName() throws Exception {
    	String parameter = "passWord";
    	PreparedStatement pst = conn.prepareStatement(
    			"SELECT passWord from UserRegister where username = ?"
    			);
    	pst.setString(1, username);
    	List<Map<String,Object>> rst =getQuery(pst);
    	if(rst.isEmpty()) {
    		throw new InvalidUserNameException("No such username present");
    	}
    
    	this.password =  (String) rst.get(0).get(parameter);
    	return this.password;
    }

}
