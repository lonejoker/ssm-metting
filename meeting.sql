CREATE DATABASE `meeting`;
USE `meeting`;


DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departmentid` INT(16) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `departmentname` VARCHAR(20) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`departmentid`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT  INTO `department`(`departmentid`,`departmentname`)
VALUES (1,'技术部'),(2,'人事部'),(3,'财务部'),(4,'行政部'),(7,'运维部');


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employeeid` INT(16) NOT NULL AUTO_INCREMENT COMMENT '用户ID（唯一）',
  `employeename` VARCHAR(14) DEFAULT NULL COMMENT '员工姓名',
  `username` VARCHAR(20) DEFAULT NULL COMMENT '用户名（登录的账号）',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '电话',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '电子邮件',
  `status` VARCHAR(20) DEFAULT NULL COMMENT '状态（0未审批 1审批通过 2审批未通过）',
  `departmentid` INT(16) DEFAULT NULL COMMENT '部门编号',
  `password` VARCHAR(50) DEFAULT NULL COMMENT '密码',
  `role` VARCHAR(12) DEFAULT NULL COMMENT '角色（1普通用户 2管理员）',
  PRIMARY KEY (`employeeid`)
) ENGINE=INNODB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
INSERT  INTO `employee`(`employeeid`,`employeename`,`username`,`phone`,
`email`,`status`,`departmentid`,`password`,`role`)
VALUES
(8,'小华','xh','13671075406','xh@qq.com','1',1,'1','1'),
(9,'小林','xl','13671075406','xl@qq.com','1',2,'1','2'),
(10,'小文','xw','134555555','xw@qq.com','1',3,'1','2'),
(11,'小敏','xm','1324554321','xm@qq.com','1',4,'1','2'),
(16,'小黄','xh','1322468321','xh@qq.com','2',4,'1','2'),
(20,'小司','xs','13454332334','xs@qq.com','2',4,'1','2'),
(21,'小陈','xc','13559994444','xc@aa.com','1',2,'1','2'),
(25,'小晓','xx','13234572357','xx@qq.com','2',4,'1','2'),
(27,'小张','xz','13356783451','xz@qq.com','2',4,'1','2'),
(36,'小董','xd','13578567824','xd@qq.com','1',3,'123','2');


DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `meetingid` INT(16) NOT NULL AUTO_INCREMENT COMMENT '会议ID',
  `meetingname` VARCHAR(20) DEFAULT NULL COMMENT '会议名称',
  `roomid` INT(16) DEFAULT NULL COMMENT '房间号',
  `reservationistid` INT(16) DEFAULT NULL COMMENT '预订会议人的ID',
  `numberofparticipants` INT(16) DEFAULT NULL COMMENT '参加人数',
  `starttime` DATETIME DEFAULT NULL COMMENT '开始时间',
  `endtime` DATETIME DEFAULT NULL COMMENT '结束时间',
  `reservationtime` DATETIME DEFAULT NULL COMMENT '预约时间',
  `canceledtime` DATETIME DEFAULT NULL COMMENT '取消时间',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '会议说明',
  `status` VARCHAR(20) DEFAULT NULL COMMENT '状态（0没取消的会议 1取消的会议）',
  `canceledreason` VARCHAR(255) DEFAULT NULL COMMENT '取消会议原因',
  PRIMARY KEY (`meetingid`)
) ENGINE=INNODB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
INSERT  INTO `meeting`(`meetingid`,`meetingname`,`roomid`,`reservationistid`,
`numberofparticipants`,`starttime`,`endtime`,`reservationtime`,`canceledtime`,
`description`,`status`,`canceledreason`) 
VALUES
(26,'测试',7,8,12,'2016-04-01 14:00:00','2016-04-01 17:00:00',
'2016-03-29 10:00:00','2015-01-11 01:06:20',NULL,'1',NULL),
(27,'java项目',6,8,12,'2016-06-01 14:00:00','2016-06-01 17:00:00',
'2016-05-31 10:00:00','2015-01-11 01:01:42','测试','1',NULL),
(28,'双十一',5,8,12,'2017-01-10 14:00:00','2017-01-10 17:00:00',
'2017-01-12 10:00:00',NULL,'测试','0',NULL),
(29,'市场部会议',6,8,12,'2017-07-25 14:00:00',
'2017-07-25 17:00:00','2017-07-27 10:00:00',NULL,'市场部','0',NULL),
(30,'技术部会议',10,8,12,'2018-08-16 14:00:00','2018-08-16 17:00:00',
'2018-08-14 10:00:00',NULL,'内部会议','0',NULL),
(31,'小组会议',9,8,12,'2019-09-11 14:00:00','2019-09-11 17:00:00',
'2019-09-10 10:00:00',NULL,'测试','0',NULL);


DROP TABLE IF EXISTS `meetingparticipants`;
CREATE TABLE `meetingparticipants` (
  `meetingid` INT(16) NOT NULL COMMENT '会议ID',
  `employeeid` INT(16) DEFAULT NULL COMMENT '参加会议的员工的员工ID'
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT  INTO `meetingparticipants`(`meetingid`,`employeeid`) 
VALUES (28,13),(28,23),(28,27),(28,16),(29,16),(29,13),(29,8),
(30,15),(30,13),(30,8),(30,23),(27,8),(26,8),(25,8),(28,8),(31,8),
(31,17),(31,23),(32,8),(32,17),(33,15),(34,8),(34,17),(35,8),(36,9),
(36,8),(37,8),(37,23),(38,11),(38,16),(38,20),(39,13),(40,17),(40,23),
(41,36),(42,29),(42,36),(43,10),(45,29),(46,29),(46,36),(47,29),(47,36),(48,29),(48,36);


DROP TABLE IF EXISTS `meetingroom`;
CREATE TABLE `meetingroom` (
  `roomid` INT(16) NOT NULL AUTO_INCREMENT COMMENT '会议室ID',
  `roomnum` INT(16) NOT NULL COMMENT '会议室房间号',
  `roomname` VARCHAR(20) NOT NULL COMMENT '会议室名称',
  `capacity` INT(16) DEFAULT NULL COMMENT '可容纳人数',
  `status` VARCHAR(20) DEFAULT NULL COMMENT '状态（0可用 1被占用）',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`roomid`)
) ENGINE=INNODB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
INSERT  INTO `meetingroom`(`roomid`,`roomnum`,`roomname`,`capacity`,`status`,`description`)
 VALUES (5,101,'第一会议室',15,'0','公共会议室'),(6,102,'第二会议室',5,'0','管理部门会议室'),
 (7,103,'第三会议室',12,'0','市场部专用会议室'),(8,401,'第四会议室',15,'0','公共会议室'),
 (9,201,'第五会议室',15,'0','最大会议室'),(10,601,'第六会议室',12,'0','需要提前三天预定');
