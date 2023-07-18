# ALTER TABLE account ADD COLUMN `phone` varchar(13) NOT NULL DEFAULT '' COMMENT '电话' AFTER COLUMN `email`;
select count(1) from `account`;