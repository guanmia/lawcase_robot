CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL,
  `partent_id` bigint(20) NOT NULL,
  `category_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`category_id`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8