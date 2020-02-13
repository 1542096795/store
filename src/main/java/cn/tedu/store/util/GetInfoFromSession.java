package cn.tedu.store.util;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * 从session中获取数据的工具类
 */
public class GetInfoFromSession implements Serializable {
    private static final long serialVersionUID = 7076855724423791762L;

    /**
     * 从session中取出当前登录的用户的uid
     *
     * @param session
     * @return 只有子类需要使用 所以使用protected 就行
     */
  public static  Integer getUidFromSession(HttpSession session) {
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
    public static  String getUsernameFromSession(HttpSession session) {
        String username = session.getAttribute("username").toString();
        System.out.println("session 中取出的: username ：" + username);
        return username;
    }
}
