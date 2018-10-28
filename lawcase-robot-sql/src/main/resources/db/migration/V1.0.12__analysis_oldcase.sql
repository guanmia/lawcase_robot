CREATE TABLE `analysis_oldcase` (
  `analysis_id` bigint(20) NOT NULL,
  `oldcase_id` bigint(20) NOT NULL,
  PRIMARY KEY (`analysis_id`,`oldcase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8