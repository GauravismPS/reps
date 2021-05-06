package reps.login;

import java.util.regex.*;

public class EmailValidator {

    public static boolean validate(String email) {


        String regex = "^(.+)@(.+)$"; 
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
    
}
