CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8;
USE `test`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(255) NOT NULL,
                         `second_name` varchar(255) NOT NULL,
                         `phone_number` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `app` varchar(255) NOT NULL,
                         `date_record` date NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `phone_number` (`phone_number`),
                         UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci