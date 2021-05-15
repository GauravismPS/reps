package reps.Interfaces;
import java.util.List;
import java.util.Map;

import reps.dao.UserRegister;

public interface IUserRegister {
	public List<Map<String, Object>> queryByUserName();
	public List<Map<String, Object>> queryByEmailId();
	
}
