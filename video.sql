/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : video

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 29/01/2022 02:14:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '后台管理主键',
  `a_username` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员用户名',
  `a_password` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'toor');

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection`  (
  `collection_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `video_id` bigint(11) NOT NULL,
  PRIMARY KEY (`collection_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collection
-- ----------------------------
INSERT INTO `t_collection` VALUES (1, 1, 2);
INSERT INTO `t_collection` VALUES (2, 1, 3);

-- ----------------------------
-- Table structure for t_commentedstar
-- ----------------------------
DROP TABLE IF EXISTS `t_commentedstar`;
CREATE TABLE `t_commentedstar`  (
  `commentedstar_id` bigint(22) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(22) NULL DEFAULT NULL,
  `video_id` bigint(22) NULL DEFAULT NULL,
  `star_num` bigint(22) NULL DEFAULT NULL,
  `comment_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`commentedstar_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_commentedstar
-- ----------------------------
INSERT INTO `t_commentedstar` VALUES (1, 1, 1, 3, '2021-04-30 19:39:23');

-- ----------------------------
-- Table structure for t_focus
-- ----------------------------
DROP TABLE IF EXISTS `t_focus`;
CREATE TABLE `t_focus`  (
  `focus_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NULL DEFAULT NULL,
  `focused_id` bigint(11) NULL DEFAULT NULL,
  PRIMARY KEY (`focus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_focus
-- ----------------------------
INSERT INTO `t_focus` VALUES (1, 1, 2);
INSERT INTO `t_focus` VALUES (2, 1, 3);
INSERT INTO `t_focus` VALUES (3, 1, 4);
INSERT INTO `t_focus` VALUES (4, 3, 1);

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `msg_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `msg_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg_context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg_send_date` datetime(0) NULL DEFAULT NULL,
  `msg_send_user_id` bigint(11) NULL DEFAULT NULL,
  `msg_receive_user_id` bigint(11) NULL DEFAULT NULL,
  `msg_state_id` bigint(11) NULL DEFAULT NULL,
  `msgtype_id` bigint(11) NULL DEFAULT NULL,
  PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (1, '点赞提醒', '你好! 你的视频《霸王别姬》的视频获得用户【请填入你的昵称】的一枚点赞', '2021-04-30 18:52:42', NULL, 1, 6, 3);
INSERT INTO `t_message` VALUES (2, '点赞提醒', '你好! 你的视频《霸王别姬》的视频获得用户【请填入你的昵称】的一枚点赞', '2021-04-30 18:58:40', NULL, 1, 6, 3);
INSERT INTO `t_message` VALUES (3, '收藏提醒', '你好！你的主题为《霸王别姬》的视频,被用户【hujian】的收藏', '2021-05-04 18:23:22', NULL, 1, 7, 5);
INSERT INTO `t_message` VALUES (4, '系统提醒', '你好！你的主题为《我不是药神》的视频因违反规定已被下架', '2021-05-24 13:12:00', NULL, 2, 6, 2);
INSERT INTO `t_message` VALUES (5, '系统提醒', '你好！你的主题为《我不是药神》的视频审核通过', '2021-05-25 15:27:07', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (6, '系统提醒', '你好！你的主题为《霸王别姬》的视频因违反规定已被下架', '2021-05-25 15:27:11', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (7, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 16:47:25', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (8, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:47:30', NULL, 1, 7, 2);
INSERT INTO `t_message` VALUES (9, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:48:35', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (10, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:49:08', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (11, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:52:09', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (12, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:13', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (13, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:15', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (14, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:16', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (15, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:16', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (16, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:16', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (17, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:17', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (18, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:53:17', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (19, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:54:29', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (20, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 16:59:35', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (21, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:01:49', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (22, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:01:53', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (23, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:05:32', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (24, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:05:36', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (25, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:07:05', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (26, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:07:09', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (27, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:33:47', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (28, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:33:50', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (29, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:40:38', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (30, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:40:42', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (31, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:43:01', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (32, '系统提醒', '你好！你的主题为《无间道》的视频审核失败', '2021-05-26 17:43:06', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (33, '系统提醒', '你好！你的主题为《无间道》的视频因违反规定已被下架', '2021-05-26 17:47:00', NULL, 1, 7, 2);
INSERT INTO `t_message` VALUES (35, 'Hello', 'Hello，你视频真好看', '2021-05-31 18:09:28', 1, 2, 6, 1);
INSERT INTO `t_message` VALUES (36, '你好', '我对你的视频很敢兴趣', '2021-06-02 14:09:29', 1, 1, 6, 1);
INSERT INTO `t_message` VALUES (37, '系统提醒', '你好！你的主题为《肖生克的救赎》的视频因违反规定已被下架', '2021-06-08 11:03:05', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (38, '系统提醒', '你好！你的主题为《肖生克的救赎》的视频审核失败', '2021-06-08 11:03:12', NULL, 1, 6, 2);
INSERT INTO `t_message` VALUES (39, '点赞提醒', '你好! 你的视频《霸王别姬》的视频获得用户【胡建】的一枚点赞', '2021-06-08 14:05:15', NULL, 1, 6, NULL);
INSERT INTO `t_message` VALUES (40, '系统提醒', '你好！你的主题为《肖生克的救赎》的视频审核通过', '2021-06-08 14:20:07', NULL, 1, 6, 2);

-- ----------------------------
-- Table structure for t_msgtype
-- ----------------------------
DROP TABLE IF EXISTS `t_msgtype`;
CREATE TABLE `t_msgtype`  (
  `msgtype_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `msgtype_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`msgtype_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_msgtype
-- ----------------------------
INSERT INTO `t_msgtype` VALUES (1, 'PrivateMsg');
INSERT INTO `t_msgtype` VALUES (2, 'SystemMsg');
INSERT INTO `t_msgtype` VALUES (3, 'SuportNotice');
INSERT INTO `t_msgtype` VALUES (4, 'EvaluateNotice');
INSERT INTO `t_msgtype` VALUES (5, 'CollectionNotice');

-- ----------------------------
-- Table structure for t_predict
-- ----------------------------
DROP TABLE IF EXISTS `t_predict`;
CREATE TABLE `t_predict`  (
  `pre_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NULL DEFAULT NULL,
  `video_id` bigint(11) NULL DEFAULT NULL,
  `pre_star` float(255, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`pre_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_predict
-- ----------------------------
INSERT INTO `t_predict` VALUES (1, 1, 1, 2.58);
INSERT INTO `t_predict` VALUES (2, 1, 2, 4.00);
INSERT INTO `t_predict` VALUES (3, 1, 3, 2.34);
INSERT INTO `t_predict` VALUES (4, 1, 4, 0.88);
INSERT INTO `t_predict` VALUES (5, 1, 5, 3.44);
INSERT INTO `t_predict` VALUES (6, 1, 6, 2.68);
INSERT INTO `t_predict` VALUES (7, 1, 7, 3.03);
INSERT INTO `t_predict` VALUES (8, 1, 8, 3.22);
INSERT INTO `t_predict` VALUES (9, 1, 9, 3.80);
INSERT INTO `t_predict` VALUES (10, 1, 10, 2.21);
INSERT INTO `t_predict` VALUES (11, 1, 11, 1.20);
INSERT INTO `t_predict` VALUES (12, 1, 12, 1.40);

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record`  (
  `record_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NULL DEFAULT NULL,
  `video_id` bigint(11) NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES (1, 1, 1);
INSERT INTO `t_record` VALUES (2, 2, 1);
INSERT INTO `t_record` VALUES (3, 1, 6);
INSERT INTO `t_record` VALUES (4, 1, 5);
INSERT INTO `t_record` VALUES (5, 1, 3);
INSERT INTO `t_record` VALUES (6, 1, 4);
INSERT INTO `t_record` VALUES (7, 3, 6);
INSERT INTO `t_record` VALUES (8, 5, 1);
INSERT INTO `t_record` VALUES (9, 3, 4);
INSERT INTO `t_record` VALUES (10, 3, 1);
INSERT INTO `t_record` VALUES (11, 3, 40);

-- ----------------------------
-- Table structure for t_state
-- ----------------------------
DROP TABLE IF EXISTS `t_state`;
CREATE TABLE `t_state`  (
  `state_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`state_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_state
-- ----------------------------
INSERT INTO `t_state` VALUES (1, '认证中');
INSERT INTO `t_state` VALUES (2, '已认证');
INSERT INTO `t_state` VALUES (3, '认证失败');
INSERT INTO `t_state` VALUES (4, '已上架');
INSERT INTO `t_state` VALUES (5, '已下架');
INSERT INTO `t_state` VALUES (6, '未读');
INSERT INTO `t_state` VALUES (7, '已读');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_age` int(11) NULL DEFAULT NULL,
  `user_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `encrypted_problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fan_num` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '粉丝数量',
  `user_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_date` datetime(0) NULL DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'jianqian', 11, '男', 'springboot', '123456', 00000000000, '15566666626', '2021-04-17 15:17:10', '/user/getIcon/icon1.jpg', 1);
INSERT INTO `t_user` VALUES (2, '请填入你的昵称', 11, '女', '西游记', '123456', 00000000000, '15566663000', '2021-04-23 16:49:52', '/user/getIcon/icon2.png', 1);
INSERT INTO `t_user` VALUES (3, '小周', 18, '女', '水浒传', '123456', 00000000000, '15566663001', '2021-04-24 16:32:18', '/user/getIcon/icon3.png', 2);
INSERT INTO `t_user` VALUES (4, '小明', 18, '男', '红楼梦', '123456', 00000000000, '12234567891', '2021-05-28 23:47:29', '/user/getIcon/icon1.png', 2);
INSERT INTO `t_user` VALUES (5, '小红', 22, '女', '活着', '123456', 00000000000, '15566661234', '2021-05-19 18:12:57', '/user/getIcon/icon1.jpg', 1);
INSERT INTO `t_user` VALUES (8, '小艾', 13, '男', 'Java', '123456', 00000000000, '12345678915', '2021-05-28 23:52:58', NULL, 1);
INSERT INTO `t_user` VALUES (9, '小台', 16, '女', 'C++', '123456', 00000000000, '12345678999', '2021-05-28 23:55:06', NULL, 1);
INSERT INTO `t_user` VALUES (10, 'hello', 18, '女', '从java入门到放弃', '123456', 00000000000, '15566666625', '2021-05-29 14:40:58', '/user/getIcon/icon10.jpg', 1);
INSERT INTO `t_user` VALUES (11, '请填入你的昵称', NULL, NULL, 'python', '123456', 00000000000, '15566663006', '2021-06-08 10:50:58', NULL, 1);

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video`  (
  `video_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `video_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `edit_date` datetime(0) NULL DEFAULT NULL,
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `thunmbnail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `video_state_id` bigint(11) NULL DEFAULT NULL,
  `view_num` int(11) NULL DEFAULT NULL,
  `pp_num` int(11) NULL DEFAULT NULL,
  `video_type_id` bigint(11) NULL DEFAULT NULL,
  `user_id` bigint(11) NULL DEFAULT NULL,
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES (1, '霸王别姬', '段小楼（张丰毅）与程蝶衣（张国荣）是一对打小一起长大的师兄弟，两人一个演生，一个饰旦，一向配合天衣无缝，尤其一出《霸王别姬》，更是誉满京城，为此，两人约定合演一辈子《霸王别姬》。但两人对戏剧与人生关系的理解有本质不同，段小楼深知戏非人生，程蝶衣则是人戏不分。', '2021-04-26 19:59:12', '/static/video/bwbj.mp4', '/video/getVideoImage/video1.png', 4, 140, 8, 1, 1);
INSERT INTO `t_video` VALUES (2, '我不是药神', '普通中年男子程勇（徐峥 饰）经营着一家保健品店，失意又失婚。不速之客吕受益（王传君 饰）的到来，让他开辟了一条去印度买药做“代购”的新事业，虽然困难重重，但他在这条“买药之路”上发现了商机，一发不可收拾地做起了治疗慢粒白血病的印度仿制药独家代理商。赚钱的同时，他也认识了几个病患及家属，为救女儿被迫做舞女的思慧（谭卓 饰）、说一口流利“神父腔”英语的刘牧师（杨新鸣 饰），以及脾气暴烈的“黄毛”（章宇 饰），几个人合伙做起了生意，利润倍增的同时也危机四伏。程勇昔日的小舅子曹警官（周一围 饰）奉命调查仿制药的源', '2021-04-28 18:56:16', '/static/video/wbsys.mp4', '/video/getVideoImage/video2.png', 4, 0, 1, 9, 1);
INSERT INTO `t_video` VALUES (3, '西虹市首富', '西虹市丙级球队大翔队的守门员王多鱼（沈腾 饰）因比赛失利被教练开除，一筹莫展之际王多鱼突然收到神秘人士金老板（张晨光 饰）的邀请，被告知自己竟然是保险大亨王老太爷（李立群 饰）的唯一继承人，遗产高达百亿！但是王老太爷给出了一个非常奇葩的条件，那就是要求王多鱼在一个月内花光十亿，还不能告诉身边人，否则失去继承权。王多鱼毫不犹豫签下了“军令状”，与好友庄强（张一鸣 饰）以及财务夏竹（宋芸桦 饰）一起开启了“挥金之旅”，即将成为西虹市首富的王多鱼，第一次感受到了做富人的快乐，同时也发现想要挥金如土实在没有那么简', '2021-04-28 19:02:37', '/static/video/xhssf.mp4', '/video/getVideoImage/video3.png', 4, 1, 1, 1, 2);
INSERT INTO `t_video` VALUES (4, '你好,李焕英', '2001年的某一天，刚刚考上大学的贾晓玲（贾玲 饰）经历了人生中的一次大起大落。一心想要成为母亲骄傲的她却因母亲突遭严重意外，而悲痛万分。在贾晓玲情绪崩溃的状态下，竟意外的回到了1981年，并与年轻的母亲李焕英（张小斐 饰）相遇，二人形影不离，宛如闺蜜。与此同时，也结识了一群天真善良的好朋友。晓玲以为来到了这片“广阔天地”，她可以凭借自己超前的思维，让母亲“大有作为”，但结果却让晓玲感到意外......', '2021-04-28 19:04:55', '/static/video/nhlhy.mp4', '/video/getVideoImage/video4.png', 4, 18, 1, 1, 2);
INSERT INTO `t_video` VALUES (5, '大话西游之大圣娶亲', '至尊宝（周星驰 饰）被月光宝盒带回到五百年前，遇见紫霞仙子（朱茵 饰），被对方打上烙印成为对方的人，并发觉自己已变成孙悟空。', '2021-04-28 19:08:16', '/static/video/dhxyzdsqq.mp4', '/video/getVideoImage/video5.png', 4, 2, 1, 1, 2);
INSERT INTO `t_video` VALUES (6, '少年的你', '陈念（周冬雨 饰）是一名即将参加高考的高三学生，同校女生胡晓蝶（张艺凡 饰）的跳楼自杀让她的生活陷入了困顿之中。胡晓蝶死后，陈念遭到了以魏莱（周也 饰）为首的三人组的霸凌，魏莱虽然表面上看来是乖巧的优等生，实际上却心思毒辣，胡晓蝶的死和她有着千丝万缕的联系。', '2021-05-26 15:19:40', '/static/video/sndn.mp4', '/video/getVideoImage/video6.png', 4, 4, 0, 1, 2);
INSERT INTO `t_video` VALUES (7, '活着', '根据余华同名小说改编。', '2021-04-28 19:12:24', '/static/video/hz.mp4', '/video/getVideoImage/video7.png', 4, 0, 1, 1, 2);
INSERT INTO `t_video` VALUES (8, '让子弹飞', '555', '2021-05-19 18:55:36', '/static/video/20210519185611.mp4', '/video/getVideoImage/video8.png', 5, 0, 0, 1, 2);
INSERT INTO `t_video` VALUES (9, '美食总动员', '小老鼠雷米在嗅觉方面有着无与伦比的天赋，不想过与垃圾堆为伴的生活，心怀成为五星大厨的梦想。', '2021-05-27 13:40:57', NULL, '/video/getVideoImage/video9.png', 4, 0, 0, 4, 3);
INSERT INTO `t_video` VALUES (10, '音乐', '高中生健治有一天突发奇想，邀请伙伴太田和朝仓一起组乐队，但是三个人都对乐器一窍不通。他们的乐队是一把贝斯，再来一把贝斯，还有鼓，配置十分独特。当他们弹奏出第一个音符，立刻被一种前所未有的感觉淹没了，音乐震撼了他们。', '2021-05-27 13:46:55', NULL, '/video/getVideoImage/video10.png', 4, 0, 0, 5, 3);
INSERT INTO `t_video` VALUES (11, '别运...别运...', '惊叹!别运...别运...诶...别!惊叹!惊叹!惊叹!', '2021-05-27 13:59:22', '/static/video/byby.mp4', '/video/getVideoImage/video11.png', 4, 0, 0, 11, 2);
INSERT INTO `t_video` VALUES (12, '更好的生活', 'Carlos Galindo（德米安·比齐尔 Demián Bichir 饰）是一个从墨西哥偷渡到美国的无户籍人士。老婆早已离他而去，如今的他和14岁的儿子Luis Galindo相依为命。儿子在学校因为自己的特殊身份遭受同学的歧视和欺负，但他总是沉默对待父亲的询问，隔阂在两人间越来越深。', '2021-05-27 14:58:27', NULL, '/video/getVideoImage/video12.png', 4, 0, 0, 3, 1);
INSERT INTO `t_video` VALUES (26, '忠犬八公的故事', '八公（Forest 饰）是一条谜一样的犬，因为没有人知道它从哪里来。教授帕克（理查·基尔 Richard Gere 饰）在小镇的火车站拣到一只走失的小狗，冥冥中似乎注定小狗和帕克教授有着某种缘分，帕克一抱起这只小狗就再也放不下来，最终，帕克对小狗八公的疼爱感化了起初极力反对养狗的妻子卡特', '2021-05-26 15:28:39', '/static/video/20210526152900.mp4', '/video/getVideoImage/video26.png', 4, 0, 0, 1, 2);
INSERT INTO `t_video` VALUES (36, '无间道', '无间道', '2021-05-26 17:47:21', '/static/video/20210526174758.mp4', '/video/getVideoImage/video36.png', 4, 0, 0, 1, 1);
INSERT INTO `t_video` VALUES (40, '肖生克的救赎', '肖生克的救赎', '2021-06-08 14:06:06', '/static/video/20210608140844.mp4', '/video/getVideoImage/video40.png', 4, 1, 0, 1, 1);

-- ----------------------------
-- Table structure for t_videotype
-- ----------------------------
DROP TABLE IF EXISTS `t_videotype`;
CREATE TABLE `t_videotype`  (
  `videotype_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`videotype_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_videotype
-- ----------------------------
INSERT INTO `t_videotype` VALUES (1, '影视');
INSERT INTO `t_videotype` VALUES (2, '新闻');
INSERT INTO `t_videotype` VALUES (3, '生活');
INSERT INTO `t_videotype` VALUES (4, '美食');
INSERT INTO `t_videotype` VALUES (5, '音乐');
INSERT INTO `t_videotype` VALUES (6, '电视剧');
INSERT INTO `t_videotype` VALUES (7, '舞蹈');
INSERT INTO `t_videotype` VALUES (8, '动漫');
INSERT INTO `t_videotype` VALUES (9, '娱乐');
INSERT INTO `t_videotype` VALUES (10, '科技数码');
INSERT INTO `t_videotype` VALUES (11, '体育');
INSERT INTO `t_videotype` VALUES (12, '美妆');

SET FOREIGN_KEY_CHECKS = 1;
