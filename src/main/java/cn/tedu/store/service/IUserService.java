package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import org.springframework.stereotype.Service;


/**
 * 处理用户数据的业务层接口
 */
@Service
public interface IUserService {

    /**
     * 用户注册
     *
     * @param user
     */
    void reg(User user);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 修改密码
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @return
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 修改用户信息的业务方法
     *
     * @param user     用户信息
     * @param username 用户名
     * @param uid      用户uid
     * @return
     */
    void changeInformation(User user, String username, Integer uid);

    /**
     * 更改用户头像
     *
     * @param uid      用户uid 从session中获取
     * @param username 当前操作修改人
     * @param avatar   用户头像路径
     */
    void changeAvatar(Integer uid, String username, String avatar);

    /**
     * 根据用户的uid,查询用户信息业务方法
     *
     * @param uid
     * @return
     */
    User getUserByUid(Integer uid);


}
