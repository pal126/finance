package com.pal.manager.service;

import com.pal.entity.Product;
import com.pal.entity.enums.ProductStatus;
import com.pal.manager.error.ErrorEnum;
import com.pal.manager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    public Product addProduct(Product product) {
        log.debug("创建产品param:{}", product.toString());
        //参数校验
        checkProduct(product);

        //设置默认值
        setDefault(product);
        Product result = productRepository.save(product);

        log.debug("创建产品result:{}", result.toString());
        return result;
    }

    /**
     * 查询单个产品
     *
     * @param id 产品编号
     * @return 返回对应的产品信息
     */
    public Product findOne(String id) {
        Assert.notNull(id, "产品编号不能为空");
        log.debug("查询单个产品,id:{}", id);

        Product product = productRepository.findById(id).orElse(null);

        log.debug("查询单个产品,result:{}", product.toString());

        return product;
    }

    /**
     * 分页查询产品
     *
     * @param idList
     * @param minRewardRate
     * @param maxRewardRate
     * @param statusList
     * @param pageable
     * @return
     */
    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate, BigDecimal maxRewardRate,
                               List<String> statusList,
                               Pageable pageable) {

        log.debug("分页查询产品,idList={},minRewardRate={},maxRewardRate={},statusList={},pageable={}"
                , idList.toString(), minRewardRate, maxRewardRate, statusList.toString(), pageable);

        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if (idList != null && idList.size() > 0) {
                    predicates.add(idCol.in(idList));
                }
                if (BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicates.add(criteriaBuilder.ge(rewardRateCol, minRewardRate));
                }
                if (BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicates.add(criteriaBuilder.ge(rewardRateCol, maxRewardRate));
                }
                if (statusList != null && statusList.size() > 0) {
                    predicates.add(statusCol.in(statusList));
                }
                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };

        Page<Product> page = productRepository.findAll(specification, pageable);

        log.debug("分页查询产品,result:{}", page.toString());
        return page;
    }

    /**
     * product参数校验
     *
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NOT_NULL.getCode());
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0
                && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, "收益率范围错误");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue())
                .compareTo(product.getStepAmount()) == 0, "投资步长需为整数");
    }

    /**
     * 设置默认值
     *
     * @param product
     */
    public void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }
}
