package cn.tedu.store.service.impl;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.IUserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户业务层处理数据的类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;


    /**
     * 修改用户头像
     * @param uid  用户uid 从session中获取
     * @param username  当前操作修改人
     * @param avatar  用户头像路径
     */
    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        System.out.println(" UserServiceImpl.changeAvatar() ———开始");
        //通过参数uid查询用户数据
        System.out.println("开始通过uid查询数据库");
        User result = userMapper.findByUid(uid);
        System.out.println("开始判断用户数据是否为空");
        //判断查询结果是否为null
        if (result == null) {
            //是：抛出UserNotFoundException
            throw new UserNotFountException("不存在此用户,或用户登录信息失效");
        }
        System.out.println("开始判断用户是否被注销");
        //判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            //是：用户数据被标记为已删除，抛出UserNotFoundException
            throw new UserNotFountException("不存在此用户,该用户已被注销！");
        }
       //执行用户头像修改
        System.out.println("开始执行用户头像修改");
        Integer rows = userMapper.updateAvatar(uid,avatar,username,new Date());
        System.out.println("执行用户头像修改结束");
        //判段受影响的行数是否为1
        if (rows != 1) {
            //是：更新错误，抛出Up
            throw new UpdateException("修改用户数据错误，请联系管理员");
        }
        System.out.println(" UserServiceImpl.changeAvatar() ———结束");
    }

    /**
     * 修改用户信息业务层实现方法
     *
     * @param user     用户信息
     * @param username 用户名
     * @param uid      用户uid
     * @return
     */
    @Override
    public void changeInformation(User user, String username, Integer uid) {
        System.out.println(" UserServiceImpl.changeInformation() ———开始");
        //通过参数uid查询用户数据
        System.out.println("开始通过uid查询数据库");
        User result = userMapper.findByUid(uid);

        System.out.println("开始判断用户数据是否为空");
        //判断查询结果是否为null
        if (result == null) {
            //是：抛出UserNotFoundException
            throw new UserNotFountException("不存在此用户,或用户登录信息失效");
        }
        System.out.println("开始判断用户是否被注销");
        //判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            //是：用户数据被标记为已删除，抛出UserNotFoundException
            throw new UserNotFountException("不存在此用户,该用户已被注销！");
        }
        //将参数uid封装到参数user的uid中
        user.setUid(uid);
        //将参数username封装到参数user的modifiedUser中
        user.setModifiedUser(username);
        //创建时间对象，封装到参数user的modifiedTime中
        user.setModifiedTime(new Date());
        //执行修改，并获取返回值
        Integer rows = userMapper.updateInformation(user);
        //判段受影响的行数是否为1
        if (rows != 1) {
            //是：更新错误，抛出Up
            throw new UpdateException("修改用户数据错误，请联系管理员");
        }

        System.out.println(" UserServiceImpl.changeInformation() ———结束");
    }

    /**
     * 查询用户信息
     *
     * @param uid 用户的id
     * @return
     */
    @Override
    public User getUserByUid(Integer uid) {
        System.out.println(" UserServiceImpl.getUserByUid() ———开始");
        //通过参数uid查询用户数据
        System.out.println("开始通过uid查询数据库");
        User result = userMapper.findByUid(uid);
        System.out.println("查询结束,查询结果：" + result.toString());
        System.out.println("开始判断用户数据是否为空");
        //判断查询结果是否为null
        if (result == null) {
            //是：抛出UserNotFoundException
            throw new UserNotFountException("不存在此用户,或用户登录信息失效");
        }
        System.out.println("开始判断用户是否被注销");
        //判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            //是：用户数据被标记为已删除，抛出UserNotFoundException
            throw new UserNotFountException("不存在此用户,该用户已被注销！");
        }
        System.out.println("开始清除不必要返回给用户的数据");
        //创建新的User对象
        User user = new User();
        //将查询结果中的username,phone,email,gender封装进User对象中
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        user.setUid(uid);
        user.setPhone(result.getPhone());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        System.out.println("返回最终结果， UserServiceImpl.getUserByUid() ———结束");
        //返回查询结果
        return user;
    }

    /**
     * 修改用户的密码
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        System.out.println("UserServiceImpl.changePassword()---开始");

        //更具uid查询用户信息
        // 判断前端是否传回数据,如果为空则抛出UserIsNullException
        System.err.println("开始判断前端传回数据是否有问题");
        if (newPassword == null || oldPassword == null) {
            throw new UserIsNullException("前端传回的数据出错！");
        }

        System.err.println("开始判断用户名是否存在");
        // 根据用户名查找所匹配的用户,如果用户名不存在，则抛出UserNotFountException
        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFountException("用户不存在");
        }

        //判断查询结构中的isDelete是否为1
        //是用户被标记为已删除，抛出UserNotFoundException
        // 判断用户是否被注销
        if (result.getIsDelete().equals(1)) {
            System.err.println("修改失败，用户已被注销");
            throw new UserNotFountException("用户已经注销，请重新输入");
        }

        System.err.println("开始对前端传回的密码进行加密");
        String oldMd5Password = this.getMd5password(oldPassword, result.getSalt());
        // 对前端传回来的密码进行加密
        System.err.println("开始比对旧密码");
        // 开始与数据库密码进行匹配 如果匹配错误则抛出 PasswordNotMatchException
        if (!result.getPassword().equals(oldMd5Password)) {
            System.err.println("原密码错误");
            throw new PasswordNotMatchException("原密码错误，请重新输入");
        }
        //对新密码进行加密
        System.err.println("开始加密新密码");
        String newMd5Password = this.getMd5password(newPassword, result.getSalt());
        //执行加密过去返回值
        System.err.println("开始更新用户密码");
        Integer rows = userMapper.updatePassword(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新密码失败，请联系管理员！");
        }
        System.out.println("UserServiceImpl.changePassword()--结束");
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        System.err.println("UserServiceImpl.login() ---开始");
        System.err.println("username  = " + username + " password = " + password);
        // 判断前端是否传回数据,如果为空则抛出UserIsNullException
        System.err.println("开始判断前端传回数据是否有问题");
        if (username == null || password == null) {
            throw new UserIsNullException("前端传回的数据为空！");
        }
        System.err.println("开始判断用户名是否存在");
        // 根据用户名查找所匹配的用户,如果用户名不存在，则抛出UserNotFountException
        User user = userMapper.findByUsername(username);
        if (user == null) {
            System.err.println("登录失败，用户不存在");
            throw new UserNotFountException("用户不存在，请重新输入");
        }
        // 判断用户是否被注销
        if (user.getIsDelete().equals(1)) {
            System.err.println("登录失败，用户已被注销");
            throw new UserNotFountException("用户已经注销，请重新输入");
        }
        System.err.println("开始对前端传回的密码进行加密");
        // 对前端传回来的密码进行加密
        String md5Password = this.getMd5password(password, user.getSalt());

        System.err.println("开始比对密码");
        // 开始与数据库密码进行匹配 如果匹配错误则抛出 PasswordNotMatchException
        if (!user.getPassword().equals(md5Password)) {
            System.err.println("登录失败！密码错误");
            throw new PasswordNotMatchException("密码错误，请重新登录");
        }
        // 将数据库中不因该返回的字段设置为null
        // 例如isDelete ,cratedUser,password,salt 等

        user.setCreatedTime(null);
        user.setCreatedUser(null);
        user.setModifiedTime(null);
        user.setModifiedUser(null);
        user.setIsDelete(null);
        user.setSalt(null);
        user.setPassword(null);

        System.err.println("用户数据：" + user);
        System.err.println("用户登录成功，业务层处理完毕！");
        // 登录成功，返回用户数据
        System.err.println("UserServiceImpl.login() ---结束");
        return user;
    }

    @Override
    /**
     * 用户注册业务类
     * @param user  用户的注册数据
     * throws UserIsNullException, InsertException, UsernameConflictException
     */
    public void reg(User user) {
        System.out.println("UserServiceImpl.reg()  开始处理用户注册数据");
        System.out.println("开始判断用户数据是否合法！");
        // 判断用户数据是否为空
        if (user.getUsername() == null) {
            System.out.println("用户数据未提交");
            throw new UserIsNullException("未接受到用户注册数据，用户为空,请重新填写用户数据");
        } else {
            // 从数据库尝试查找用户数据，如果不为空 则抛出 UsernameConflictException 异常
            // 用户已被注册
            System.out.println("开始查找用户数据");
            User result = userMapper.findByUsername(user.getUsername());
            System.out.println("查找用户数据结束");
            if (result != null) {
                System.out.println("用户已被注册");
                throw new UsernameConflictException("用户已经被注册！请更换账号重新注册！");
            } else {
                System.out.println("开始补填入用户数据");
                // 将用户的isDelete数据设置为0
                user.setIsDelete(0);
                System.out.println("开始用户密码加密");
                System.out.println("开始获取 salt");
                String salt = UUID.randomUUID().toString();
                user.setSalt(salt);
                System.out.println("获取 salt 结束");
                System.out.println("开始加密用户密码");
                String md5Password = getMd5password(user.getPassword(), salt);
                user.setPassword(md5Password);
                System.out.println("加密用户密码结束");
                System.out.println("开始补全用户日志------------");
                Date time = new Date();
                System.out.println("当前时间 ： " + time);
                user.setCreatedUser(user.getUsername());
                user.setCreatedTime(time);
                user.setModifiedUser(user.getUsername());
                user.setModifiedTime(time);
                System.out.println("开始补全用户日志------------");

                // 开始插入用户数据到数据库
                System.out.println("开始将用户数据写入数据库");
                Integer row = userMapper.insertUser(user);
                if (row != 1) {
                    throw new InsertException("插入用户数据失败！ 请联系管理员");
                }
                System.out.println("将用户数据写入数据库完毕");
            }
        }
        System.out.println("处理用户数据结束");
        System.out.println("UserServiceImpl.reg()，用户注册完毕");
    }

    /**
     * 执行加密MD5
     *
     * @param password
     * @param salt
     * @return
     */
    private String getMd5password(String password, String salt) {
        // 加密规则：在原密码的左侧和右侧均拼接盐值，循环3次
        String md5Password = salt + password + salt;
        for (int i = 0; i < 5; i++) {
            md5Password = DigestUtils.md5DigestAsHex((md5Password).getBytes());
        }
        return md5Password;
    }

}
