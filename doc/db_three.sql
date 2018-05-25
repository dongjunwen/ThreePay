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
   id                   bigint not null auto_increment comment '����id',
   pay_way              varchar(32) comment '֧����ʽ',
   pay_way_name         varchar(64) comment '֧����ʽ����',
   channel_code         varchar(64) comment '�������',
   sign_type            varchar(16) comment '���ܷ�ʽ',
   pub_key              varchar(4096) comment '��Կ',
   priv_key             varchar(4096) comment '˽Կ',
   resv_key             varchar(4096) comment '����key',
   resv1                varchar(1024) comment '������1',
   resv2                varchar(1024) comment '������2',
   channel_fee          decimal(16,2) default 0 comment '��������ȡ������',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table CHANNEL_DETAIL comment '���������';

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
   id                   bigint not null auto_increment comment '����id',
   channel_code         varchar(64) comment '��������븶�ʽ��Ӧ',
   channel_name         varchar(64) comment '�������� ����΢�š�֧����',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table CHANNEL_INFO comment '������Ϣ��';

/*==============================================================*/
/* Table: MER_INFO                                              */
/*==============================================================*/
create table MER_INFO
(
   id                   bigint not null auto_increment comment '����id',
   mer_no               varchar(64) comment '�̻����',
   mer_name             varchar(64) comment '������̻�',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table MER_INFO comment '�̻���Ϣ��';

/*==============================================================*/
/* Table: PAY_ORDER_DETAIL                                      */
/*==============================================================*/
create table PAY_ORDER_DETAIL
(
   id                   bigint not null auto_increment comment '����id',
   trade_no             varchar(64) comment '���׺�',
   pay_time             datetime comment '��������',
   pay_seq_no           varchar(64) comment '������ˮ��',
   pay_way              varchar(32) comment '���ʽ',
   pay_status           int default 0 comment '����״̬ 0:֧���� 1:֧���ɹ� 2:֧��ʧ�� 3:���׹ر�',
   pay_success_time     datetime comment '����ɹ�ʱ��',
   refund_onway_amt     decimal(16,2) default 0 comment '�˿���;���',
   refund_success_amt   decimal(16,2) default 0 comment '���˿���',
   pay_amt              decimal(16,2) default 0 comment '������',
   user_no              varchar(64) comment '�û����(ϵͳ�ڲ�ʹ��)',
   mer_no               varchar(64) comment '�̻���',
   mer_name             varchar(64) comment '�̻�����',
   channel_code         varchar(64) comment '��������븶�ʽ��Ӧ',
   channel_name         varchar(64) comment '�������� ����΢�š�֧����',
   channel_partener_no  varchar(64) comment '����������',
   channel_patener_name varchar(64) comment '������������',
   goods_name           varchar(64) comment '��Ʒ����',
   goods_desc           varchar(128) comment '��Ʒ����',
   currency_type        varchar(16) comment '����',
   pay_fee              decimal(16,2) default 0 comment '������',
   start_time           datetime comment '��ʼʱ��',
   end_time             datetime comment '����ʱ��',
   payler_acct          varchar(64) comment '�������˺�',
   seller_acct          varchar(64) comment '�տ����˺�',
   equip_type           varchar(64) comment '�豸����',
   equip_no             varchar(64) comment '�豸���',
   equip_ip             varchar(32) comment '�豸ip',
   once_str             varchar(128) comment '�����',
   forward_url          varchar(128) comment '��ת��ַ',
   resp_pay_no          varchar(64) comment '����������ţ�֪ͨ�ص���',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table PAY_ORDER_DETAIL comment '������ϸ��';

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
   id                   bigint not null auto_increment comment '����id',
   trade_no             varchar(64) comment '���׺�',
   trade_time           datetime comment '��������',
   mer_no               varchar(64) comment '�̻���',
   mer_name             varchar(64) comment '�̻�����',
   user_no              varchar(64) comment '�û����',
   trade_type           int default 0 comment '�������� 0:���� 1:�˿�',
   trade_amt            decimal(16,2) comment '���׽��',
   act_trade_amt        decimal(16,2) default 0 comment 'ʵ�ʽ��׽��',
   trade_status         varchar(16) comment '����״̬ ',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table PAY_TRADE_TOTAL comment '���׻��ܱ�';

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
   id                   bigint not null auto_increment comment '����id',
   pay_no               varchar(128) comment '����Ψһ��=���׺�+�̻�֧����ˮ��',
   pay_time             datetime comment '��������',
   trade_no             varchar(64) comment '���׺�',
   mer_no               varchar(64) comment '�̻���',
   mer_name             varchar(64) comment '�̻�����',
   mer_order_no         varchar(64) comment '�̻�������',
   mer_pay_seq          varchar(64) comment '�̻�֧����ˮ��',
   product_no           varchar(32) comment '��Ʒ����',
   pay_status           int default 0 comment '֧��״̬ 0:֧���� 1:֧���ɹ� 2:֧��ʧ�� 3:���׹ر�',
   pay_amt              decimal(16,2) default 0 comment '֧�����=�������-�Żݽ��',
   order_amt            decimal(16,2) default 0 comment '�������',
   discount_amt         decimal(16,2) default 0 comment '�Żݽ��',
   refund_amt           decimal(16,2) default 0 comment '���˿���',
   user_no              varchar(64) comment '�û����',
   equip_type           varchar(32) comment '�豸����',
   equip_no             varchar(32) comment '�豸���',
   equip_id             varchar(64) comment '�豸ip',
   resv1                varchar(256) comment '������1',
   resv2                varchar(256) comment '������2',
   resv3                varchar(256) comment '������3',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table mer_order comment '�̻�������';

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
   id                   bigint not null auto_increment comment '����id',
   mer_order_no         varchar(64) comment '�̻�������',
   goods_id             varchar(64) comment '��Ʒ���',
   goods_desc           varchar(256) comment '��Ʒ����',
   goods_price          decimal(16,2) default 0 comment '��Ʒ����',
   goods_unit           varchar(64) comment '��Ʒ��λ',
   goods_amt            decimal(16,2) default 0 comment '��Ʒ���',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table mer_order_detail comment '�̻����������';

/*==============================================================*/
/* Table: mer_product                                           */
/*==============================================================*/
create table mer_product
(
   id                   bigint not null auto_increment comment '����id',
   mer_no               varchar(64) comment '�̻����',
   product_no           varchar(32) comment '��Ʒ����',
   mer_fee              decimal(16,2) comment '�̻�������',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table mer_product comment '�̻���Ʒ��Ϣ��';

/*==============================================================*/
/* Table: mer_refund_order                                      */
/*==============================================================*/
create table mer_refund_order
(
   id                   bigint not null auto_increment comment '����id',
   refund_no            varchar(64) comment '�˿����',
   refund_time          datetime comment '�˿�����',
   pay_no               varchar(64) comment '����Ψһ��(�̻�������+�̻�֧����ˮ�����ϲ�ѯ����)',
   trade_no             varchar(64) comment '���׺�',
   mer_no               varchar(64) comment '�̻���',
   mer_name             varchar(64) comment '�̻�����',
   mer_order_no         varchar(64) comment '�̻�������',
   mer_pay_seq          varchar(64) comment '�̻�֧����ˮ��',
   user_no              varchar(64) comment '�û����',
   refund_way           varchar(32) comment '�˿ʽ',
   refund_status        int default 0 comment '�˿�״̬ 0:�˿��� 1:�˿�ɹ� 2:�˿�ʧ��',
   refund_amt           decimal(16,2) comment '�˿���',
   refund_desc          varchar(128) comment '�˿�ԭ��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table mer_refund_order comment '�˿��յ���';

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
   id                   bigint not null auto_increment comment '����id',
   dict_code            varchar(32) comment '�ֵ����',
   dict_value           varchar(64) comment '�ֵ�ֵ',
   dict_name            varchar(64) comment '�ֵ�����',
   dict_desc            varchar(128) comment '�ֵ�����',
   pdict_code           varchar(32) comment '�ϼ��ֵ����',
   status               char(1) comment '״̬ Y:��Ч N:��Ч',
   memo                 varchar(128) comment '��ע',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table pay_dict comment '�����ֵ��';

/*==============================================================*/
/* Table: pay_refund_detail                                     */
/*==============================================================*/
create table pay_refund_detail
(
   id                   bigint not null auto_increment comment '����id',
   refund_seq_no        varchar(64) comment '�˿���ˮ��',
   trade_no             varchar(64) comment '���׺�',
   pay_seq_no           varchar(64) comment '������ˮ��',
   user_no              varchar(64) comment '�û����(ϵͳ�ڲ�ʹ��)',
   refund_way           varchar(16) comment '�˿ʽ',
   refund_amt           decimal(16,2) default 0 comment '�˿���',
   refund_status        int default 0 comment '�˿�״̬  0:�˿��� 1:�˿�ɹ� 2:�˿�ʧ��',
   refund_desc          varchar(128) comment '�˿�ԭ��',
   start_time           datetime comment '�˿ʼʱ��',
   end_time             datetime comment '�˿����ʱ��',
   once_str             varchar(128) comment '�����',
   forward_url          varchar(128) comment '��ת��ַ',
   resp_refund_no       varchar(64) comment '�������˿�ţ�֪ͨ�ص���',
   mer_no               varchar(64) comment '�̻���',
   mer_name             varchar(64) comment '�̻�����',
   channel_code         varchar(64) comment '��������븶�ʽ��Ӧ',
   channel_name         varchar(64) comment '�������� ����΢�š�֧����',
   channel_partener_no  varchar(64) comment '����������',
   channel_patener_name varchar(64) comment '������������',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table pay_refund_detail comment '�˿���ϸ��';

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
   id                   bigint not null auto_increment comment '����id',
   product_no           varchar(32) comment '��Ʒ����',
   pay_way              varchar(32) comment '֧����ʽ',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   use_level            int default 1 comment 'ʹ�����ȼ� �������� ����1���',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table product_channel_route comment '��Ʒ����·�ɱ�';

/*==============================================================*/
/* Table: product_info                                          */
/*==============================================================*/
create table product_info
(
   id                   bigint not null auto_increment comment '����id',
   product_no           varchar(64) comment '��Ʒ����',
   product_name         varchar(64) comment '��Ʒ����',
   status               char(1) default 'Y' comment '״̬ Y:��Ч N:��Ч',
   version              int default 0 comment '�汾��',
   create_time          datetime default CURRENT_TIMESTAMP comment '����ʱ��',
   modi_time            datetime default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (id)
);

alter table product_info comment '��Ʒ��Ϣ��';

