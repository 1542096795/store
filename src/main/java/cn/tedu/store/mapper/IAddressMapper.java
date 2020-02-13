package cn.tedu.store.mapper;


import cn.tedu.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 处理地址的数据的持久层接口
 */
public interface IAddressMapper {

    //查询区域-------------------------------------------------------------------------------

    /**
     * 根据收货地址数据的id查询收货地址详情
     * @param aid 收货地址数据的id
     * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
     */
    Address seltctAddressByAid(Integer aid);

    /**
     * 查询某用户的收货地址列表
     * @param uid 用户id
     * @return 该用户的收货地址列表
     */
    List<Address> selectAddressByUid(Integer uid);

    /**
     * 根据用户id统计该用户的收货地址数据的数量
     * @param uid 用户id
     * @return 该用户的收货地址数据的数量
     */
    Integer selectCountAddressByUid(Integer uid);

    /**
     * 查询某用户最后修改1条收货地址
     * @param uid 用户的id
     * @return 该用户的最后修改1条收货地址
     */
    Address selectLastModified(Integer uid);
    //更新区域 update————————————————————————————————————————————————

    /**
     * 根据aid修改收货地址信息
     *
     * @param aid
     * @param address
     * @return
     */
    Integer updateAddressByAid(@Param("aid") Integer aid, @Param("address") Address address);


    /**
     * 将指定的收货地址设置为默认
     * @param aid 收货地址数据的id
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);
    /**
     * 将某用户的所有收货地址设置为非默认
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updateNotDefaultByUid(@Param("uid") Integer uid);

    //插入区域insert—————————————————————————————————————————————————

    /**
     * 插入收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insertAddress(Address address);


    //删除区域delete—————————————————————————————————————————————————


     /**
     * 根据收货地址数据的id删除收货地址
     * @param aid 收货地址数据的id
     * @return 受影响的行数
     */
    Integer deleteAddressByAid(Integer aid);

}
