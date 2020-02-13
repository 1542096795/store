package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.IAddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 处理地址数据的业务层处理类
 */
@Service
public class AddressServiceImpl implements IAddressService {


    @Autowired
    private IAddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;


    @Override
    public Address findAddressByAid(Integer aid) {
        return seltctAddressByAid(aid);
    }

    /**
     * 添加收获地址处理类
     *
     * @param address
     * @param uid
     * @param username
     * @return
     */
    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        System.err.println(address);
        //根据参数uid获取用户的收获地址数据的数量
        Integer count = selectCountAddressByUid(uid);
        //判断数量是否大于或等于限制值
        if (count >= MAX_ADDRESS_COUNT) {
            //是：抛出AddresssSizeLimitException
            throw new AddresssSizeLimitException("对不起您最多能添加 " + MAX_ADDRESS_COUNT + " 个收货地址");
        }
        //补全数据：isDefault
        //判段收货地址数据的数量是否为0
        //是：即将插入的收获地址是默认的，isDefault=1
        //否：即将插入的收获地址不是默认的，isDefault=0
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        //补全数据：将参数uid 封装到参数address中
        address.setUid(uid);
        //补全数据:将参数username封装为参数address的createUser和modifiedUser属性值
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        //补全数据:创建当前时间对象，封装为参数address中的createTime和modifiedTime属性值
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);
        //补全省市区的名称
        District province = districtService.getByCode(address.getProvinceCode());
        if (province != null) {
            System.err.println("province : " + province);
            address.setProvinceName(province.getName());
        }
        District city = districtService.getByCode(address.getCityCode());
        if (city != null) {
            System.err.println("city : " + city);
            address.setCityName(city.getName());
        }
        District area = districtService.getByCode(address.getAreaCode());
        if (area != null) {
            System.err.println("area :" + area);
            address.setAreaName(area.getName());
        }
        System.err.println(address);
        insertAddress(address);
    }


    /**
     * 查询用户的所有收货地址
     *
     * @param uid 用户uid
     * @return
     */
    @Override
    public List<Address> findAddressByUid(Integer uid) {
        return  selectAddressByUid(uid);
    }

    /**
     * @param aid
     */
    @Override
    @Transactional
    public void deleteAddressByAid(Integer aid, Integer uid, String username) {
        // 根据参数aid查询收货地址数据
        Address result = seltctAddressByAid(aid);

        // 判断查询结果中的uid与参数uid是否不匹配：AccessDeniedException
        if (!uid.equals(result.getUid())) {
            throw new AccessDeniedException("非法访问！");
        }
        // 执行删除
        deleteAddressByAid(aid);
        // 判断查询结果中的isDefault是否为0
        if (result.getIsDefault() == 0) {
            // 删除的不是默认收货地址，可直接结束：return;
            return;
        }
        // 调用countByUid(uid)统计当前用户的收货地址数量
        Integer countAddress = selectCountAddressByUid(uid);
        // 判断数量是否为0
        if (countAddress == 0) {
            // 当前用户已经没有收货地址，可直接结束：return;
            return;
        }
        // 调用selectLastModified(uid)找出最后修改的收货地址
        Address address = selectLastModified(uid);
        // 基于以上查询结果中的aid，将该收货地址设置为默认，并获取返回值
        updateDefaultByAid(address.getAid(), username, new Date());

    }

    @Override
    @Transactional
    //@Transactional事务注解，可添加在类之前
    // 当一个service方法涉及超过1个增、删、查操作时使用（如两个update、一个update和一个insert）
    public void updateDefault(Integer uid, Integer aid, String username) {
        //判断收货地址是不是存在
        Address address = seltctAddressByAid(aid);
        //判段收货地址是不是当前用户的
        if (!uid.equals(address.getUid())) {
            System.err.println("AddressServiceImpl.updateDefault()非法传参，用户和收货地址不匹配");
            throw new AccessDeniedException("非法访问，修改数据失败！");
        }
        //设置所有的地址为非默认收货地址
        updateNotDefaultByUid(uid);
        //设置默认收获地址
        updateDefaultByAid(aid, username, new Date());

    }

//私有方法区***********************************************************************************************************
// 查询区--------------------------------------------------------------------------------------------------------



    /**
     * 根据aid查找地址信息
     *
     * @param aid
     * @return
     */
    private Address seltctAddressByAid(Integer aid) {
        // 判断查询结果是否为null：AddressNotFoundException
        Address address = addressMapper.seltctAddressByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("该地址已被删除，请刷新页面");
        }
        return address;

    }

    /**
     * 根据用户id查找所有的用户收货地址
     *
     * @param uid
     * @return
     */
    private List<Address> selectAddressByUid(Integer uid) {
        List<Address> addresses = addressMapper.selectAddressByUid(uid);
        if (addresses == null) {
            throw new AddressNotFoundException("您还没有收获地址哦！赶快添加您的第一个收获地址把");
        }
        return addresses;
    }

    /**
     * 查询用户有多少条收获地址，目的是为了添加限制，收货地址最多不能超过10条
     *
     * @param uid 用户uid
     * @return 用户的收获地址数量
     */
    private Integer selectCountAddressByUid(Integer uid) {
        return addressMapper.selectCountAddressByUid(uid);
    }

    /**
     * 查询某用户最后修改的收货地址
     *
     * @param uid 用户的uid
     * @return 用户最后修改的收货地址信息
     */
    private Address selectLastModified(Integer uid) {
        return addressMapper.selectLastModified(uid);
    }
    //更新区域 update————————————————————————————————————————————————

    /**
     * 根据aid修改收货地址信息
     *
     * @param aid
     * @param address
     */
    private void updateAddressByAid(Integer aid, Address address) {
        Integer row = addressMapper.updateAddressByAid(aid, address);
        if (row != 1) {
            throw new UpdateException("修改收货地址时出现未知错误！");
        }
    }


    /**
     * 更改默认收货地址
     *
     * @param aid          收货地址aid
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    private void updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime) {
        Integer row = addressMapper.updateDefaultByAid(aid,modifiedUser,modifiedTime);
        if (row != 1) {
            throw new UpdateException("更新默认收货地址时出现未知错误！");
        }
    }

    /**
     * 把用户所有的收货地址设置为不是默认地址
     *
     * @param uid 用户的uid
     * @return
     */
    private void updateNotDefaultByUid(Integer uid) {
        Integer row = addressMapper.updateNotDefaultByUid(uid);
        if (row < 1) {
            throw new UpdateException("更新数据时出现未知错误！");
        }
    }
    //插入区域insert—————————————————————————————————————————————————

    /**
     * 插入收获地址信息
     *
     * @param address
     * @return
     */
    private void insertAddress(Address address) {
        Integer row = addressMapper.insertAddress(address);
        if (row != 1) {
            throw new UpdateException("添加收货地址时出现未知错误");
        }
    }

    //删除区域delete—————————————————————————————————————————————————

    /**
     * 根据aid删除收获地址信息
     *
     * @param aid
     * @return
     */
    private void deleteAddressByAid(Integer aid) {
        Integer row = addressMapper.deleteAddressByAid(aid);
        if (row != 1) {
            throw new UpdateException("删除收货地址时出现未知错误");
        }
    }
}
