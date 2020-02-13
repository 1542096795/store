package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IAddressMapperTest {

    @Autowired
    IAddressMapper IAddressMapper;

    @Test
    public void seltctAddressByAid() {
        System.err.println("开始测试");
        Address address = IAddressMapper.seltctAddressByAid(10);
        System.err.println(address);
        System.err.println("测试结束");
    }

    @Test
    public void seltctAddressByUid() {
        System.err.println("开始测试");
        List<Address> addresss = IAddressMapper.selectAddressByUid(36);
        for (Address address : addresss) {
            System.err.println(address);
        }
        System.err.println("测试结束");
    }

    @Test
    public void selectLastModified() {
        System.err.println("开始测试");
        Address address = IAddressMapper.selectLastModified(36);
        System.err.println(address);
        System.err.println("测试结束");
    }

    @Test
    public void selectCountAddressByUid() {
        System.err.println("开始测试");
        Integer rows = IAddressMapper.selectCountAddressByUid(36);
        System.err.println("收获地址数量 ： " + rows);
        System.err.println("测试结束");
    }

    @Test
    public void insertAddress() {
        System.err.println("开始测试");
        Address address = new Address();
        address.setUid(36);
        address.setProvinceName("江西省");
        address.setCityName("南昌市");
        address.setAreaName("高新开发区");
        address.setAddress("京东大道918号");
        address.setReceiver("张洋洋");
        address.setPhone("13767973981");
        Integer rows = IAddressMapper.insertAddress(address);
        System.err.println("受影响的行数" + rows);
        System.err.println("测试结束");
    }

    @Test
    public void deleteByAid() {
        System.err.println("开始测试");
        Integer aid = 32;
        Integer rows = IAddressMapper.deleteAddressByAid(aid);
        System.err.println("受影响的行数" + rows);
        System.err.println("测试结束");

    }

    @Test
    public void updateDefaultByAid(){
        System.err.println("开始测试");
        Integer  aid = 31;
        Integer uid = 36;
        String  modifiedUser= "张洋洋";
        Date modifiedTime = new Date();
        Integer rows1 = IAddressMapper.updateNotDefaultByUid(uid);
        Integer  rows2 = IAddressMapper.updateDefaultByAid(aid,modifiedUser,modifiedTime);
        System.err.println("受影响的行数rows1 ：" + rows1);
        System.err.println("受影响的行数rows2 ：" + rows2);
        System.err.println("测试结束");
    }

}