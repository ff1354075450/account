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
  `date` VARCHAR(8) not null,
  `day1` FLOAT(2,1),
  `day2` FLOAT(2,1),
  `day3` FLOAT(2,1),
  `day4` FLOAT(2,1),
  `day5` FLOAT(2,1),
  `day6` FLOAT(2,1),
  `day7` FLOAT(2,1),
  `day8` FLOAT(2,1),
  `day9` FLOAT(2,1),
  `day10` FLOAT(2,1),
  `day11` FLOAT(2,1),
  `day12` FLOAT(2,1),
  `day13` FLOAT(2,1),
  `day14` FLOAT(2,1),
  `day15` FLOAT(2,1),
  `day16` FLOAT(2,1),
  `day17` FLOAT(2,1),
  `day18` FLOAT(2,1),
  `day19` FLOAT(2,1),
  `day20` FLOAT(2,1),
  `day21` FLOAT(2,1),
  `day22` FLOAT(2,1),
  `day23` FLOAT(2,1),
  `day24` FLOAT(2,1),
  `day25` FLOAT(2,1),
  `day26` FLOAT(2,1),
  `day27` FLOAT(2,1),
  `day28` FLOAT(2,1),
  `day29` FLOAT(2,1),
  `day30` FLOAT(2,1),
  `day31` FLOAT(2,1),
  `note` TEXT NOT NULL DEFAULT 'note:\n' COMMENT '记录异常情况，请著名日期什么事情',
  UNIQUE KEY (worker,date)
);