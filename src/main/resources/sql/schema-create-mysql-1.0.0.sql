-- create database springboot_scaffolding DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- grant all privileges on `springboot_scaffolding`.* to `springboot_scaffolding`@`%` identified by 'qwe!@#123';
-- flush privileges;

-- 账户表
CREATE TABLE IF NOT EXISTS `account` (
  `id`    bigint(20) NOT NULL AUTO_INCREMENT,
  `name`  varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 DEFAULT COLLATE=utf8_unicode_ci;