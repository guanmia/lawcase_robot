CREATE TABLE `option` (
  `option_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `option_name` VARCHAR(20) NOT NULL,
  `option_type` INT NOT NULL,
  `option_value_step` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`option_id`),
  INDEX `option_fk_idx` (`category_id` ASC),
  CONSTRAINT `option_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )ENGINE=InnoDB DEFAULT CHARSET=utf8
