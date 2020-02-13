package cn.tedu.store.controller;


import cn.tedu.store.entity.Order;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.util.GetInfoFromSession;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    @Autowired
    IOrderService orderService;

    @PostMapping("create")
    public JsonResult<Order> addOrder(HttpSession session, Integer[] cids, Integer aid) {
        Integer uid = GetInfoFromSession.getUidFromSession(session);
        String username = GetInfoFromSession.getUsernameFromSession(session);
        Order order = orderService.addOrder(aid, cids, uid, username);
        return new JsonResult<>(SUCCESS,order);
    }
}
