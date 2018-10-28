CREATE TABLE `analysis_law` (
  `id` BIGINT(20) NOT NULL,
  `analysis_id` BIGINT(20) NOT NULL,
  `law_name` VARCHAR(100) NOT NULL,
  `law_num` VARCHAR(100) NOT NULL,
  `law_content` VARCHAR(2000) NOT NULL,
  PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;
