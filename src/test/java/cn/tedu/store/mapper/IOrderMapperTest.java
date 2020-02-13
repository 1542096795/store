package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IOrderMapperTest {

    @Autowired
    IOrderMapper orderMapper;

    @Test
    public void insertOrder() {
        System.err.println("开始测试");
        Order order = new Order();
        Date date = new Date();
        order.setUid(36);
        order.setReceiverName("张洋洋");
        order.setOrderTime(date);
        order.setPayTime(date);
        order.setPayType(0);
        order.setReceiverProvince("江西省");
        order.setReceiverCity("吉安市");
        order.setReceiverArea("峡江县");
        order.setTotalPrice(6000L);
        order.setState(1);
        order.setReceiverAddress("戈平乡");
        order.setReceiverPhone("13467973981");
        Integer row = orderMapper.insertOrder(order);
        System.err.println("受影响的行数 ： " + row);
        System.err.println("测试结束");
    }


    @Test
    public void insertOrderItem() {
        System.err.println("开始测试");
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000001);
        orderItem.setNum(2);
        orderItem.setPrice(20L);
        orderItem.setImage("/images/portal/00GuangBo1040A5GBR0731/");
        orderItem.setTitle("广博(GuangBo)10本装40张A5牛皮纸记事本子日记本办公软抄本GBR0731");
        Integer row = orderMapper.insertOrderItem(orderItem);
        System.err.println("受影响的行数 ： " + row);
        System.err.println("测试结束");
    }

}