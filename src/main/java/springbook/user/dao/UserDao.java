package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import springbook.user.domain.User;

import javax.sql.DataSource;

import java.sql.*;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public class UserDao {
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplate;

  public UserDao() {
  }

  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate();
    this.jdbcTemplate.setDataSource(dataSource);
    this.dataSource = dataSource;

  }

  public void add(final User user) throws ClassNotFoundException, SQLException {
    this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
        user.getId(), user.getName(), user.getPassword());
  }

  public User get(String id) throws ClassNotFoundException, SQLException {
    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
    ps.setString(1, id);

    ResultSet rs = ps.executeQuery();

    User user = null;
    if (rs.next()) {
      user = new User();
      user.setId(rs.getString("id"));
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
    }

    rs.close();
    ps.close();
    c.close();

    if (user == null) {
      throw new EmptyResultDataAccessException(1);
    }

    return user;
  }

  public void deleteAll() throws SQLException {
    this.jdbcTemplate.update("delete from users");
  }


  public int getCount() throws SQLException, ClassNotFoundException {
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      c = dataSource.getConnection();
      ps = c.prepareStatement("select count(*) from users");
      rs = ps.executeQuery();

      rs.next();
      return rs.getInt(1);
    } catch (SQLException e) {
      throw e;
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {

        }
      }
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
}
