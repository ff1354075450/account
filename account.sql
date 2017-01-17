CREATE DATABASE IF NOT EXISTS account;
USE account;
CREATE TABLE user(
  id INT(10) NOT  NULL AUTO_INCREMENT COMMENT '标志用户的id',
  name VARCHAR(20) NOT NULL COMMENT '用户的姓名',
  table_pre VARCHAR(20) NOT NULL COMMENT '人员表名前缀',
  note TEXT COMMENT '备注',
  PRIMARY KEY (id)
)DEFAULT CHARSET=utf8 COMMENT='人员信息表';

CREATE TABLE work (
  `id` INT(10) NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '标志用户的id',
  `worker` VARCHAR(20) NOT NULL COMMENT '员工的姓名',
  `boss`  VARCHAR(20) NOT NULL COMMENT '老板的姓名',
  `date` TIMESTAMP(4) not null DEFAULT current_timestamp,
  `1` FLOAT(2,1),
  `2` FLOAT(2,1),
  `3` FLOAT(2,1),
  `4` FLOAT(2,1),
  `5` FLOAT(2,1),
  `6` FLOAT(2,1),
  `7` FLOAT(2,1),
  `8` FLOAT(2,1),
  `9` FLOAT(2,1),
  `10` FLOAT(2,1),
  `11` FLOAT(2,1),
  `12` FLOAT(2,1),
  `13` FLOAT(2,1),
  `14` FLOAT(2,1),
  `15` FLOAT(2,1),
  `16` FLOAT(2,1),
  `17` FLOAT(2,1),
  `18` FLOAT(2,1),
  `19` FLOAT(2,1),
  `20` FLOAT(2,1),
  `21` FLOAT(2,1),
  `22` FLOAT(2,1),
  `23` FLOAT(2,1),
  `24` FLOAT(2,1),
  `25` FLOAT(2,1),
  `26` FLOAT(2,1),
  `27` FLOAT(2,1),
  `28` FLOAT(2,1),
  `29` FLOAT(2,1),
  `30` FLOAT(2,1),
  `31` FLOAT(2,1),
  `note` TEXT COMMENT '记录异常情况，请著名日期什么事情'
);