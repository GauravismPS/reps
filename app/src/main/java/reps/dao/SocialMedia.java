package reps.dao;

import reps.dbs.ConnectionManager;
import reps.dbs.DatabaseManager;
import reps.exceptions.InvalidSocialMediaException;
import reps.exceptions.InvalidUserIdException;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;

public class SocialMedia {
    int userId = -1;
    String type = "";                          // like twitter, insta, etc.
    String url = "";                           // url to that Media;
    Connection conn = ConnectionManager.getConnection();
    List<String> medium = List.of("Twitter", "Facebook", "Twitch", "Instagram");
    public SocialMedia(int id) throws Exception {
        userId = id;
    }
    public SocialMedia(int id, String _type, String _url) throws Exception {
        userId = id;
        type = _type;
        url = _url;
    }

    public SocialMedia(int id, String _type) throws Exception {
        userId = id;
        type = _type;
    }

    public List<Map<String, Object>> getUrlById() throws Exception {
        if(userId == -1) {
            throw new InvalidUserIdException("UserId is not valid or not set");
        }
        PreparedStatement pst = conn.prepareStatement(
                "SELECT (type, url) from socialmedia where userid=?"
        );
        pst.setInt(1, userId);
        return DatabaseManager.getQuery(pst);
    }

    public List<Map<String, Object>> getUrlByIdMedium() throws Exception {
        if(userId == -1) {
            throw new InvalidUserIdException("UserId is not valid or not set");
        }
        if(!medium.contains(type)) {
            throw new InvalidSocialMediaException(type + " is not allowed");
        }
        PreparedStatement pst = conn.prepareStatement(
                "SELECT (type, url) from SOCIALMEDIA where userid=? and type=?"
        );
        pst.setInt(1, userId);
        pst.setString(2, type);
        return DatabaseManager.getQuery(pst);
    }

    public int upsert() throws Exception {
        if(getUrlByIdMedium().size() == 0) {
            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO SOCIALMEDIA (userid, type, url) values (?, ?, ?)"
            );
            pst.setInt(1, userId);
            pst.setString(2, type);
            pst.setString(3, url);
            return DatabaseManager.modify(pst);
        }
        PreparedStatement pst = conn.prepareStatement(
                "UPDATE SOCIALMEDIA SET URL=? WHERE USERID=? and TYPE=?"
        );

        pst.setString(1, url);
        pst.setInt(2, userId);
        pst.setString(3, type);
        return DatabaseManager.modify(pst);
    }


}