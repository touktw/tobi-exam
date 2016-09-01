package springbook.user.dao;

import java.util.List;

import springbook.user.domain.User;

/**
 * Created by tae.kim on 2016. 9. 1..
 */
public interface UserDao {
  void add(User user);

  User get(String id);

  List<User> getAll();

  void deleteAll();

  int getCount();
}
