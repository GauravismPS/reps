package reps.Interfaces;
import java.util.List;
import java.util.Map;

import reps.dao.SocialMedia;

public interface ISocialMedia {
	public List<Map<String, Object>> getUrlById() ;
	public List<Map<String, Object>> getUrlByIdMedium();
	public int upsert() ;
	
}
