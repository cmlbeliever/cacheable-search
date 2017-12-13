/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : jpa-w

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-11-10 14:21:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'user0', '96e79218965eb72c92a549dd5a330112', 'nickname0', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('2', 'user1', '96e79218965eb72c92a549dd5a330112', 'nickname1', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('3', 'user2', '96e79218965eb72c92a549dd5a330112', '被修改了咯', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('4', 'user3', '96e79218965eb72c92a549dd5a330112', 'nickname3', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('5', 'user4', '96e79218965eb72c92a549dd5a330112', 'nickname4', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('6', 'user5', '96e79218965eb72c92a549dd5a330112', 'nickname5', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('7', 'user6', '96e79218965eb72c92a549dd5a330112', 'nickname6', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('8', 'user7', '96e79218965eb72c92a549dd5a330112', 'nickname7', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('9', 'user8', '96e79218965eb72c92a549dd5a330112', 'nickname8', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('10', 'user9', '96e79218965eb72c92a549dd5a330112', 'nickname9', '2017-11-09 11:48:06', '2017-11-09 11:48:06');
INSERT INTO `t_user` VALUES ('11', 'user10', '96e79218965eb72c92a549dd5a330112', 'nickname10', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('12', 'user11', '96e79218965eb72c92a549dd5a330112', 'nickname11', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('13', 'user12', '96e79218965eb72c92a549dd5a330112', 'nickname12', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('14', 'user13', '96e79218965eb72c92a549dd5a330112', 'nickname13', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('15', 'user14', '96e79218965eb72c92a549dd5a330112', 'nickname14', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('16', 'user15', '96e79218965eb72c92a549dd5a330112', 'nickname15', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('17', 'user16', '96e79218965eb72c92a549dd5a330112', 'nickname16', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('18', 'user17', '96e79218965eb72c92a549dd5a330112', 'nickname17', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('19', 'user18', '96e79218965eb72c92a549dd5a330112', 'nickname18', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('20', 'user19', '96e79218965eb72c92a549dd5a330112', 'nickname19', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('21', 'user20', '96e79218965eb72c92a549dd5a330112', 'nickname20', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('22', 'user21', '96e79218965eb72c92a549dd5a330112', 'nickname21', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('23', 'user22', '96e79218965eb72c92a549dd5a330112', 'nickname22', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('24', 'user23', '96e79218965eb72c92a549dd5a330112', 'nickname23', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('25', 'user24', '96e79218965eb72c92a549dd5a330112', 'nickname24', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('26', 'user25', '96e79218965eb72c92a549dd5a330112', 'nickname25', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('27', 'user26', '96e79218965eb72c92a549dd5a330112', 'nickname26', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('28', 'user27', '96e79218965eb72c92a549dd5a330112', 'nickname27', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('29', 'user28', '96e79218965eb72c92a549dd5a330112', 'nickname28', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('30', 'user29', '96e79218965eb72c92a549dd5a330112', 'nickname29', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('31', 'user30', '96e79218965eb72c92a549dd5a330112', 'nickname30', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('32', 'user31', '96e79218965eb72c92a549dd5a330112', 'nickname31', '2017-11-09 11:48:07', '2017-11-09 11:48:07');
INSERT INTO `t_user` VALUES ('33', 'user32', '96e79218965eb72c92a549dd5a330112', 'nickname32', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('34', 'user33', '96e79218965eb72c92a549dd5a330112', 'nickname33', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('35', 'user34', '96e79218965eb72c92a549dd5a330112', 'nickname34', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('36', 'user35', '96e79218965eb72c92a549dd5a330112', 'nickname35', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('37', 'user36', '96e79218965eb72c92a549dd5a330112', 'nickname36', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('38', 'user37', '96e79218965eb72c92a549dd5a330112', 'nickname37', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('39', 'user38', '96e79218965eb72c92a549dd5a330112', 'nickname38', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('40', 'user39', '96e79218965eb72c92a549dd5a330112', 'nickname39', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('41', 'user40', '96e79218965eb72c92a549dd5a330112', 'nickname40', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('42', 'user41', '96e79218965eb72c92a549dd5a330112', 'nickname41', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('43', 'user42', '96e79218965eb72c92a549dd5a330112', 'nickname42', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('44', 'user43', '96e79218965eb72c92a549dd5a330112', 'nickname43', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('45', 'user44', '96e79218965eb72c92a549dd5a330112', 'nickname44', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('46', 'user45', '96e79218965eb72c92a549dd5a330112', 'nickname45', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('47', 'user46', '96e79218965eb72c92a549dd5a330112', 'nickname46', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('48', 'user47', '96e79218965eb72c92a549dd5a330112', 'nickname47', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('49', 'user48', '96e79218965eb72c92a549dd5a330112', 'nickname48', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
INSERT INTO `t_user` VALUES ('50', 'user49', '96e79218965eb72c92a549dd5a330112', 'nickname49', '2017-11-09 11:48:08', '2017-11-09 11:48:08');
