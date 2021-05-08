package reps.login;

import java.sql.*;
import java.util.*;
import reps.exceptions.PasswordMisMatchException;
import reps.exceptions.*;
public class Register {

    public static int reg(String uname, String email, String pass1, String pass2) throws Exception {
        PreparedStatement pst = null;
        if (pass1 != pass2) 
            throw new PasswordMisMatchException("Passwords doesn't match");
        if (!EmailValidator.validate(email))
            throw new InvalidEmailException(email + " is not a valid Email");
        if (uname.isEmpty())
            throw new EmptyUsernameException("Username is Empty");
        pass1 = new String(Base64.getEncoder().encode(pass1.getBytes()));
        Connection conn = ConnectionManager.getConnection();
        pst = conn.prepareStatement(
                "INSERT INTO UserRegister (username, emailid, password) values (?, ?, ?)");
        pst.setString(1, uname);
        pst.setString(2, email);
        pst.setString(3, pass1);
        return DatabaseManager.modify(pst);
    }

}
