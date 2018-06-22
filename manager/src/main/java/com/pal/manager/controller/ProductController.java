package com.pal.manager.controller;

import com.pal.entity.Product;
import com.pal.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 产品管理
 *
 * @author pal
 */
@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        log.info("创建产品param:{}", product.toString());
        Product result = productService.addProduct(product);
        log.info("创建产品result:{}", result.toString());
        return result;
    }

    /**
     * 查询单个产品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product findOne(@PathVariable String id) {
        log.info("查询单个产品,id:{}", id);

        Product product = productService.findOne(id);

        log.info("查询单个产品,result:{}", product.toString());
        return product;
    }

    /**
     * 分页查询产品
     *
     * @param ids
     * @param minRewardRate
     * @param maxRewardRate
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Page<Product> query(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, String status,
                               @RequestParam(defaultValue = "0") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        log.info("分页查询产品,ids={},minRewardRate={},maxRewardRate={},status={},pageNum={},pageSize={}"
                , ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);

        List<String> idList = null, statusList = null;
        if (StringUtils.isNotEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (StringUtils.isNotEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);

        log.info("分页查询产品,result:{}", page);
        return page;
    }
}
