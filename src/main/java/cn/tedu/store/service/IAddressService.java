package cn.tedu.store.service;


import cn.tedu.store.entity.Address;

import java.util.List;

/**
 * 处理收获地址的业务层接口
 */
public interface IAddressService {
    /**
     * 每个用户最多允许的最大收货地址数量
     */
    public static final int MAX_ADDRESS_COUNT = 10;

    /**
     * 根据aid查询收货地址数据
     * @param aid
     * @return
     */
    Address findAddressByAid(Integer aid);

    /**
     * 用户添加收获地址处理接口
     *
     * @param address  用户添加的数据
     * @param uid      当前登录用户的uid
     * @param username 当前用户名
     * @return
     */
    void addNewAddress(Address address, Integer uid, String username);

    /**
     * 根据用户uid查询用户所有的收获地址
     *
     * @param uid 用户uid
     * @return 所有的收货地址
     */
    List<Address> findAddressByUid(Integer uid);

    /**
     * 根据aid删除用户的收获地址
     *
     * @param aid
     */
    void deleteAddressByAid(Integer aid,Integer uid,String username);

    /**
     * 设置用户，默认的收货地址
     *
     * @param uid      用户uid
     * @param aid      地址aid
     * @param username 当前操作的用户名
     */
    void updateDefault(Integer uid, Integer aid, String username);
}
