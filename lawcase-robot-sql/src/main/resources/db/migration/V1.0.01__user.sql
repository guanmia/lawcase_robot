CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL,
  `openid` varchar(45) NOT NULL,
  `reg_time` datetime NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8