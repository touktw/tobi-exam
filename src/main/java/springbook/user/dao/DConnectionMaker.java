package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public class DConnectionMaker implements ConnectionMaker {
//    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/tobi_test", "touktw", "asdqwe123");
//
//        return c;
//    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/tobi_test", "touktw", "asdqwe123");

        return c;
    }
}
