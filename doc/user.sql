DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `user` VALUES ('82bf87e6-5ac3-42b0-92fc-360e71e05f6b', 'user1', '12312341234', '6b7b839377d1e083df26e11ccf6677bbf30a2008', '2017-11-02 12:04:27');
INSERT INTO `user` VALUES ('b6d382c5-6996-4c7a-aab6-e5de37c16f74', 'user2', '45667896789', '7c4a8d09ca3762af61e59520943dc26494f8941b', '2017-12-13 17:07:27');
