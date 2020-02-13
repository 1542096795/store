package cn.tedu.store.mapper;

import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.entity.Order;
public interface IOrderMapper {

    //查询区域-------------------------------------------------------------------------------


    //更新区域 update————————————————————————————————————————————————


    //插入区域insert—————————————————————————————————————————————————

    /*  对Order类的操作——————————————————————————————————————————————*/

    /**
     *用户下订单，向订单表添加数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /*  对OrderItem类的操作——————————————————————————————————————————————*/

    /**
     *
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);

    //删除区域delete—————————————————————————————————————————————————

}
