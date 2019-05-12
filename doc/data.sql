/*
 Navicat Premium Data Transfer

 Source Server         : 10.100.1.211
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 10.100.1.211:3306
 Source Schema         : smartlearning_workflow

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 10/05/2019 23:32:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `product_item`;
CREATE TABLE `product_item` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `rank` int(255) DEFAULT NULL COMMENT '排序',
  `item_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分类名',
  `item_img` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分类图片',
  `item_parent` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '父级分类',
  `item_declare` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分类描述',
  `is_root` tinyint(4) DEFAULT NULL COMMENT '是否是根分类',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '产品名',
  `type` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '类型',
  `price` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '价格',
  `sales` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '销量',
  `amount` varchar(255) CHARACTER SET utf8mb4 NULL COMMENT '库存量',
  `measure` varchar(255) CHARACTER SET utf8mb4 NULL COMMENT '材质',
  `texture` varchar(255) CHARACTER SET utf8mb4 NULL COMMENT '说明',
  `product_declare` varchar(255) CHARACTER SET utf8mb4 NULL COMMENT '描述',
  `img_urls` varchar(1000) CHARACTER SET utf8mb4 NULL COMMENT '图片',
  `taobao_link` varchar(255) CHARACTER SET utf8mb4 NULL COMMENT '淘宝链接',
  `item_id` varchar(1000) CHARACTER SET utf8mb4 NULL COMMENT '所属分类',
  `labels` varchar(1000) CHARACTER SET utf8mb4 NULL COMMENT '标签',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `project_label`;
CREATE TABLE `project_label` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '标签名',
  `label_code` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '标签代码',
  `label_declare` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '标签描述',
  `label_img` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '标签图片',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `project_news`;
CREATE TABLE `project_news`  (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `stye` varchar(25) CHARACTER SET utf8mb4 NULL  COMMENT '类型',
  `title` varchar(500) CHARACTER SET utf8mb4 NULL COMMENT '标题',
  `author` varchar(25) CHARACTER SET utf8mb4 NULL  COMMENT '作者',
  `back_img` varchar(255) CHARACTER SET utf8mb4 NULL  COMMENT '背景图',
  `intro` varchar(500) CHARACTER SET utf8mb4  COMMENT '简介',
  `subject` longtext CHARACTER SET utf8mb4  COMMENT '文章内容',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 NULL  COMMENT '用户名',
  `password` varchar(25) CHARACTER SET utf8mb4 NULL COMMENT '用户密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 NULL  COMMENT '用户手机号',
  `email` varchar(25) CHARACTER SET utf8mb4 NULL  COMMENT '用户邮箱',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `project_designer`;
CREATE TABLE `project_designer`  (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 NULL  COMMENT '设计师名称',
  `sex` varchar(10) CHARACTER SET utf8mb4 NULL COMMENT '性别',
  `age` varchar(5) CHARACTER SET utf8mb4 NULL  COMMENT '年龄',
  `introduce` varchar(500) CHARACTER SET utf8mb4 NULL  COMMENT '个人介绍',
  `sampleReels` longtext CHARACTER SET utf8mb4  COMMENT '作品集',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `project_common`;
CREATE TABLE `project_common`  (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `intro` varchar(500) CHARACTER SET utf8mb4 NULL  COMMENT '内容介绍',
    `module` varchar(10) CHARACTER SET utf8mb4 NOT NULL COMMENT '类型',
	`keyword` varchar(500) CHARACTER SET utf8mb4 NULL  COMMENT '搜索关键字',
  `content` longtext CHARACTER SET utf8mb4  COMMENT '内容',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `create_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
