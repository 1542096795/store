package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.IProductMapper;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理产品数据的业务层处理类
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private  IProductMapper productMapper;

    @Override
    public List<Product> findProductHotList() {
        return selectProductHotList();
    }

    @Override
    public Product findProductById(Integer id) {
        return selectProductById(id);
    }

    private List<Product> selectProductHotList() {
        return productMapper.selectProductHotList();
    }

    private Product selectProductById(Integer id) {
        Product product = productMapper.selectProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("该商品已经下架！");
        }
        return product;

    }
}
