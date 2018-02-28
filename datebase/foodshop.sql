/*
Navicat MySQL Data Transfer

Source Server         : hyk
Source Server Version : 50717
Source Host           : 119.29.142.195:3306
Source Database       : cakeshop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-28 20:47:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `addressId` varchar(40) NOT NULL COMMENT 'guid',
  `userId` varchar(40) NOT NULL COMMENT '用户账号（外键）',
  `userName` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `userTel` varchar(15) DEFAULT NULL COMMENT '收货电话',
  `address` varchar(100) NOT NULL COMMENT '收货地址',
  PRIMARY KEY (`addressId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `cartId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(15) NOT NULL COMMENT '用户账号（外键）',
  `goodsId` varchar(40) NOT NULL COMMENT '商品id',
  `buyNum` int(11) NOT NULL COMMENT '欲购买数量',
  `addTime` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`cartId`),
  KEY `userIdInCart` (`userId`),
  CONSTRAINT `userIdInCart` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `goodsId` varchar(40) NOT NULL COMMENT '商品guid（外键）',
  `userId` varchar(15) NOT NULL COMMENT '用户账号',
  `comment` varchar(100) DEFAULT NULL COMMENT '评论',
  `score` smallint(6) DEFAULT NULL COMMENT '评分总五分',
  `time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`commentId`),
  KEY `goodsIdInComment` (`goodsId`),
  CONSTRAINT `goodsIdInComment` FOREIGN KEY (`goodsId`) REFERENCES `t_goods` (`goodsId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `goodsId` varchar(40) NOT NULL COMMENT '商品guid',
  `goodsName` varchar(40) DEFAULT NULL COMMENT '商品名',
  `goodsImg` varchar(50) DEFAULT NULL COMMENT '展示于主页的图',
  `goodsPrice` decimal(8,2) DEFAULT NULL COMMENT '价格',
  `goodsNum` int(11) DEFAULT NULL COMMENT '库存数量',
  `salesNum` int(11) DEFAULT NULL COMMENT '销售数',
  `goodsSize` varchar(20) DEFAULT NULL COMMENT '商品规格',
  `goodsFrom` varchar(50) DEFAULT NULL COMMENT '商品产地',
  `goodsTime` varchar(10) DEFAULT NULL COMMENT '保质期',
  `goodsSaveCondition` varchar(20) DEFAULT NULL COMMENT '存储条件',
  `goodsDescribe` varchar(100) DEFAULT NULL COMMENT '商品描述介绍',
  `goodsExplain` varchar(50) DEFAULT NULL COMMENT '对商品简短说明',
  `goodsClass` varchar(20) DEFAULT NULL COMMENT '所属类别',
  `goodsDiscount` decimal(5,2) DEFAULT NULL COMMENT '折扣',
  `discountStartTime` datetime DEFAULT NULL COMMENT '优惠起始时间',
  `discountEndTime` datetime DEFAULT NULL COMMENT '优惠截止时间',
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_goods_img
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_img`;
CREATE TABLE `t_goods_img` (
  `goodsImgId` int(11) NOT NULL AUTO_INCREMENT,
  `goodsId` varchar(255) NOT NULL COMMENT '商品guid',
  `goodsImgUrl` varchar(50) NOT NULL,
  `goodsImgType` int(11) NOT NULL COMMENT 'img类型，0展示于详情，1展示于介绍',
  PRIMARY KEY (`goodsImgId`),
  KEY `goodsId` (`goodsId`),
  CONSTRAINT `goodsId` FOREIGN KEY (`goodsId`) REFERENCES `t_goods` (`goodsId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_goodsClass
-- ----------------------------
DROP TABLE IF EXISTS `t_goodsClass`;
CREATE TABLE `t_goodsClass` (
  `classNum` int(11) NOT NULL AUTO_INCREMENT,
  `goodsClass` varchar(255) NOT NULL,
  PRIMARY KEY (`classNum`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_goodsFrom
-- ----------------------------
DROP TABLE IF EXISTS `t_goodsFrom`;
CREATE TABLE `t_goodsFrom` (
  `addressId` smallint(6) NOT NULL AUTO_INCREMENT,
  `goodsFrom` varchar(20) NOT NULL COMMENT '商品产地',
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderId` varchar(40) NOT NULL COMMENT '订单编号guid',
  `userId` varchar(15) NOT NULL COMMENT '付款账号',
  `orderTime` datetime NOT NULL COMMENT '订单时间',
  `addressId` varchar(40) NOT NULL COMMENT '订单地址',
  PRIMARY KEY (`orderId`),
  KEY `userIdInOrder` (`userId`),
  CONSTRAINT `userIdInOrder` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order_child
-- ----------------------------
DROP TABLE IF EXISTS `t_order_child`;
CREATE TABLE `t_order_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(40) NOT NULL COMMENT '订单编号（外键）',
  `goodsId` varchar(40) NOT NULL COMMENT '商品id',
  `buyNum` int(11) NOT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `orderId` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`orderId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` varchar(15) NOT NULL COMMENT '账号，电话',
  `userName` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `userHeadImg` varchar(50) DEFAULT 'default.png' COMMENT '用户头像img',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
