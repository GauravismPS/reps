package reps.login;

import reps.dao.LoginVerify;

public class Login {

	public static boolean check(String uname, String pass) throws Exception {
		LoginVerify currUser = new LoginVerify(uname);
		if (pass.equals(currUser.getPasswordByUserName())) {
			return true;
		}
		
		return false;

	}
}
