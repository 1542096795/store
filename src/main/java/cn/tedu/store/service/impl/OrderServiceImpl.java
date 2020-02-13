package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.entity.vo.CartVO;
import cn.tedu.store.mapper.IOrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户提交订单的数据的业务层处理类
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICartService cartService;

    /**
     * 对用户提交订单的数据的处理
     *
     * @param aid      用户收货地址
     * @param cids     用户的购物车选中的数据
     * @param uid      当前提交订单的用户编号
     * @param uesrname 当前提交订单的用户名
     * @return
     */
    @Override
    public Order addOrder(Integer aid, Integer[] cids, Integer uid, String uesrname) {
        // 通过参数aid查询收货地址数据
        Address address = addressService.findAddressByAid(aid);
        // 检查数据归属是否正确
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问！");
        }
        // 通过参数cids查询购物车数据，得到List<CartVO>
        List<CartVO> cartVOList = cartService.findCartsByCids(cids, uid);
        // 遍历List<CartVO>，计算商品总价
        Long totalPrice = 0L;
        for (CartVO cartVO : cartVOList) {
            totalPrice += cartVO.getRealPrice()*cartVO.getNum();
        }
        // 创建当前时间对象now
        Date date = new Date();
        // 创建Order对象
        Order order = new Order();
        // 封装Order属性：uid
        order.setUid(uid);
        // 封装Order属性：recv_???
        order.setReceiverName(uesrname);
        order.setReceiverProvince(address.getProvinceName());
        order.setReceiverCity(address.getCityName());
        order.setReceiverArea(address.getAreaName());
        order.setReceiverAddress(address.getAddress());
        order.setReceiverPhone(address.getPhone());
        // 封装Order属性：total_price(以上计算的商品总价)
        order.setTotalPrice(totalPrice);
        // 封装Order属性：status(0)
        order.setState(0);
        // 封装Order属性：order_time(now)
        order.setOrderTime(date);
        // 封装Order属性：pay_type(null), pay_time(null)
        order.setPayType(null);
        order.setPayTime(null);
        // 封装Order属性：4项日志
        order.setCreatedTime(date);
        order.setModifiedTime(date);
        order.setCreatedUser(uesrname);
        order.setModifiedUser(uesrname);
        // 向t_order表中插入数据：saveOrder(Order order)
        insertOrder(order);

        // 遍历List<CartVO>

        for (CartVO cartVO : cartVOList) {
            // -- 创建OrderItem对象
            OrderItem orderItem = new OrderItem();
            // -- 封装OrderItem属性：oid(order.getOid())
            orderItem.setOid(order.getOid());
            // -- 封装OrderItem属性：pid,title,image,price,num
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            // -- 封装OrderItem属性：4项日志
            orderItem.setCreatedTime(date);
            orderItem.setModifiedTime(date);
            orderItem.setCreatedUser(uesrname);
            orderItem.setModifiedUser(uesrname);
            // -- 向t_order_item表中插入若干条数据：saveOrderItem(OrderItem orderItem)
            insertOrderItem(orderItem);
        }
        // 返回order对象，返回之前可以将部分数据设置为null
        order.setCreatedTime(null);
        order.setModifiedTime(null);
        order.setCreatedUser(null);
        order.setModifiedUser(null);
        order.setUid(null);
        return order;
    }

    //private 区域*****************************************************************************

    /**
     * 用户下订单，向订单表添加数据
     *
     * @param order 订单数据
     * @return 受影响的行数
     */
    private void insertOrder(Order order) {
        Integer row = orderMapper.insertOrder(order);
        if (row != 1) {
            throw new InsertException("插入数据出现未知错误，请联系管理员");
        }
    }

    /**
     * @param orderItem
     * @return
     */
    private void insertOrderItem(OrderItem orderItem) {
        Integer row = orderMapper.insertOrderItem(orderItem);
        if (row != 1) {
            throw new InsertException("插入数据出现未知错误，请联系管理员");
        }
    }
}
