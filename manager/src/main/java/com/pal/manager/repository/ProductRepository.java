package com.pal.manager.repository;

import com.pal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 产品管理
 *
 * @author pal
 */
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

}
