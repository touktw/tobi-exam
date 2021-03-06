package springbook.user.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import springbook.user.domain.User;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
//    @Autowired
//    private ApplicationContext context;

  @Autowired
  private UserDaoJdbc dao;
  @Autowired
  private DataSource dataSource;
  private User user1;
  private User user2;
  private User user3;

  @Before
  public void setUp() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        dao = context.getBean("userDao", UserDaoJdbc.class);
//        this.dao = this.context.getBean("userDao", UserDaoJdbc.class);
    user1 = new User("test1", "tester1", "123");
    user2 = new User("test2", "tester2", "456");
    user3 = new User("test03", "tester3", "789");
  }

  @Test
  public void addAndGet() throws SQLException, ClassNotFoundException {
    dao.deleteAll();
    assertEquals(dao.getCount(), 0);

    dao.add(user1);
    dao.add(user2);
    assertEquals(dao.getCount(), 2);

    User userGet1 = dao.get(user1.getId());
    assertThat(userGet1.getName(), is(user1.getName()));
    assertThat(userGet1.getPassword(), is(user1.getPassword()));

    User userGet2 = dao.get(user2.getId());
    assertThat(userGet2.getName(), is(user2.getName()));
    assertThat(userGet2.getPassword(), is(user2.getPassword()));
  }

  @Test
  public void count() throws SQLException, ClassNotFoundException {
    dao.deleteAll();
    assertThat(dao.getCount(), is(0));

    dao.add(user1);
    assertThat(dao.getCount(), is(1));

    dao.add(user2);
    assertThat(dao.getCount(), is(2));

    dao.add(user3);
    assertThat(dao.getCount(), is(3));
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void getUserFailure() throws SQLException, ClassNotFoundException {
    dao.deleteAll();
    assertThat(dao.getCount(), is(0));

    dao.get("unknown_id");
  }

  @Test
  public void getAll() throws SQLException, ClassNotFoundException {
    dao.deleteAll();

    List<User> users0 = dao.getAll();
    assertThat(users0.size(), is(0));

    dao.add(user1);
    List<User> users1 = dao.getAll();
    assertThat(users1.size(), is(1));
    checkSameUser(user1, users1.get(0));

    dao.add(user2);
    List<User> users2 = dao.getAll();
    assertThat(users2.size(), is(2));
    checkSameUser(user1, users2.get(0));
    checkSameUser(user2, users2.get(1));

    dao.add(user3);
    List<User> users3 = dao.getAll();
    assertThat(users3.size(), is(3));
    checkSameUser(user3, users3.get(0));
    checkSameUser(user1, users3.get(1));
    checkSameUser(user2, users3.get(2));
  }

  private void checkSameUser(User user1, User user2) {
    assertThat(user1.getId(), is(user2.getId()));
    assertThat(user1.getName(), is(user2.getName()));
    assertThat(user1.getPassword(), is(user2.getPassword()));
  }

  @Test(expected = DuplicateKeyException.class)
  public void duplicateKey() {
    dao.deleteAll();

    dao.add(user1);
    dao.add(user1);
  }

  @Test
  public void sqlExceptionTranslate() {
    dao.deleteAll();

    try {
      dao.add(user1);
      dao.add(user1);
    } catch (DuplicateKeyException ex) {
      SQLException sqlEx = (SQLException) ex.getRootCause();
      SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
      assertThat(set.translate(null, null, sqlEx), instanceOf(DuplicateKeyException.class));
    }
  }
}