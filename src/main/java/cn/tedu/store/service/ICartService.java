package cn.tedu.store.service;

import cn.tedu.store.entity.vo.CartVO;

import java.util.List;

/**
 * 购物车业务层接口
 */
public interface ICartService {

    /**
     * 将商品添加到购物车
     * @param uid 用户uid
     * @param pid 商品pid
     * @param num 添加到购物车商品的数量
     * @param username 修改人
     */
    void addToCart(Integer uid, Integer pid, Integer num, String username);

    /**
     * 根据用户的uid查询用户的购物车数据
     * @param uid 用户的id
     * @return 用户的购物车数据
     */
    List<CartVO> getCartVOByUid(Integer uid);


    /**
     *增加购物车某个商品的数量
     * @param cid
     * @return 返回一个Cart对象，如果没有找到，则反回Null
     */
    Integer addProductNum(Integer cid , Integer uid,String username);


    /**
     * 根据若干个cid查询购物车数据
     * @param cids
     * @return
     */
    List<CartVO> findCartsByCids(Integer[] cids ,Integer uid);

}
