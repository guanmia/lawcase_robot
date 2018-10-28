CREATE TABLE `category_option_analysis` (
  `analysis_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `options` varchar(1000) NOT NULL,
  `analysis_summary` varchar(1000) NOT NULL,
  PRIMARY KEY (`analysis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8