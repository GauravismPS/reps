package reps.dao;



import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import reps.dbs.DatabaseManager;
import reps.dbs.ConnectionManager;
import reps.exceptions.InvalidUserIdException;

public class Profile {
    Integer userId;
    Integer profileImageId;
    String firstName;
    String middleName;
    String lastName;
    String contactNo;
    Integer age;                       // In Years (Rounded).
    Integer weight;                    // In Kgs
    Integer height;                    // in centimeter
    Character gender;                  // M or F;
    Integer goalWeight;                // In Kgs
    Integer planDuration;              // In days
    String accessType;                 // User, Trainer, Admin etc.
    Float stars;                       // User Rating
    String plans;                      // Some like premium, gold etc.
    Connection conn = ConnectionManager.getConnection();



    public Profile(int _userId) throws Exception {
        userId = _userId;
    }

    public Profile(Map<String, Object> arg) throws Exception {
        for(Map.Entry<String, Object> entry : arg.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            switch (key) {
                case "userId" -> userId = (Integer) val;
                case "profileImageId" -> profileImageId = (Integer) val;
                case "firstName" -> firstName = (String) val;
                case "middleName" -> middleName = (String) val;
                case "lastName" -> lastName = (String) val;
                case "contactNo" -> contactNo = (String) val;
                case "age" -> age = (Integer) val;
                case "weight" -> weight = (Integer) val;
                case "height" -> height = (Integer) val;
                case "gender" -> gender = (Character) val;
                case "goalWeight" -> goalWeight = (Integer) val;
                case "planDuration" -> planDuration = (Integer) val;
                case "accessType" -> accessType = (String) val;
                case "stars" -> stars = (Float) val;
                case "plans" -> plans = (String) val;
            }
        }
    }

    public int deleteByUserId() throws Exception{
        if(userId == -1) throw new InvalidUserIdException(userId + " is not a valid userId");
        PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM PROFILE WHERE USERID=?"
        );
        pst.setInt(1, userId);
        return DatabaseManager.modify(pst);
    }

}