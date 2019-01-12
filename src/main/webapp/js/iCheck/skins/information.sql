/*
Navicat MySQL Data Transfer

Source Server         : abc
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : information

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-01-12 08:32:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单链接',
  `pid` int(6) DEFAULT '0' COMMENT '父级菜单id',
  `sort` int(4) NOT NULL COMMENT '菜单排序',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `flag` int(1) DEFAULT NULL COMMENT '0代表生效 1代表无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES ('1', '项目', '', '0', '1', 'fa fa-cogs', '1');
INSERT INTO `t_function` VALUES ('2', '项目', 'project/index', '1', '1', null, '1');
INSERT INTO `t_function` VALUES ('3', '项目类型', 'project/type', '1', '2', null, '1');
INSERT INTO `t_function` VALUES ('4', '客户', 'project/kehu', '1', '3', null, '1');
INSERT INTO `t_function` VALUES ('5', '供应商', 'project/pri', '1', '4', null, '1');
INSERT INTO `t_function` VALUES ('6', '项目发票', 'a/a', '1', '5', null, '1');
INSERT INTO `t_function` VALUES ('7', '成本', 'a/a', '1', '6', null, '1');
INSERT INTO `t_function` VALUES ('8', '员工', null, '0', '2', 'fa fa-cogs fa-cogs', '1');
INSERT INTO `t_function` VALUES ('9', '本部', 'user/index', '8', '1', null, '1');
INSERT INTO `t_function` VALUES ('10', '外包', 'out/index', '8', '2', null, '1');

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectname` varchar(20) DEFAULT NULL COMMENT '项目名称',
  `number` varchar(20) DEFAULT NULL COMMENT '项目编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('1', '项目名a', '01111111111');
INSERT INTO `t_project` VALUES ('2', '项目名1', '02');
INSERT INTO `t_project` VALUES ('3', '项目名2', '03');
INSERT INTO `t_project` VALUES ('4', '项目名3', '04');
INSERT INTO `t_project` VALUES ('5', '项目名4', '05');
INSERT INTO `t_project` VALUES ('6', '项目名5', '06');
INSERT INTO `t_project` VALUES ('7', '项目名6', '07');
INSERT INTO `t_project` VALUES ('8', '项目名7', '08');
INSERT INTO `t_project` VALUES ('9', '项目名8', '09');
INSERT INTO `t_project` VALUES ('12', '大哥', '11111111111');
INSERT INTO `t_project` VALUES ('14', '大飞哥', '11111111111');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `flag` varchar(255) DEFAULT NULL COMMENT '角色状态 0:注销 1:正常',
  `level` int(2) DEFAULT NULL COMMENT '1.系统管理员 2.管理员 3.员工 4.用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '1', '0');
INSERT INTO `t_role` VALUES ('2', '平台管理员', '1', '1');
INSERT INTO `t_role` VALUES ('3', '租户管理员', '1', '2');
INSERT INTO `t_role` VALUES ('4', '普通员工', '1', '3');

-- ----------------------------
-- Table structure for t_role_function
-- ----------------------------
DROP TABLE IF EXISTS `t_role_function`;
CREATE TABLE `t_role_function` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `roleid` int(6) DEFAULT NULL,
  `functionid` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_function
-- ----------------------------
INSERT INTO `t_role_function` VALUES ('1', '1', '1');
INSERT INTO `t_role_function` VALUES ('2', '1', '2');
INSERT INTO `t_role_function` VALUES ('3', '1', '3');
INSERT INTO `t_role_function` VALUES ('4', '1', '4');
INSERT INTO `t_role_function` VALUES ('5', '1', '5');
INSERT INTO `t_role_function` VALUES ('6', '1', '6');
INSERT INTO `t_role_function` VALUES ('7', '1', '7');
INSERT INTO `t_role_function` VALUES ('8', '1', '8');
INSERT INTO `t_role_function` VALUES ('9', '1', '9');
INSERT INTO `t_role_function` VALUES ('10', '1', '10');
INSERT INTO `t_role_function` VALUES ('11', '2', '1');
INSERT INTO `t_role_function` VALUES ('12', '2', '2');
INSERT INTO `t_role_function` VALUES ('13', '2', '3');
INSERT INTO `t_role_function` VALUES ('14', '2', '4');
INSERT INTO `t_role_function` VALUES ('15', '2', '5');
INSERT INTO `t_role_function` VALUES ('16', '2', '6');
INSERT INTO `t_role_function` VALUES ('17', '2', '7');
INSERT INTO `t_role_function` VALUES ('18', '3', '8');
INSERT INTO `t_role_function` VALUES ('19', '3', '9');
INSERT INTO `t_role_function` VALUES ('20', '3', '10');
INSERT INTO `t_role_function` VALUES ('33', '4', '1');
INSERT INTO `t_role_function` VALUES ('34', '4', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '用户姓名',
  `login_name` varchar(50) NOT NULL COMMENT '用户账号',
  `login_pwd` varchar(50) NOT NULL COMMENT '用户密码',
  `date` datetime DEFAULT NULL COMMENT '创建时间',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `sex` int(1) NOT NULL COMMENT '用户性别',
  `flag` int(1) DEFAULT NULL COMMENT '用户账号状态 0:注销 1:正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', 'admin', '2017-01-09 14:04:56', '1', '1333333333', '1', '1');
INSERT INTO `t_user` VALUES ('2', '测试1', '1', '1', '2018-01-10 14:03:43', '2', '13222222222', '1', '1');
INSERT INTO `t_user` VALUES ('4', '张三', 'zhangsan', 'zhangsan', '2018-01-09 14:54:09', '4', '13333333333', '0', '1');
INSERT INTO `t_user` VALUES ('5', '李四', 'lisi', 'lisi', '2018-01-09 15:09:30', '4', '13333333333', '1', '1');
