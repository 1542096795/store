package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {


    @Autowired
    IOrderService orderService;

    @Test
    public void addOrder() {
        System.err.println("开始测试");
        Integer[] cids = {2, 3, 4};
        Integer uid = 36;
        String  username = "张洋洋";
        Order order= orderService.addOrder(24, cids,uid,username);
        System.err.println(order);
        System.err.println("测试结束");
    }
}