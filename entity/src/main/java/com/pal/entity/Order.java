package com.pal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * @author pal
 */
@Getter
@Setter
@ToString
@Entity(name = "order_t")
public class Order {

    @Id
    private String orderId;

    private String chanId;

    private String chanUserId;

    private String orderType;

    private String productId;

    private BigDecimal amount;

    private String outerOrderId;

    private String orderStatus;

    private String memo;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updateAt;

}
