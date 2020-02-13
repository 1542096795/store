package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    IUserMapper IUserMapper;

    @Test
    public void findUserByUsername() {
        User user = IUserMapper.findByUsername("2222");
        System.err.println(user);
    }

    @Test
    public void findByUid() {
        User user = IUserMapper.findByUid(31);
        System.err.println(user);
    }

    @Test
    public void updatePasword() {
        Integer uid = 31;
        String password = "1234";
        String mo = "yangyang";
        Date date = new Date();
        Integer rows = IUserMapper.updatePassword(uid, password, mo, date);
        System.out.println("受影响的行数" + rows);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("root");
        user.setPhone("13767973981");
        user.setPassword("1234");
        user.setIsDelete(0);
        Integer rows = IUserMapper.insertUser(user);
        System.err.println(rows);
    }

    @Test
    public  void updateInformation() {
        User user = new User();
        user.setUid(32);
        user.setPhone("13767973981");
        user.setGender(1);
        user.setEmail("154209679@qq.vom");
        user.setCreatedTime(new Date());
        user.setCreatedUser("admin");
        Integer rows = IUserMapper.updateInformation(user);
        System.err.println(rows);
    }

    @Test
    public void updateAvatar() {
        Integer uid = 35;
        String avatar = "avatar/sfhh/fiafhi/saf.jpa";
        String mo = "yangyang";
        Integer rows = IUserMapper.updateAvatar( uid,avatar, mo, new Date());
        System.err.println("受影响的行数" + rows);
    }
}
