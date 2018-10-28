CREATE TABLE `option_value` (
  `option_id` bigint(20) NOT NULL,
  `value_id` bigint(20) NOT NULL,
  `value_name` varchar(20) NOT NULL,
  `value_intro` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`option_id`,`value_id`,`value_name`),
  CONSTRAINT `option_value_fk` FOREIGN KEY (`option_id`) REFERENCES `option` (`option_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8