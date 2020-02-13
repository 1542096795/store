package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.vo.CartVO;
import cn.tedu.store.mapper.ICartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.CartNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 处理购物车的业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartMapper cartMapper;

    @Autowired
    private IProductService productService;


    /**
     * 根据用户的uid查询用户的购物车数据
     *
     * @param uid 用户的id
     * @return 用户的购物车数据
     */
    public List<CartVO> getCartVOByUid(Integer uid) {
        return findCartsByUid(uid);
    }

    /**
     * 将商品添加到购物车
     *
     * @param uid      用户uid
     * @param pid      商品pid
     * @param num      添加到购物车商品的数量
     * @param username 修改人
     */
    @Override
    public void addToCart(Integer uid, Integer pid, Integer num, String username) {
        // 根据参数uid和pid查询数据
        Cart result = findByUidPid(uid, pid);
        Date date = new Date();
        // 判断查询结果是否为null
        if (result == null) {
            // -- 是
            // -- -- 通过productService查出商品价格
            Product product = productService.findProductById(pid);
            Long price = product.getPrice();
            Cart cart = new Cart();
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setNum(num);
            cart.setPrice(price);
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            // -- -- 执行添加
            insertCart(cart);
        } else {
            // -- 否
            // -- -- 从查询结果中获取原数量，和参数num相加，得到新数量
            num += result.getNum();
            // -- -- 执行更新数量
            updateNum(result.getCid(), num, username, new Date());
        }
    }

    @Override
    public Integer addProductNum(Integer cid, Integer uid, String username) {
        // 根据参数cid查询购物车数据
        Cart cart = selectCartByCid(cid);
        // 判断查询结果是否为null：CartNotFoundException
        if (cart == null) {
            throw new CartNotFoundException("该商品已被删除！请刷新页面重试");
        }
        // 判断查询结果中的uid与参数uid是否不一致：AccessDeniedException
        if (cart.getUid() != uid) {
            throw new AccessDeniedException("非法访问！");
        }
        // 从查询结果中取出num，并加1
        Integer num = cart.getNum() + 1;
        // 执行更新数量
        updateNum(cid, num, username, new Date());
        // 返回新的数量
        return num;
    }

    /**
     * 根据若干个cid查询购物车数据
     *
     * @param cids
     * @return
     */
    @Override
    public List<CartVO> findCartsByCids(Integer[] cids, Integer uid) {
        //执行查询
        List<CartVO> cartVOList = selectCartsByCids(cids);
        //遍历查询结果，移除非当前uid的数据
        // 在循环过程中有元素的增加和移除需要使用迭代器
        Iterator<CartVO> it = cartVOList.iterator();
        while (it.hasNext()) {
           CartVO item = it.next();
           //如果当前购物车数据不是当前用户的，从List的集合中移除
           if (!item.getUid().equals(uid)){
               System.err.println("找到归属错误的数据并删除");
               it.remove();
           }

        }
        return cartVOList;
    }


//私有方法区--------------------------------------------------------------------------

    /**
     * 根据若干个cid查询购物车数据
     *
     * @param cids
     * @return
     */
    private List<CartVO> selectCartsByCids(Integer[] cids) {
        return cartMapper.selectCartsByCids(cids);
    }


    /**
     * 根据购物车的cid 查询购物车数据
     *
     * @param cid
     * @return 返回一个Cart对象，如果没有找到，则反回Null
     */
    private Cart selectCartByCid(Integer cid) {
        return cartMapper.selectCartByCid(cid);
    }

    /**
     * @param uid
     * @param pid
     * @return
     */
    private Cart findByUidPid(Integer uid, Integer pid) {
        return cartMapper.selectByUidPid(uid, pid);
    }

    /**
     * 根据用户的uid查询用户的购物车数据
     *
     * @param uid 用户的id
     * @return 用户的购物车数据
     */
    private List<CartVO> findCartsByUid(Integer uid) {
        return cartMapper.selectCartsByUid(uid);
    }


    //更新区域 update————————————————————————————————————————————————

    /**
     * 更新商品数量
     *
     * @param cid          购物车cid
     * @param num          商品数量 num
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    private void updateNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime) {
        Integer row = cartMapper.updateNum(cid, num, modifiedUser, modifiedTime);
        if (row != 1) {
            throw new UpdateException("修改数据时出现未知错误，请从试");
        }
    }

    //插入区域insert—————————————————————————————————————————————————

    /**
     * 插入购物车数据
     *
     * @param cart
     * @return 受影响的行数
     */
    private void insertCart(Cart cart) {
        Integer row = cartMapper.insertCart(cart);
        if (row != 1) {
            throw new InsertException("插入数据时出现未知错误，请从试");
        }
    }

}


