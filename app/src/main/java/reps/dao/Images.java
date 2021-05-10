package reps.dao;




import java.util.List;
import java.util.Map;
import java.sql.Connection;
import reps.dbs.DatabaseManager;
import reps.dbs.ConnectionManager;
import java.sql.PreparedStatement;
import reps.exceptions.InvalidUserIdException;
import reps.exceptions.InvalidImageIdException;
import reps.exceptions.InvalidImageUrlException;
public class Images {
    int imageId=-1;
    int userId=-1;
    String imageUrl="";
    Connection conn = ConnectionManager.getConnection();

    public Images(int _userId) throws Exception {
        userId = _userId;
    }

    public Images(int _userId, String _imageUrl) throws Exception {
        imageUrl = _imageUrl;
        userId = _userId;
    }

    public List<Map<String, Object>> getByUserId() throws Exception {
        if(userId == -1) throw new InvalidUserIdException(userId + " is invalid");
        PreparedStatement pst = conn.prepareStatement(
                "SELECT (imageId, imageUrl) from Images where UserId=?"
        );
        pst.setInt(1, userId);
        return DatabaseManager.getQuery(pst);
    }

    public int deleteByImageId() throws Exception {
        if(imageId == -1) throw new InvalidImageIdException(imageId + " is not a valid Image Id");
        PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM IMAGES WHERE IMAGEID=?"
        );
        pst.setInt(1, imageId);
        return DatabaseManager.modify(pst);
    }

    public int deleteByUserId() throws Exception {
        if(userId == -1) throw new InvalidUserIdException(userId + " is invalid");
        PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM IMAGES WHERE USERID=?"
        );
        pst.setInt(1, userId);
        return DatabaseManager.modify(pst);
    }

    public int insertImage() throws Exception {
        if(userId == -1) throw new InvalidUserIdException(userId + " is invalid");
        if(imageUrl.equals("")) throw new InvalidImageUrlException(imageUrl + " is invalid");
        PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO (userId, imageUrl) values(?, ?)"
        );
        pst.setInt(1, userId);
        pst.setString(2, imageUrl);
        return DatabaseManager.modify(pst);
    }
}