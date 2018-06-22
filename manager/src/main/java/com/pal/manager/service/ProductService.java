package com.pal.manager.service;

import com.pal.entity.Product;
import com.pal.manager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品服务类
 *
 * @author pal
 */
@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return null;
    }

}
