CREATE TABLE `user_case` (
  `case_id` varchar(45) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `court_datetime` datetime NOT NULL,
  `case_num` varchar(45) NOT NULL,
  `case_content` varchar(2000) DEFAULT NULL,
  `add_time` timestamp(1) NOT NULL DEFAULT CURRENT_TIMESTAMP(1) ON UPDATE CURRENT_TIMESTAMP(1),
  PRIMARY KEY (`case_id`),
  KEY `user_case_fk_idx` (`uid`),
  CONSTRAINT `user_case_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8