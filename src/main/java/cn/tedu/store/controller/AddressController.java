package cn.tedu.store.controller;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;


    /**
     * 增加收货地址控制器实现方法
     *
     * @param address
     * @param session
     * @return
     */
    @RequestMapping("/add_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(address, uid, username);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 根据当前登录用户的uid查询用户的收获地址
     *
     * @param session 从session 或取用户的uid
     * @return
     */
    @GetMapping("/find_address")
    public JsonResult<List<Address>> findAddressByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> addresses = addressService.findAddressByUid(uid);
        return new JsonResult<>(SUCCESS, addresses);
    }


    /**
     * 设置默认收货地址
     * 使用新的编码风格
     *
     * @param aid
     * @param session
     * @return
     * @PathVariable 该注解用于获取请求路径中占位符的值作为当前值{aid}-> aid
     */
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setAddressIsDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        String username = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        addressService.updateDefault(uid, aid, username);
        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("/{aid}/delete_address")
    public JsonResult<Void> deleteAddress(HttpSession session, @PathVariable("aid") Integer aid) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.deleteAddressByAid(aid, uid, username);
        return new JsonResult<>(SUCCESS);
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
