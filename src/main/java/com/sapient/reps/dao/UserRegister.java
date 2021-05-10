/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.reps.dao;


import static com.sapient.reps.dbs.DatabaseManager.getQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import com.sapient.reps.dbs.ConnectionManager;
import com.sapient.reps.dbs.DatabaseManager;
import com.sapient.reps.exceptions.DuplicateEmailException;
import com.sapient.reps.exceptions.DuplicateUsernameException;

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
    public UserRegister() throws Exception {

    }

    public void setUserName(String uname) throws Exception {
        userName = uname;
    }

    public void setEmailId(String email) throws Exception {
        emailId = email;
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

    public List<Map<String, Object>> queryAll() throws Exception {
        PreparedStatement pst = conn.prepareStatement(
                "SELECT userid, username from userregister"
        );
        return getQuery(pst);
    }

}