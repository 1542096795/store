package cn.tedu.store.service;

import cn.tedu.store.entity.Order;

/**
 * 订单数据处理的业务层接口
 */
public interface IOrderService {

    /**
     * 对用户提交订单的数据的处理
     * @param aid 用户收货地址
     * @param cids 用户的购物车选中的数据
     * @param uid 当前提交订单的用户编号
     * @param uesrname 当前提交订单的用户名
     * @return  处理完成的订单数据
     */
    Order addOrder(Integer aid,Integer[] cids,Integer uid ,String uesrname);

}
