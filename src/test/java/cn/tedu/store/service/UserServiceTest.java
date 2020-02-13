package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.service.exception.UserIsNullException;
import cn.tedu.store.service.exception.UsernameConflictException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    IUserService userService;


    @Test
    public void  changeAvatar(){
        try {
            Integer uid = 8;
            String username = "张洋";
            String avatar = "H:/fl/aa.txt";
            userService.changeAvatar(uid, username, avatar);
            System.err.println("OK.");
        } catch (ServiceException e) {
         e.printStackTrace();
        }
    }

    @Test
    public void changeInformation(){
        System.err.println("开始测试");
        User user = new User();
        user.setPhone("13265746");
        user.setEmail("2316846134@qq.oga");
        user.setGender(0);
        userService.changeInformation(user,"张洋洋",35);
        System.err.println("Ok");
    }
    @Test
    public void getUserByUid(){
       User user = userService.getUserByUid(35);
        System.err.println(user);
    }

    @Test
    public void login() {
        System.err.println("开始测试");

        try {
            userService.login("张洋洋a", "1111"
            );
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.err.println("测试完毕");
    }

    @Test
    public void reg() {
        System.err.println("开始测试");
        User user = new User();
        user.setUsername("1111 ");
        user.setPassword("1111");
        user.setGender(1);
        user.setPhone("15649131654");
        user.setEmail("19379247295@qq.com");
        try {
            userService.reg(user);
        } catch (UserIsNullException e) {
            e.printStackTrace();
        } catch (UsernameConflictException e) {
            e.printStackTrace();
        } catch (InsertException e) {
            e.printStackTrace();
        }
        System.err.println("测试完毕");
    }


    @Test
    public void changePassword() {
        try {
            Integer uid = 28;
            String username = "张洋";
            String oldPassword = "3698";
            String newPassword = "3698";
            userService.changePassword(uid, username, oldPassword, newPassword);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
}
