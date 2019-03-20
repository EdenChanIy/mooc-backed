/*
Navicat MySQL Data Transfer

Source Server         : Admin
Source Server Version : 50721
Source Host           : 192.168.1.105:3306
Source Database       : muke

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-18 23:43:31
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
  `cid` int(11) unsigned NOT NULL COMMENT '章节所属课程id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `cid_ibfk_1` (`cid`),
  CONSTRAINT `cid_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所有章节信息表';

-- ----------------------------
-- Records of chapter
-- ----------------------------

-- ----------------------------
-- Table structure for collections
-- ----------------------------
DROP TABLE IF EXISTS `collections`;
CREATE TABLE `collections` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户收藏课程关系表',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL COMMENT '课程id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `collection_user_ibfk` (`user_id`),
  KEY `collection_course_ibfk` (`course_id`),
  CONSTRAINT `collection_course_ibfk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `collection_user_ibfk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏课程关系表';

-- ----------------------------
-- Records of collections
-- ----------------------------

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
  `category1` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '一级分类id,0为未分类',
  `category2` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '二级分类id,0为未分类',
  `need_to_know` varchar(255) DEFAULT '' COMMENT '课程须知（长度不超过255）',
  `learning_count` int(10) unsigned DEFAULT '0' COMMENT '学习人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程信息表';

-- ----------------------------
-- Records of course
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户兴趣表';

-- ----------------------------
-- Records of interest
-- ----------------------------

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
  `cid` int(11) unsigned NOT NULL COMMENT '课时所属章节id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `cid_ibfk_2` (`cid`),
  CONSTRAINT `cid_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所有课时信息表';

-- ----------------------------
-- Records of period
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-课程关系表';

-- ----------------------------
-- Records of user_course_rel
-- ----------------------------

-- ----------------------------
-- Table structure for user_interest_id
-- ----------------------------
DROP TABLE IF EXISTS `user_interest_id`;
CREATE TABLE `user_interest_id` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-兴趣关系id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `interest_id` int(11) unsigned NOT NULL COMMENT '兴趣id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_interest_rel_ibfk_1` (`user_id`),
  KEY `user_interest_rel_ibfk_2` (`interest_id`),
  CONSTRAINT `user_interest_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_interest_rel_ibfk_2` FOREIGN KEY (`interest_id`) REFERENCES `interest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-兴趣关系表';

-- ----------------------------
-- Records of user_interest_id
-- ----------------------------

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
