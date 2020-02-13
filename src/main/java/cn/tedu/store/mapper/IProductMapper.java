package cn.tedu.store.mapper;

import cn.tedu.store.entity.Product;

import java.util.List;

/**
 *
 */
public interface IProductMapper {


    /**
     * 查询热销商品排行的前4个商品数据
     * @return 热销商品排行的前4个商品数据
     */
    List<Product> selectProductHotList();

    /**
     * 根据商品的id查询商品信息
     * @param id
     * @return
     */
    Product selectProductById(Integer id);

}
