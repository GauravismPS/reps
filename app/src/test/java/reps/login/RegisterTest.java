package reps.login;

import static org.junit.Assert.*;
import org.junit.Test;


public class RegisterTest {
    
    @Test
    public void RegTest() throws Exception {

        String uname = "Zeref";
        String email = "Zeref@drag.com";
        String pass1 = "123456";
        String pass2 = "123456";
        int status = Register.reg(uname, email, pass1, pass2);
        assertEquals(status, 0);
    }
}
