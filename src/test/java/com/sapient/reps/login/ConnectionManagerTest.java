package com.sapient.reps.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sapient.reps.dbs.ConnectionManager;

import java.sql.Connection;

public class ConnectionManagerTest {


    // @Test
    // public void tester() throws Exception {
    //     String str = ConnectionManager.writeConnString();
    //     assertEquals(str, "");
    // }

    @Test
    public void testConn() throws Exception {
        Connection str = ConnectionManager.getConnection();
        assertNotNull(str);
    }
}
