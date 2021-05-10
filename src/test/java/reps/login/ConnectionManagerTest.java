package reps.login;

import static org.junit.Assert.*;

import org.junit.Test;
import reps.dbs.ConnectionManager;

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
