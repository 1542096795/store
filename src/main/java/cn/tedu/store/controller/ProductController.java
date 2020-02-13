package cn.tedu.store.controller;

import cn.tedu.store.entity.Product;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private  IProductService productService;

    @GetMapping("hot_list")
    public JsonResult<List<Product>> findProductHotList() {
        List<Product> products = productService.findProductHotList();
        return new JsonResult<>(SUCCESS, products);
    }

    @GetMapping("/{id}/details")
    public JsonResult<Product> findProductById(@PathVariable("id") Integer id) {
        System.err.println("ProductController.findProductById()");
        Product product = productService.findProductById(id);
        return new JsonResult<>(SUCCESS, product);
    }
}
