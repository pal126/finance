package com.pal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品
 * @author pal
 */
@Getter
@Setter
@ToString
@Entity
public class Product {

    @Id
    private String id;

    private String name;

    private String status;
    /**
     * 起投金额
     */
    private BigDecimal thresholdAmount;
    /**
     * 投资步长
     */
    private BigDecimal stepAmount;
    /**
     * 锁定期
     */
    private Integer lockTerm;
    /**
     * 收益率,要和其他参数相乘
     */
    private BigDecimal rewardRate;

    private String memo;

    private Date createAt;

    private Date updateAt;

    private String createUser;

    private String updateUser;

}
