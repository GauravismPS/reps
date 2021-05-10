package reps.dao;


import java.util.List;
import java.util.Map;
import java.sql.Connection;
import reps.dbs.DatabaseManager;
import java.sql.PreparedStatement;
import reps.dbs.ConnectionManager;
import reps.exceptions.DuplicateEmailException;
import reps.exceptions.DuplicateUsernameException;

import static reps.dbs.DatabaseManager.getQuery;

public class UserRegister {
    Integer userId;
    String userName;
    String emailId;
    String passWord;
    Connection conn = ConnectionManager.getConnection();

    public UserRegister(String uname, String email, String pass) throws Exception {
        userName = uname;
        emailId = email;
        passWord = pass;
        if(queryByUserName().size() > 0) {
            throw new DuplicateUsernameException("Username Already Exists in database");
        }
        if(queryByEmailId().size() > 0) {
            throw new DuplicateEmailException("Email already exists in database");
        }

    }
    public int Register() throws Exception{

        PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO UserRegister (username, emailid, password) values (?, ?, ?)");
        pst.setString(1, userName);
        pst.setString(2, emailId);
        pst.setString(3, passWord);
        return DatabaseManager.modify(pst);
    }
    public List<Map<String, Object>> queryByUserName() throws Exception{
        PreparedStatement pst = conn.prepareStatement(
                "SELECT userid, EmailId from UserRegister where username=?"
        );
        pst.setString(1, userName);
        return getQuery(pst);
    }
    public List<Map<String, Object>> queryByEmailId() throws Exception{
        PreparedStatement pst = conn.prepareStatement(
                "SELECT userid, username from UserRegister where emailid=?"
        );
        pst.setString(1, emailId);
        return getQuery(pst);
    }

}