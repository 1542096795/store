package cn.tedu.store.service;

import cn.tedu.store.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceImplTest {
    @Autowired
    ICartService cartService;

    @Test
    public void addToCart() {
        System.err.println("开始测试");
        Integer uid = 36;
        Integer pid = 10000001;
        Integer num = 2;
        String username = "admin";
        try {
            cartService.addToCart(uid, pid, num, username);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.err.println("测试结束");
    }

    @Test
    public void addProductNum() {
        Integer cid = 2;
        String username = "admin";
        Integer uid = 36;
        Integer num = cartService.addProductNum(cid, uid, username);
        System.err.println("商品数量"+num);
        System.err.println("测试结束");
    }
}