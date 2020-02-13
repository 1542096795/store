package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

    @Autowired
    IAddressService addressService;

    @Test
    public void addNewAddress() {
        System.out.println("开始测试");
        String username = "张洋洋";
        Integer uid = 36;
        Address address = new Address();
        address.setUid(36);
        address.setProvinceName("江西省");
        address.setCityName("南昌市");
        address.setAreaName("高新开发区");
        address.setAddress("京东大道918号");
        address.setReceiver("张洋洋");
        address.setPhone("13767973981");
        addressService.addNewAddress(address,uid,username);
        System.out.println("测试结束");
    }

    @Test
    public void updateDefault(){
        System.err.println("开始测试");
        Integer  aid = 23;
        Integer uid = 36;
        String  username= "张洋洋";
        addressService.updateDefault(uid,aid,username);
        System.err.println("测试结束");
    }

    @Test
    public void deleteByAid(){
        System.err.println("开始测试");
        Integer  aid = 23;
        Integer uid = 36;
        String  username= "张洋洋";
        addressService.deleteAddressByAid(aid,uid,username);
        System.err.println("测试结束");
    }
}
