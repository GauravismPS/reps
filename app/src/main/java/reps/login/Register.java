package reps.login;

import java.sql.*;
import java.util.*;

public class Register {

    public static int reg(String uname, String email, String pass1, String pass2) throws Exception {
        PreparedStatement pst = null;
        if (pass1 != pass2)
            return -1;
        if (!EmailValidator.validate(email))
            return 1;
        if (uname.isEmpty())
            return 2;
        pass1 = new String(Base64.getEncoder().encode(pass1.getBytes()));
        Connection conn = ConnectionManager.getConnection();
        pst = conn.prepareStatement(
                "INSERT INTO UserRegister (username, emailid, password) values (?, ?, ?)");
        pst.setString(1, uname);
        pst.setString(2, email);
        pst.setString(3, pass1);
        try {
            pst.executeUpdate();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                return 3;
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                return 4;
            }
        }
        return 0;
    }
}
