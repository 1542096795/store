package cn.tedu.store.controller;


import cn.tedu.store.controller.exception.*;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 处理用户数据相关请求的控制器类
 *
 * @author yangyang
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;



    /**
     * 上传文件的最大值
     */
    private static final long FILE_MAX_SIZE = 1024 * 500;

    /**
     * 文件上传类型
     */
    private static final List<String> FILE_TYPES = new ArrayList<>();

    static {
        FILE_TYPES.add("image/jpeg");
        FILE_TYPES.add("image/png");
    }

    /**
     * @param session
     * @param file
     * @return
     */
    @PostMapping("change_avatar")
    public JsonResult changeAvatar(HttpSession session,@RequestParam("avatar_file") MultipartFile file) {

        // 检查：上传的文件是否为空
        if (file.isEmpty()) {
            //是：没有选择文件，或选择的文件时0字节
            throw new FileEmptyException("没有选择文件，或者选择的文件是空的");
        }
        //检查：上传的文件的大小
        System.err.println("file.getSize->" + file.getSize());
        if (file.getSize() > FILE_MAX_SIZE) {
            //如果上传文件，超过500K则抛出异常，注意我们这里的文件大小有可能超过springBoot默认的大小（1M）
            //则需要在application.properties配置或者在StoreApplication配置(这个项目我选择的是后者)
            throw new FileSizeException("上传的文件大小超出了限制，允许上传文件的最大值为" + FILE_MAX_SIZE);
        }
        //检查：上传的文件类型
        if (!FILE_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("上传的文件类型错误，仅支持上传" + FILE_TYPES + "类型的文件");
        }

        //上传到的文件夹
        String dirPath = session.getServletContext().getRealPath("upload");
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //获取源文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename->" + originalFilename);
        //扩展名
        String suffix = "";
        int index = originalFilename.lastIndexOf(".");//获取最后一个点的位置
        if (index > 0) {
            suffix = originalFilename.substring(index);//从最后一个点开始，取后续所有字符串，及文件后缀名
        }
        //文件全名
        //UUID 生成一个随机的字符串，目的是为了防止文件名相同，导致文件被覆盖
        String filename = UUID.randomUUID().toString() + suffix;
        //保存到的文件
        File dest = new File(dir, filename);
        //保存用户上传的头像
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadIOException("文件出现读写异常");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new FileUploadStateException("文件可能已经被移动或者被删除，不可访问到该文件");
        }
        //头像路径


        String avatar = "/upload/" + filename;
        //从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        //执行更新数据库
        userService.changeAvatar(uid, username, avatar);
        return new JsonResult<>(SUCCESS, avatar);
    }

    /**
     * @param session
     * @return
     */
    @GetMapping("/get_by_uid")
    public JsonResult<User> getUserByUid(HttpSession session) {
        //从session中取出uid
        Integer uid = getUidFromSession(session);
        User data = userService.getUserByUid(uid);
        return new JsonResult<>(SUCCESS, data);
    }

    @RequestMapping("change_info")
    public JsonResult<User> changeInformation(User user, HttpSession session) {
        String username = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        userService.changeInformation(user, username, uid);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 修改用户密码
     *
     * @return
     */
    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(@RequestParam("old_password") String oldPassword,@RequestParam("new_password") String newPassword, HttpSession session) {

        System.out.println("前端传回数据—— oldPassword : " + oldPassword + " ,newPassword : " + newPassword);
        System.out.println("UserController.changePassword () 开始从session中取出数据");
        //获取当前登录用户的uid 和username
        String username = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        //开始传如参数致业务层处理业务
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 处理用户登录的控制器方法
     *
     * @param username 用户的登录账号
     * @param password 用户的登录密码
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public JsonResult<Void> handleLogin(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        session.setAttribute("username", data.getUsername());
        System.out.println("UserController.handleLogin() ----        session.setAttribute(\"username\", data.getUsername());" + data.getUsername());
        session.setAttribute("uid", data.getUid());
        System.out.println("UserController.handleLogin() ----session.setAttribute(\"uid\", data.getUid());" + data.getUid());
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 用户注册的控器方法
     *
     * @param user 用户的注册数据会被springMVC封装进User类中
     * @return
     */
    @PostMapping("/reg")
    public JsonResult<Void> handleReg(User user) {
        System.err.println("UserController.handleReg" + user);
        //开始处理注册业务
        userService.reg(user);

        JsonResult jsonResult = new JsonResult<>(SUCCESS);
        return jsonResult;
    }

    /**
     * 从session中取出当前登录的用户的uid
     *
     * @param session
     * @return 只有子类需要使用 所以使用protected 就行
     */
    protected Integer getUidFromSession(HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        System.out.println("session 中取出的: uid ：" + uid);
        return uid;
    }

    /**
     * 从session中取出当前登录的用户的username
     *
     * @param session
     * @return
     */
    protected String getUsernameFromSession(HttpSession session) {
        String username = session.getAttribute("username").toString();
        System.out.println("session 中取出的: username ：" + username);
        return username;
    }
}
