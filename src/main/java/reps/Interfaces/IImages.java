package reps.Interfaces;
import java.util.List;
import java.util.Map;

import reps.dao.Images;

//contract

public interface IImages {
	public List<Map<String, Object>> getByUserId() ;
	public int deleteByImageId();
	public int deleteByUserId();
	public int insertImage();
	
}
