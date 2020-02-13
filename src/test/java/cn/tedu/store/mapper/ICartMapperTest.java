package cn.tedu.store.mapper;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ICartMapperTest {

    @Autowired
    ICartMapper cartMapper;

    @Test
    public void selectByUidPid() {
        System.err.println("开始测试");
        Integer uid = 36;
        Integer pid = 10000001;
        Cart cart = cartMapper.selectByUidPid(uid, pid);
        System.err.println(cart);
        System.err.println("测试结束");

    }

    @Test
    public void updateNum() {
        System.err.println("开始测试");
        Integer cid = 1;
        Integer num = 5;
        String modifiedUser = "张洋洋";
        Date modifiedTime = new Date();
        Integer row = cartMapper.updateNum(cid, num, modifiedUser, modifiedTime);
        System.err.println("受影响的行数 ： " + row);
        System.err.println("测试结束");
    }

    @Test
    public void insertCart() {
        System.err.println("开始测试");
        Integer pid = 10000001;
        Integer uid = 36;
        Integer num = 1;
        Long price = 1000L;
        String createdUser = "张洋洋";
        Date createdTime = new Date();
        String modifiedUser = "张洋洋";
        Date modifiedTime = new Date();
        Cart cart = new Cart(null, pid, uid, num, price);
        cart.setCreatedUser(createdUser);
        cart.setCreatedTime(createdTime);
        cart.setModifiedUser(modifiedUser);
        cart.setModifiedTime(modifiedTime);
        Integer row = cartMapper.insertCart(cart);
        System.err.println("受影响的行数 ： " + row);
        System.err.println("测试结束");
    }

    @Test
    public void selectCartsByUid() {

        System.err.println("开始测试");
        Integer uid = 36;
        List<CartVO> cartVOS = cartMapper.selectCartsByUid(uid);
        for (CartVO cartVO : cartVOS) {
            System.err.println(cartVO);
        }
        System.err.println("测试结束");
    }

    @Test
    public void selectCartByCid() {

        System.err.println("开始测试");
        Integer cid = 2;
        Cart cart = cartMapper.selectCartByCid(cid);
        System.err.println(cart);
        System.err.println("测试结束");
    }

    @Test
    public void selectCartsByCids() {
        System.err.println("开始测试");
        Integer[] cids = {2,3,4};
        List<CartVO> cartVOS = cartMapper.selectCartsByCids(cids);
        for (CartVO cartVO : cartVOS) {
            System.err.println(cartVO);
        }
        System.err.println("测试结束");
    }
}