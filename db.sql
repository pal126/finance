CREATE TABLE product(
		id VARCHAR(50) NOT NULL COMMENT '产品编号',
		name VARCHAR(50) NOT NULL COMMENT '产品名称',
		threshold_amount DECIMAL(15,3) NOT NULL COMMENT '起投金额',
		step_amount DECIMAL(15,3) NOT NULL COMMENT '投资步长',
		lock_term SMALLINT NOT NULL COMMENT '锁定期',
		reward_rate DECIMAL(5,3) NOT NULL COMMENT '收益率',
		status VARCHAR(20) NOT NULL COMMENT '状态,AUDINTING:审核中;IN_SELL:销售中;LOCKED:暂停销售;FINISHED:已结束',
		memo VARCHAR(200) COMMENT '备注',
		create_at DATETIME COMMENT '创建时间',
		create_user VARCHAR(20) COMMENT '创建者',
		update_at DATETIME COMMENT '更新时间',
		update_user VARCHAR(20) COMMENT '更新者',
		PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE order_t(
		order_id VARCHAR(50) NOT NULL COMMENT '订单编号',
		chan_id VARCHAR(50) NOT NULL COMMENT '渠道编号',
		product_id VARCHAR(50) NOT NULL COMMENT '产品编号',
		chan_user_id VARCHAR(50) NOT NULL COMMENT '渠道用户编号',
		order_type VARCHAR(50) NOT NULL COMMENT '类型,APPLY:申购;REDEEM:赎回',
		order_status VARCHAR(50) NOT NULL COMMENT '状态,INIT:初始化;PROCESS:处理中;SUCCESS:成功;FAIL:失败',
		outer_order_id VARCHAR(50) NOT NULL COMMENT '外部订单编号',
		amount DECIMAL(15,3) NOT NULL COMMENT '金额',
		memo VARCHAR(200) COMMENT '备注',
		create_at DATETIME COMMENT '创建时间',
		update_at DATETIME COMMENT '更新时间',
		PRIMARY KEY(order_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;