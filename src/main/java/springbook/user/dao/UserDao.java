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
import java.util.List;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public class UserDao {
  private JdbcTemplate jdbcTemplate;
  private RowMapper<User> userMapper = new RowMapper<User>() {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setId(rs.getString("id"));
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
      return user;
    }
  };

  public UserDao() {
  }

  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate();
    this.jdbcTemplate.setDataSource(dataSource);
  }

  public void add(final User user) throws ClassNotFoundException, SQLException {
    this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
        user.getId(), user.getName(), user.getPassword());
  }

  public User get(String id) {
    return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id},
        this.userMapper);
  }

  public void deleteAll() throws SQLException {
    this.jdbcTemplate.update("delete from users");
  }

  // queryForInt deprecated...
  public int getCount() {
    return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
  }

  public List<User> getAll() {
    return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
  }
}
