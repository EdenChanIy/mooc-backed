/*
Navicat MySQL Data Transfer

Source Server         : Admin
Source Server Version : 50721
Source Host           : 192.168.3.53:3306
Source Database       : muke

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-04-23 22:06:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '章节id',
  `no` tinyint(3) unsigned NOT NULL COMMENT '章节序号',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '章节名称',
  `intro` varchar(255) DEFAULT '' COMMENT '章节介绍',
  `cid` int(11) unsigned NOT NULL COMMENT '章节所属课程id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `cid_ibfk_1` (`cid`),
  CONSTRAINT `cid_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='所有章节信息表';

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1', '1', '章节1', 'aaaaa', '1', '2019-03-25 23:04:25');
INSERT INTO `chapter` VALUES ('2', '2', '章节2', 'bbbbb', '1', '2019-03-25 23:04:37');
INSERT INTO `chapter` VALUES ('3', '1', '章节1', 'ccccc', '2', '2019-03-25 23:04:58');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户收藏课程关系表',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL COMMENT '课程id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `collection_user_ibfk` (`user_id`),
  KEY `collection_course_ibfk` (`course_id`),
  CONSTRAINT `collection_course_ibfk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `collection_user_ibfk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户收藏课程关系表';

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('2', '1', '1', '2019-03-21 21:14:00');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '课程名称',
  `subtitle` varchar(10) DEFAULT '' COMMENT '课程副标题',
  `img` varchar(99) DEFAULT NULL COMMENT '课程图片url',
  `lector` int(11) NOT NULL DEFAULT '0' COMMENT '课程发布者id，0为未知作者',
  `rating` int(10) DEFAULT '-1' COMMENT '评分，-1表示没有评分',
  `category1` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '一级分类id,-1为未分类',
  `category2` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '二级分类id,-1为未分类',
  `need_to_know` varchar(255) DEFAULT '' COMMENT '课程须知（长度不超过255）',
  `learning_count` int(10) unsigned DEFAULT '0' COMMENT '学习人数',
  `total_time` time NOT NULL DEFAULT '00:00:00' COMMENT '课程总时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='课程信息表';

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'test', 'test', '192.168.2.202/img/afwet434235f.jpg', '1', '-1', '0', '0', 'none', '0', '00:00:00');
INSERT INTO `course` VALUES ('2', 'test2', '测试样例2', '192.168.2.202/img/afwet434235f.jpg', '0', '-1', '0', '0', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('3', 'test3', '', '192.168.2.202/img/afwet434235f.jpg', '0', '-1', '0', '1', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('7', 'test4', '', null, '0', '-1', '0', '2', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('8', 'test5', '', null, '0', '-1', '0', '3', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('9', 'test6', '', null, '0', '-1', '4', '4', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('10', 'test7', '', null, '0', '-1', '4', '5', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('11', 'test8', '', null, '0', '-1', '0', '2', '', '0', '00:00:00');
INSERT INTO `course` VALUES ('12', 'test9', '', null, '0', '-1', '0', '3', '', '0', '00:00:00');

-- ----------------------------
-- Table structure for discussion
-- ----------------------------
DROP TABLE IF EXISTS `discussion`;
CREATE TABLE `discussion` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '讨论id',
  `title` varchar(10) NOT NULL COMMENT '讨论标题（非空）',
  `reply_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '回复数',
  `like_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `view` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数',
  `author_id` int(11) unsigned NOT NULL COMMENT '讨论创建者id',
  `pid` int(11) unsigned NOT NULL COMMENT '创建讨论的课时id',
  `time` time NOT NULL DEFAULT '00:00:00' COMMENT '建立讨论的时间节点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `discussion_ibfk_1` (`author_id`),
  CONSTRAINT `discussion_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='讨论';

-- ----------------------------
-- Records of discussion
-- ----------------------------

-- ----------------------------
-- Table structure for interest
-- ----------------------------
DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '兴趣id',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '兴趣名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户兴趣表';

-- ----------------------------
-- Records of interest
-- ----------------------------
INSERT INTO `interest` VALUES ('1', '前端框架');
INSERT INTO `interest` VALUES ('2', '小程序');
INSERT INTO `interest` VALUES ('3', '语言基础');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '节点名',
  `pid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '节点所属课时',
  `time` time NOT NULL DEFAULT '00:00:00' COMMENT '节点对应时间戳',
  PRIMARY KEY (`id`),
  KEY `period_ibfk` (`pid`),
  CONSTRAINT `period_ibfk` FOREIGN KEY (`pid`) REFERENCES `period` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='节点';

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES ('1', '节点1', '1', '01:00:00');
INSERT INTO `node` VALUES ('2', '节点2', '1', '00:30:00');
INSERT INTO `node` VALUES ('3', '节点1', '3', '00:00:00');

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '笔记id',
  `title` varchar(20) NOT NULL DEFAULT '' COMMENT '笔记标题（长度不超过20）',
  `author_id` int(11) unsigned NOT NULL COMMENT '作者id',
  `like_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '收藏人数',
  `pid` int(11) unsigned NOT NULL COMMENT '课时id',
  `time` time NOT NULL DEFAULT '00:00:00' COMMENT '记录笔记的时间节点',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '笔记内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `note_ibfk_1` (`author_id`),
  KEY `note_ibfk_2` (`pid`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `note_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `period` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记';

-- ----------------------------
-- Records of note
-- ----------------------------

-- ----------------------------
-- Table structure for period
-- ----------------------------
DROP TABLE IF EXISTS `period`;
CREATE TABLE `period` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '课时id',
  `no` tinyint(3) unsigned NOT NULL COMMENT '课时序号',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '课时名称',
  `url` varchar(99) DEFAULT '' COMMENT '视频链接',
  `duration` time DEFAULT '00:00:00' COMMENT '视频时长',
  `cid` int(11) unsigned NOT NULL COMMENT '课时所属章节id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `cid_ibfk_2` (`cid`),
  CONSTRAINT `cid_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='所有课时信息表';

-- ----------------------------
-- Records of period
-- ----------------------------
INSERT INTO `period` VALUES ('1', '1', '课时1', 'www.1', '01:00:00', '1', '2019-03-25 23:05:21');
INSERT INTO `period` VALUES ('2', '2', '课时2', 'www.2', '00:06:00', '1', '2019-03-25 23:05:45');
INSERT INTO `period` VALUES ('3', '3', '课时3', '3', '00:25:00', '1', '2019-03-25 23:06:01');
INSERT INTO `period` VALUES ('4', '1', '课时1', '222', '02:00:00', '2', '2019-03-25 23:06:15');
INSERT INTO `period` VALUES ('5', '2', '课时2', '22222', '00:34:00', '2', '2019-03-25 23:06:43');
INSERT INTO `period` VALUES ('6', '1', '课时1', 'wraetg', '00:00:59', '3', '2019-03-25 23:07:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(13) DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(64) NOT NULL DEFAULT '123456' COMMENT '用户密码（默认123456）',
  `name` varchar(10) DEFAULT '' COMMENT '用户昵称',
  `icon` varchar(99) DEFAULT '' COMMENT '用户头像路径',
  `sex` tinyint(3) unsigned DEFAULT '1' COMMENT '用户性别，1-无，2-男，3-女',
  `job` varchar(10) DEFAULT '' COMMENT '用户职业',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（注册时间）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, '123', 'abc', '', '1', '', '2019-03-18 23:27:32');

-- ----------------------------
-- Table structure for user_course_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_course_rel`;
CREATE TABLE `user_course_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-课程关系id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL COMMENT '收藏课程id',
  `finished` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:未学习,1:未学习完,2:已学习完;',
  `chapter` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户最后停留章节id，0为未学习课程',
  `period` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户最后停留课时id，0为未学习课程',
  `time` time NOT NULL DEFAULT '00:00:00' COMMENT '用户最后离开视频的时间戳',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户最后停留时间（学习日期）',
  PRIMARY KEY (`id`),
  KEY `user_course_rel_ibfk_1` (`user_id`),
  KEY `user_course_rel_ibfk_2` (`course_id`),
  CONSTRAINT `user_course_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_course_rel_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户-课程关系表';

-- ----------------------------
-- Records of user_course_rel
-- ----------------------------
INSERT INTO `user_course_rel` VALUES ('1', '1', '1', '1', '1', '1', '03:28:00', '2019-03-21 23:03:19', '2019-03-21 23:03:19');

-- ----------------------------
-- Table structure for user_discussion_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_discussion_rel`;
CREATE TABLE `user_discussion_rel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户-讨论关系id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `discussion_id` int(11) unsigned NOT NULL COMMENT '讨论id',
  `liked` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否喜欢;1为喜欢',
  `is_followed` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否关注；1为关注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_discussion_rel
-- ----------------------------
INSERT INTO `user_discussion_rel` VALUES ('1', '1', '1', '1', '0');
INSERT INTO `user_discussion_rel` VALUES ('2', '2', '1', '0', '0');

-- ----------------------------
-- Table structure for user_interest_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_interest_rel`;
CREATE TABLE `user_interest_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-兴趣关系id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `interest_id` int(11) unsigned NOT NULL COMMENT '兴趣id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_interest_rel_ibfk_1` (`user_id`),
  KEY `user_interest_rel_ibfk_2` (`interest_id`),
  CONSTRAINT `user_interest_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户-兴趣关系表';

-- ----------------------------
-- Records of user_interest_rel
-- ----------------------------
INSERT INTO `user_interest_rel` VALUES ('1', '1', '1', '2019-03-20 23:30:49');
INSERT INTO `user_interest_rel` VALUES ('2', '1', '2', '2019-03-20 23:30:56');
INSERT INTO `user_interest_rel` VALUES ('3', '1', '3', '2019-03-20 23:31:03');

-- ----------------------------
-- Table structure for user_message_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_message_rel`;
CREATE TABLE `user_message_rel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户-消息关系id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `message_id` int(11) unsigned NOT NULL COMMENT '消息id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_message_rel_ibfk_1` (`user_id`),
  KEY `user_message_rel_ibfk_2` (`message_id`),
  CONSTRAINT `user_message_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_message_rel_ibfk_2` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-消息关系表';

-- ----------------------------
-- Records of user_message_rel
-- ----------------------------

-- ----------------------------
-- Table structure for user_period_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_period_rel`;
CREATE TABLE `user_period_rel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户-课时关系',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `period_id` int(11) unsigned NOT NULL COMMENT '课时id',
  `leave_time` time NOT NULL DEFAULT '00:00:00' COMMENT '用户上次离开课时的时间点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_period_rel_ibfk_1` (`user_id`),
  KEY `user_period_rel_ibfk_2` (`period_id`),
  CONSTRAINT `user_period_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_period_rel_ibfk_2` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户-课时关系表';

-- ----------------------------
-- Records of user_period_rel
-- ----------------------------
INSERT INTO `user_period_rel` VALUES ('1', '1', '1', '00:32:00', '2019-03-25 23:35:36', '2019-03-25 23:35:36');
