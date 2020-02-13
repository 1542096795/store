package cn.tedu.store.controller;

import cn.tedu.store.entity.vo.CartVO;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.GetInfoFromSession;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {

    @Autowired
    ICartService cartService;

    /**
     * 像购物车中添加商品
     *
     * @param num     商品的数量
     * @param pid     商品的pid
     * @param session
     * @return
     */
    @PostMapping("/add_to_cart")
    public JsonResult<Void> addToCart(Integer num, Integer pid, HttpSession session) {
        System.err.println("CartController.addToCart()");
        String username = GetInfoFromSession.getUsernameFromSession(session);
        Integer uid = GetInfoFromSession.getUidFromSession(session);
        cartService.addToCart(uid, pid, num, username);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 增加某用户的购物车的某个货物的数量
     *
     * @param session
     * @param cid
     * @return
     */
    @RequestMapping("/{cid}/num/add")
    public JsonResult<Integer> addProductNum(HttpSession session, @PathVariable("cid") Integer cid) {
        Integer uid = GetInfoFromSession.getUidFromSession(session);
        String username = GetInfoFromSession.getUsernameFromSession(session);
        Integer num = cartService.addProductNum(cid, uid, username);
        return new JsonResult<>(SUCCESS, num);
    }


    /**
     * 返回某用户的所有购物车数据
     *
     * @param session
     * @return
     */
    @GetMapping("/carts_vo")
    public JsonResult<List<CartVO>> getCartVOByUid(HttpSession session) {
        Integer uid = GetInfoFromSession.getUidFromSession(session);
        List<CartVO> cartVOList = cartService.getCartVOByUid(uid);
        return new JsonResult<>(SUCCESS, cartVOList);
    }

    /**
     * 根据用户选择的货物的cid查询购物车信息并返回，查询的结果
     * @param cids
     * @return List<CartVO>
     */
    @GetMapping("/get_by_cids")
    public JsonResult<List<CartVO>> getCartVOByCids(Integer[] cids,HttpSession session) {
        Integer  uid = GetInfoFromSession.getUidFromSession(session);
        List<CartVO> cartVOList = cartService.findCartsByCids(cids,uid);
        return new JsonResult<>(SUCCESS, cartVOList);
    }
}
