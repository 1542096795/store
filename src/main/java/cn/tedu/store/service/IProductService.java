package cn.tedu.store.service;

import cn.tedu.store.entity.Product;

import java.util.List;

/**
 * 处理产品数据的业务层处理接口
 */
public interface IProductService {

    /**
     * 查询热销商品排行的前4个商品数据
     * @return 热销商品排行的前4个商品数据
     */
    List<Product> findProductHotList();

    /**
     *根据id查询产品信息
     */
    Product findProductById(Integer id);

}
