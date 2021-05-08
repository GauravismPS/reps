package reps.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.ResultSetMetaData;

public class DatabaseManager {

    public static List<Map<String, Object>> getQuery(String query) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> row = null;

        Connection conn = ConnectionManager.getConnection();
        try (Statement smt = conn.createStatement()) {
            ResultSet rst = smt.executeQuery(query);
            ResultSetMetaData metaData = rst.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            while (rst.next()) {
                row = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rst.getObject(i));
                }
                resultList.add(row);
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return resultList;
    }


    public static int modify(String query) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        try (Statement smt = conn.createStatement()) {
            int count = smt.executeUpdate(query);
            System.out.println("Number of records modified :" + count);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return -1;
        }
        return 0;
    }


    public static int modify(PreparedStatement query) throws Exception {
        try{
            query.executeUpdate();
        } 
        finally {
            try {
                if(query != null) {
                    query.close();
                }
            }
            catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                return -1;
            }
        }
        return 0;
    }
}


