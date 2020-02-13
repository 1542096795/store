package cn.tedu.store.mapper;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 购物车的持久层接口
 */
public interface ICartMapper {

    //查询区域-------------------------------------------------------------------------------

    /**
     *获取某用户在购物车中添加的某商品的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 返回购物车的数据，如果没有匹配的数据,则返回NULL
     */
    Cart selectByUidPid(@Param("uid") Integer uid,
                       @Param("pid") Integer pid);

    /**
     * 根据用户的uid查询用户的购物车数据
     * @param uid 用户的id
     * @return 用户的购物车数据
     */
    List<CartVO> selectCartsByUid(Integer uid);

    /**
     * 根据若干个cid查询购物车数据
     * @param cids
     * @return
     */
    List<CartVO> selectCartsByCids(@Param("cids") Integer[] cids);

    /**
     *根据购物车的cid 查询购物车数据
     * @param cid
     * @return 返回一个Cart对象，如果没有找到，则反回Null
     */
    Cart selectCartByCid(Integer cid);
    
    //更新区域 update————————————————————————————————————————————————

    /**
     * 修改商品数量
     * @param cid 购物车数据cid
     * @param num 新的数量 num
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updateNum(@Param("cid") Integer cid,
                      @Param("num") Integer num,
                      @Param("modifiedUser") String modifiedUser,
                      @Param("modifiedTime") Date modifiedTime
    );

    //插入区域insert—————————————————————————————————————————————————

    /**
     * 像购物车添加购物数据
     *
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insertCart(Cart cart);


    //删除区域delete—————————————————————————————————————————————————

}
