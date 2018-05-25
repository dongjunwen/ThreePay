/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/25 18:04:04                           */
/*==============================================================*/


drop index uniq_pay_way on CHANNEL_DETAIL;

drop table if exists CHANNEL_DETAIL;

drop table if exists CHANNEL_INFO;

drop table if exists MER_INFO;

drop index uniq_pay_seq on PAY_ORDER_DETAIL;

drop table if exists PAY_ORDER_DETAIL;

drop index uniq_trade_no on PAY_TRADE_TOTAL;

drop table if exists PAY_TRADE_TOTAL;

drop index uniq_index on mer_order;

drop table if exists mer_order;

drop table if exists mer_order_detail;

drop table if exists mer_product;

drop index uniq_index on mer_refund_order;

drop table if exists mer_refund_order;

drop table if exists pay_dict;

drop index uniq_refund_no on pay_refund_detail;

drop table if exists pay_refund_detail;

drop table if exists product_channel_route;

drop table if exists product_info;

/*==============================================================*/
/* Table: CHANNEL_DETAIL                                        */
/*==============================================================*/
create table CHANNEL_DETAIL
(
   id                   bigint not null auto_increment comment '主键id',
   pay_way              varchar(32) comment '支付方式',
   pay_way_name         varchar(64) comment '支付方式名称',
   channel_code         varchar(64) comment '渠道编号',
   sign_type            varchar(16) comment '加密方式',
   pub_key              varchar(4096) comment '公钥',
   priv_key             varchar(4096) comment '私钥',
   resv_key             varchar(4096) comment '保留key',
   resv1                varchar(1024) comment '保留域1',
   resv2                varchar(1024) comment '保留域2',
   channel_fee          decimal(16,2) default 0 comment '渠道方收取手续费',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table CHANNEL_DETAIL comment '渠道详情表';

/*==============================================================*/
/* Index: uniq_pay_way                                          */
/*==============================================================*/
create index uniq_pay_way on CHANNEL_DETAIL
(
   pay_way
);

/*==============================================================*/
/* Table: CHANNEL_INFO                                          */
/*==============================================================*/
create table CHANNEL_INFO
(
   id                   bigint not null auto_increment comment '主键id',
   channel_code         varchar(64) comment '渠道编号与付款方式对应',
   channel_name         varchar(64) comment '渠道名称 类似微信、支付宝',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table CHANNEL_INFO comment '渠道信息表';

/*==============================================================*/
/* Table: MER_INFO                                              */
/*==============================================================*/
create table MER_INFO
(
   id                   bigint not null auto_increment comment '主键id',
   mer_no               varchar(64) comment '商户编号',
   mer_name             varchar(64) comment '接入的商户',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table MER_INFO comment '商户信息表';

/*==============================================================*/
/* Table: PAY_ORDER_DETAIL                                      */
/*==============================================================*/
create table PAY_ORDER_DETAIL
(
   id                   bigint not null auto_increment comment '主键id',
   trade_no             varchar(64) comment '交易号',
   pay_time             datetime comment '交易日期',
   pay_seq_no           varchar(64) comment '付款流水号',
   pay_way              varchar(32) comment '付款方式',
   pay_status           int default 0 comment '付款状态 0:支付中 1:支付成功 2:支付失败 3:交易关闭',
   pay_success_time     datetime comment '付款成功时间',
   refund_onway_amt     decimal(16,2) default 0 comment '退款在途金额',
   refund_success_amt   decimal(16,2) default 0 comment '已退款金额',
   pay_amt              decimal(16,2) default 0 comment '付款金额',
   user_no              varchar(64) comment '用户编号(系统内部使用)',
   mer_no               varchar(64) comment '商户号',
   mer_name             varchar(64) comment '商户名称',
   channel_code         varchar(64) comment '渠道编号与付款方式对应',
   channel_name         varchar(64) comment '渠道名称 类似微信、支付宝',
   channel_partener_no  varchar(64) comment '渠道合作号',
   channel_patener_name varchar(64) comment '渠道合作名称',
   goods_name           varchar(64) comment '商品名称',
   goods_desc           varchar(128) comment '商品描述',
   currency_type        varchar(16) comment '币种',
   pay_fee              decimal(16,2) default 0 comment '手续费',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   payler_acct          varchar(64) comment '付款人账号',
   seller_acct          varchar(64) comment '收款人账号',
   equip_type           varchar(64) comment '设备类型',
   equip_no             varchar(64) comment '设备编号',
   equip_ip             varchar(32) comment '设备ip',
   once_str             varchar(128) comment '随机号',
   forward_url          varchar(128) comment '跳转地址',
   resp_pay_no          varchar(64) comment '第三方付款单号（通知回调）',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table PAY_ORDER_DETAIL comment '付款明细表';

/*==============================================================*/
/* Index: uniq_pay_seq                                          */
/*==============================================================*/
create unique index uniq_pay_seq on PAY_ORDER_DETAIL
(
   pay_seq_no
);

/*==============================================================*/
/* Table: PAY_TRADE_TOTAL                                       */
/*==============================================================*/
create table PAY_TRADE_TOTAL
(
   id                   bigint not null auto_increment comment '主键id',
   trade_no             varchar(64) comment '交易号',
   trade_time           datetime comment '交易日期',
   mer_no               varchar(64) comment '商户号',
   mer_name             varchar(64) comment '商户名称',
   user_no              varchar(64) comment '用户编号',
   trade_type           int default 0 comment '交易类型 0:付款 1:退款',
   trade_amt            decimal(16,2) comment '交易金额',
   act_trade_amt        decimal(16,2) default 0 comment '实际交易金额',
   trade_status         varchar(16) comment '交易状态 ',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table PAY_TRADE_TOTAL comment '交易汇总表';

/*==============================================================*/
/* Index: uniq_trade_no                                         */
/*==============================================================*/
create unique index uniq_trade_no on PAY_TRADE_TOTAL
(
   trade_no
);

/*==============================================================*/
/* Table: mer_order                                             */
/*==============================================================*/
create table mer_order
(
   id                   bigint not null auto_increment comment '主键id',
   pay_no               varchar(128) comment '付款唯一号=交易号+商户支付流水号',
   pay_time             datetime comment '付款日期',
   trade_no             varchar(64) comment '交易号',
   mer_no               varchar(64) comment '商户号',
   mer_name             varchar(64) comment '商户名称',
   mer_order_no         varchar(64) comment '商户订单号',
   mer_pay_seq          varchar(64) comment '商户支付流水号',
   product_no           varchar(32) comment '产品编码',
   pay_status           int default 0 comment '支付状态 0:支付中 1:支付成功 2:支付失败 3:交易关闭',
   pay_amt              decimal(16,2) default 0 comment '支付金额=订单金额-优惠金额',
   order_amt            decimal(16,2) default 0 comment '订单金额',
   discount_amt         decimal(16,2) default 0 comment '优惠金额',
   refund_amt           decimal(16,2) default 0 comment '已退款金额',
   user_no              varchar(64) comment '用户编号',
   equip_type           varchar(32) comment '设备类型',
   equip_no             varchar(32) comment '设备编号',
   equip_id             varchar(64) comment '设备ip',
   resv1                varchar(256) comment '保留域1',
   resv2                varchar(256) comment '保留域2',
   resv3                varchar(256) comment '保留域3',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table mer_order comment '商户订单表';

/*==============================================================*/
/* Index: uniq_index                                            */
/*==============================================================*/
create unique index uniq_index on mer_order
(
   pay_no
);

/*==============================================================*/
/* Table: mer_order_detail                                      */
/*==============================================================*/
create table mer_order_detail
(
   id                   bigint not null auto_increment comment '主键id',
   mer_order_no         varchar(64) comment '商户订单号',
   goods_id             varchar(64) comment '商品编号',
   goods_desc           varchar(256) comment '商品描述',
   goods_price          decimal(16,2) default 0 comment '商品单价',
   goods_unit           varchar(64) comment '商品单位',
   goods_amt            decimal(16,2) default 0 comment '商品金额',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table mer_order_detail comment '商户订单详情表';

/*==============================================================*/
/* Table: mer_product                                           */
/*==============================================================*/
create table mer_product
(
   id                   bigint not null auto_increment comment '主键id',
   mer_no               varchar(64) comment '商户编号',
   product_no           varchar(32) comment '产品编码',
   mer_fee              decimal(16,2) comment '商户手续费',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table mer_product comment '商户产品信息表';

/*==============================================================*/
/* Table: mer_refund_order                                      */
/*==============================================================*/
create table mer_refund_order
(
   id                   bigint not null auto_increment comment '主键id',
   refund_no            varchar(64) comment '退款订单号',
   refund_time          datetime comment '退款日期',
   pay_no               varchar(64) comment '付款唯一号(商户订单号+商户支付流水号联合查询出来)',
   trade_no             varchar(64) comment '交易号',
   mer_no               varchar(64) comment '商户号',
   mer_name             varchar(64) comment '商户名称',
   mer_order_no         varchar(64) comment '商户订单号',
   mer_pay_seq          varchar(64) comment '商户支付流水号',
   user_no              varchar(64) comment '用户编号',
   refund_way           varchar(32) comment '退款方式',
   refund_status        int default 0 comment '退款状态 0:退款中 1:退款成功 2:退款失败',
   refund_amt           decimal(16,2) comment '退款金额',
   refund_desc          varchar(128) comment '退款原因',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table mer_refund_order comment '退款收单表';

/*==============================================================*/
/* Index: uniq_index                                            */
/*==============================================================*/
create unique index uniq_index on mer_refund_order
(
   refund_no
);

/*==============================================================*/
/* Table: pay_dict                                              */
/*==============================================================*/
create table pay_dict
(
   id                   bigint not null auto_increment comment '主键id',
   dict_code            varchar(32) comment '字典代码',
   dict_value           varchar(64) comment '字典值',
   dict_name            varchar(64) comment '字典名称',
   dict_desc            varchar(128) comment '字典描述',
   pdict_code           varchar(32) comment '上级字典代码',
   status               char(1) comment '状态 Y:有效 N:无效',
   memo                 varchar(128) comment '备注',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table pay_dict comment '数据字典表';

/*==============================================================*/
/* Table: pay_refund_detail                                     */
/*==============================================================*/
create table pay_refund_detail
(
   id                   bigint not null auto_increment comment '主键id',
   refund_seq_no        varchar(64) comment '退款流水号',
   trade_no             varchar(64) comment '交易号',
   pay_seq_no           varchar(64) comment '付款流水号',
   user_no              varchar(64) comment '用户编号(系统内部使用)',
   refund_way           varchar(16) comment '退款方式',
   refund_amt           decimal(16,2) default 0 comment '退款金额',
   refund_status        int default 0 comment '退款状态  0:退款中 1:退款成功 2:退款失败',
   refund_desc          varchar(128) comment '退款原因',
   start_time           datetime comment '退款开始时间',
   end_time             datetime comment '退款结束时间',
   once_str             varchar(128) comment '随机号',
   forward_url          varchar(128) comment '跳转地址',
   resp_refund_no       varchar(64) comment '第三方退款单号（通知回调）',
   mer_no               varchar(64) comment '商户号',
   mer_name             varchar(64) comment '商户名称',
   channel_code         varchar(64) comment '渠道编号与付款方式对应',
   channel_name         varchar(64) comment '渠道名称 类似微信、支付宝',
   channel_partener_no  varchar(64) comment '渠道合作号',
   channel_patener_name varchar(64) comment '渠道合作名称',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table pay_refund_detail comment '退款明细表';

/*==============================================================*/
/* Index: uniq_refund_no                                        */
/*==============================================================*/
create unique index uniq_refund_no on pay_refund_detail
(
   refund_seq_no
);

/*==============================================================*/
/* Table: product_channel_route                                 */
/*==============================================================*/
create table product_channel_route
(
   id                   bigint not null auto_increment comment '主键id',
   product_no           varchar(32) comment '产品编码',
   pay_way              varchar(32) comment '支付方式',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   use_level            int default 1 comment '使用优先级 升序排列 类似1最大',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table product_channel_route comment '产品渠道路由表';

/*==============================================================*/
/* Table: product_info                                          */
/*==============================================================*/
create table product_info
(
   id                   bigint not null auto_increment comment '主键id',
   product_no           varchar(64) comment '产品编码',
   product_name         varchar(64) comment '产品名称',
   status               char(1) default 'Y' comment '状态 Y:有效 N:无效',
   version              int default 0 comment '版本号',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   modi_time            datetime default CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table product_info comment '产品信息表';

