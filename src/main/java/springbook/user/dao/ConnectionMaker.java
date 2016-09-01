package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
