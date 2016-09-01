package springbook.user;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.dao.*;
import springbook.user.domain.User;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by tae.kim on 2016. 8. 29..
 */
public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        final File f = new File(UserDaoTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//        System.out.println("Classpath root : " + f.getAbsolutePath());
//
//        String workingDir = System.getProperty("user.dir");
//        System.out.println("Current working directory : " + workingDir);
//
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        UserDao dao = context.getBean("userDao", UserDao.class);
//
//        User user = new User();
//        user.setId("touktw");
//        user.setName("tae.kim");
//        user.setPassword("asdqwe123");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + " registration success");
//
//        User user2 = dao.get(user.getId());
//        if(!user.getName().equals(user2.getName())) {
//            System.out.println("Test fail (name)");
//        }
//        else if(!user.getPassword().equals(user2.getPassword())) {
//            System.out.println("Test fail (password)");
//        }
//        else {
//            System.out.println("Read test success");
//        }
//
//        System.out.println("current size : " + dao.count());
//
//        int ret = dao.delete(user.getId());
//        System.out.println(ret + " row(s) effected");
//        System.out.println("current size : " + dao.count());
    }
}
