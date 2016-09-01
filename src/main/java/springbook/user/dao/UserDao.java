package springbook.user.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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

  public User get(String id) {
    return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id},
        new RowMapper<User>() {
          @Override
          public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            // this method call after call next()
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
          }
        });
  }

//  public User get(String id) throws ClassNotFoundException, SQLException {
//    Connection c = dataSource.getConnection();
//
//    PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
//    ps.setString(1, id);
//
//    ResultSet rs = ps.executeQuery();
//
//    User user = null;
//    if (rs.next()) {
//      user = new User();
//      user.setId(rs.getString("id"));
//      user.setName(rs.getString("name"));
//      user.setPassword(rs.getString("password"));
//    }
//
//    rs.close();
//    ps.close();
//    c.close();
//
//    if (user == null) {
//      throw new EmptyResultDataAccessException(1);
//    }
//
//    return user;
//  }

  public void deleteAll() throws SQLException {
    this.jdbcTemplate.update("delete from users");
  }

  // queryForInt deprecated...
  public int getCount() {
    return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
  }

//  public int getCount() {
//    return this.jdbcTemplate.query(new PreparedStatementCreator() {
//      @Override
//      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//        return con.prepareStatement("select count(*) from users");
//      }
//    }, new ResultSetExtractor<Integer>() {
//      @Override
//      public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
//        rs.next();
//        return rs.getInt(1);
//      }
//    });
//  }
}
