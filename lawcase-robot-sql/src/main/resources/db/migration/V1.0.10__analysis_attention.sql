CREATE TABLE `analysis_attention` (
  `id` BIGINT(20) NOT NULL,
  `analysis_id` BIGINT(20) NOT NULL,
  `attention_name` VARCHAR(100) NOT NULL,
  `attention_content` VARCHAR(2000) NOT NULL,
  PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;
