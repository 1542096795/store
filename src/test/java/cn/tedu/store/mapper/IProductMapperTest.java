package cn.tedu.store.mapper;

import cn.tedu.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductMapperTest {

    @Autowired
    IProductMapper productMapper;

    @Test
    public void selectProductHotList() {
        System.err.println("开始测试");
        List<Product> products = productMapper.selectProductHotList();
        for (Product product : products) {
            System.err.println(product);
        }
        System.err.println("测试结束");
    }

    @Test
    public void selectProductById() {
        System.err.println("开始测试");
        Product products = productMapper.selectProductById(10000001);
        System.err.println(products);
        System.err.println("测试结束");
    }
}
