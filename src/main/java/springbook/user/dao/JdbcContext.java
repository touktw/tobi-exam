package springbook.user.dao;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public class JdbcContext {
  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
    Connection c = null;
    PreparedStatement ps = null;

    try {
      c = dataSource.getConnection();
      ps = stmt.makePrepareStatement(c);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {

        }
      }
      if (c != null) {
        try {
          c.close();
        } catch (SQLException e) {

        }
      }
    }
  }

  public void executeSql(final String query, final String... args) throws SQLException {
    workWithStatementStrategy(new StatementStrategy() {
      @Override
      public PreparedStatement makePrepareStatement(Connection c) throws SQLException {
        int idx = 0;
        PreparedStatement ps = c.prepareStatement(query);
        for (String arg : args) {
          ps.setString(++idx, arg);
        }
        return ps;
      }
    });
  }


}
