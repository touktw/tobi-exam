package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public class DaoFactory {
//    @Bean
//    public UserDao userDao() {
//        UserDao userDao = new UserDao(connectionMaker());
//
//        return userDao;
//    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
