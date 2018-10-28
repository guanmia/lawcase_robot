CREATE TABLE `oldcase` (
  `oldcase_id` bigint(20) NOT NULL,
  `court_datetime` datetime(1) NOT NULL,
  `case_num` varchar(45) NOT NULL,
  `case_content` varchar(2000) NOT NULL,
  `court` varchar(45) NOT NULL,
  PRIMARY KEY (`oldcase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
