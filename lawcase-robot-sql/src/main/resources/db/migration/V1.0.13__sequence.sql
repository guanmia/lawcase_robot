CREATE TABLE `sequence` (
  `sequence_name` varchar(45) NOT NULL,
  `value` bigint(20) NOT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP function IF EXISTS `nextval`;

DELIMITER $$
CREATE FUNCTION `nextval`(v_sequence_name varchar(45)) RETURNS bigint(20)
BEGIN
    declare current bigint(20);
    set current = 0;
    select t.value into current from sequence t where t.sequence_name = v_sequence_name;
    if current = 0 then 
		insert into sequence (sequence_name, value) values (v_sequence_name, 0);
		select t.value into current from sequence t where t.sequence_name = v_sequence_name;
	end if;
    update sequence t set t.value = t.value + 1 where t.sequence_name = v_sequence_name;
    select t.value into current from sequence t where t.sequence_name = v_sequence_name;

    return current;
end$$

DELIMITER ;