/*
 Navicat Premium Data Transfer

 Source Server         : Web
 Source Server Type    : MySQL
 Source Server Version : 100406
 Source Host           : localhost:3306
 Source Schema         : web_thi_trac_nghiem

 Target Server Type    : MySQL
 Target Server Version : 100406
 File Encoding         : 65001

 Date: 21/06/2023 15:25:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Nội dung phần thi toán', 'Toán');
INSERT INTO `category` VALUES (2, 'Nội dung phần thi Vật lý', 'Vật lý');
INSERT INTO `category` VALUES (3, 'Nội dung phần thi Hóa học test', 'Hóa học');
INSERT INTO `category` VALUES (7, 'Danh sách đề thi môn hóa', 'Văn hóa');
INSERT INTO `category` VALUES (8, 'Công nghệ  Nhật Bản đến từ Châu Âu', 'Công nghệ');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `ques_id` bigint NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `option1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `option2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `option3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `option4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `quiz_q_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`ques_id`) USING BTREE,
  INDEX `FKq1xd7v9iuws36j2pb22my632e`(`quiz_q_id`) USING BTREE,
  CONSTRAINT `FKq1xd7v9iuws36j2pb22my632e` FOREIGN KEY (`quiz_q_id`) REFERENCES `quiz` (`q_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '2', '1 + 1 = ?', 'none', '2', '3', '4', '5', 5);
INSERT INTO `question` VALUES (5, '90', 'Góc vuông là góc bao nhiêu độ?', 'none', '90', '30', '60', '65', 5);
INSERT INTO `question` VALUES (6, '5', '25/5', 'none', '6', '7', '5', '1', 5);
INSERT INTO `question` VALUES (11, '12', '<p><strong>Các thông số cơ bản trong phép chia</strong></p><p><i>(chia các số thập phân với nhau)</i></p><ul><li>2</li><li>3</li><li>5</li><li>6</li><li>7</li><li>8</li></ul>', NULL, '12', '13', '14', '15', 6);
INSERT INTO `question` VALUES (12, '4', '<figure class=\"table\"><table><tbody><tr><td>1</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>4</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>3</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>4</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>5</td></tr></tbody></table></figure>', NULL, '1', '2', '3', '4', 6);
INSERT INTO `question` VALUES (15, '6m2', '<p>Từ trường xung quanh 1 cuộn dây dẫn dài 1m là</p>', NULL, '9m2', '6m2', '22m', '12m', 13);
INSERT INTO `question` VALUES (16, 'Tạo ra điện trở', '<p>Cuộn 1 dây dẫn điện thành vòng tròn sẽ xảy ra hiện tượng gì</p>', NULL, 'Không có hiện tượng gì', 'Tạo ra điện trở', 'Tạo ra lực hút kinh hoàng', 'Cả B và C', 13);
INSERT INTO `question` VALUES (17, 'Because i\'m from VietNam', '<blockquote><p>Why are u from?</p></blockquote><p><a href=\"facebook.com\">facebook.com</a></p><p>&nbsp;</p>', NULL, 'from VietNam', 'Because i ngu', 'Because i\'m from VietNam', 'none', 7);

-- ----------------------------
-- Table structure for quiz
-- ----------------------------
DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz`  (
  `q_id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `max_marks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `number_of_questions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `category_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`q_id`) USING BTREE,
  INDEX `FK82x9fxd5tsbb3i1ewrp3cr8xa`(`category_id`) USING BTREE,
  CONSTRAINT `FK82x9fxd5tsbb3i1ewrp3cr8xa` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quiz
-- ----------------------------
INSERT INTO `quiz` VALUES (5, b'0', 'Toán cơ bản giúp ôn lại kiến thức', '200', '20', 'Toán cơ bản là dễ', 3);
INSERT INTO `quiz` VALUES (6, b'1', 'Tính đóng gói cho phép che giấu thông tin và những tính chất xử lý bên trong của đối tượng. Các đối tượng khác không thể tác động trực tiếp đến dữ liệu bên trong và làm thay đổi trạng thái của đối tượng mà bắt buộc phải thông qua các phương thức công khai do đối tượng đó cung cấp.', '200', '20', 'Toán cơ bản', 3);
INSERT INTO `quiz` VALUES (7, b'1', 'Hóa cơ bản giúp ôn lại kiến thức', '200', '201', 'Hóa cơ bản dễ nè', 7);
INSERT INTO `quiz` VALUES (10, b'1', 'Sinh cơ bản giúp ôn lại kiến thức', '200', '20', 'Sinh cơ bản', 3);
INSERT INTO `quiz` VALUES (11, b'0', 'Sinh cơ bản giúp ôn lại kiến thức', '200', '20', 'Sinh cơ bản', 3);
INSERT INTO `quiz` VALUES (13, b'1', 'Ôn tập vật lý', '200', '10', 'Lý v1', 2);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `role_id` bigint NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (45, 'NORMAL');
INSERT INTO `roles` VALUES (46, 'ADMIN');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `marks` double NOT NULL,
  `number_of_question_attempted` int NOT NULL,
  `number_of_question_correct` int NOT NULL,
  `number_of_test` int NOT NULL,
  `time_end` datetime NULL DEFAULT NULL,
  `time_start` datetime NULL DEFAULT NULL,
  `quiz_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `exam_time` bigint NULL DEFAULT NULL,
  `is_end` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKlyijermk8u4365q8bfcu75n6u`(`quiz_id`) USING BTREE,
  INDEX `FKdug99c3uufjv7wedmlt9euews`(`user_id`) USING BTREE,
  CONSTRAINT `FKdug99c3uufjv7wedmlt9euews` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKlyijermk8u4365q8bfcu75n6u` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`q_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (27, 100, 2, 1, 0, '2023-06-09 06:09:26', '2023-06-09 06:05:25', 6, 7, 0, b'1');
INSERT INTO `test` VALUES (28, 100, 2, 1, 0, '2023-06-09 06:08:59', '2023-06-09 06:08:37', 6, 7, 0, b'1');
INSERT INTO `test` VALUES (29, 0, 2, 0, 0, '2023-06-09 06:14:53', '2023-06-09 06:14:36', 6, 7, 17, b'1');
INSERT INTO `test` VALUES (31, 0, 2, 0, 0, '2023-06-09 06:40:47', '2023-06-09 06:39:22', 6, 7, 85, b'1');
INSERT INTO `test` VALUES (32, 100, 2, 1, 0, '2023-06-09 06:40:47', '2023-06-09 06:39:22', 6, 5, 80, b'1');
INSERT INTO `test` VALUES (33, 1000, 2, 2, 0, '2023-06-09 06:40:47', '2023-06-09 06:39:22', 6, 6, 85, b'1');
INSERT INTO `test` VALUES (34, 200, 2, 2, 0, '2023-06-09 06:40:47', '2023-06-09 06:39:22', 6, 7, 0, b'1');
INSERT INTO `test` VALUES (35, 1000, 10, 10, 0, '2023-06-09 06:40:47', '2023-06-09 06:39:22', 6, 7, 100, b'1');
INSERT INTO `test` VALUES (36, 0, 2, 0, 0, '2023-06-21 07:43:58', '2023-06-21 07:43:22', 6, 7, 36, b'1');
INSERT INTO `test` VALUES (37, 0, 0, 0, 0, '2023-06-21 07:44:24', '2023-06-21 07:44:24', 6, 7, NULL, b'0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_role_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `FK7u21823ktfhu9bmx2350x6n8s`(`role_role_id`) USING BTREE,
  INDEX `FKj345gk1bovqvfame88rcx7yyx`(`user_id`) USING BTREE,
  CONSTRAINT `FK7u21823ktfhu9bmx2350x6n8s` FOREIGN KEY (`role_role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 45, 1);
INSERT INTO `user_role` VALUES (2, 45, 2);
INSERT INTO `user_role` VALUES (3, 45, 3);
INSERT INTO `user_role` VALUES (4, 45, 4);
INSERT INTO `user_role` VALUES (5, 45, 5);
INSERT INTO `user_role` VALUES (6, 46, 6);
INSERT INTO `user_role` VALUES (7, 45, 7);
INSERT INTO `user_role` VALUES (14, 46, 7);
INSERT INTO `user_role` VALUES (15, 46, 1);
INSERT INTO `user_role` VALUES (16, 46, 2);
INSERT INTO `user_role` VALUES (17, 46, 5);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'ducminh123', b'1', 'Nguyen', 'Nam', 'test', '01234567889', 'minh', 'test.jpg');
INSERT INTO `users` VALUES (2, '123@gmail.com', b'1', 'Tran ', 'Van', '123', '1322', '123', 'default.png');
INSERT INTO `users` VALUES (3, '1234@gmail.com', b'1', 'Le', 'Duc Minh', '123', '123123', 'minh1234', 'default.png');
INSERT INTO `users` VALUES (4, '23@gmail.com', b'1', '123', '123', '123', '3', '1234', 'default.png');
INSERT INTO `users` VALUES (5, 'ducminh0573@gmail.com', b'1', 'Le', 'Duc Minh', '$2a$10$XaE.QbVp/xff68i40M0owu8XrEQhak2BGBRUrmyB2wKyz1cDOhmdy', '346082073', 'minh123456', 'default.png');
INSERT INTO `users` VALUES (6, '19130133@st.hcmuaf.edu.vn', b'1', 'Le', 'Duc Minh', '$2a$10$cj8jdRAx0mv8VERO/kmUee2KVQOGs7Tk3IzUzD5.zD44spkEtodx6', '0346082073', 'leb', 'default.png');
INSERT INTO `users` VALUES (7, 'ducminh0573@gmail.com', b'1', 'Lê', 'Đức Minh', '$2a$10$0bYogLqrxoX61z6kTT4c8uBCoOMKK4OU44dVOm/xP9n22iQfummLS', '0346082073', 'user', 'default.png');

SET FOREIGN_KEY_CHECKS = 1;
