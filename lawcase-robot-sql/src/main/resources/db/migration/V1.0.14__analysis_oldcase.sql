DROP procedure IF EXISTS `analysis_oldcase`;

DELIMITER $$
CREATE PROCEDURE `analysis_oldcase`()
BEGIN
	declare max_analysis_id bigint(20) default 0;
	declare max_oldcase_id bigint(20) default 0;
	select MAX(oldcase_id) into max_oldcase_id from oldcase;
	select MAX(analysis_id) into max_analysis_id from category_option_analysis;

	while max_oldcase_id > 0 DO
		while max_analysis_id > 0 do 
		   insert into analysis_oldcase(analysis_id, oldcase_id) values(max_analysis_id, max_oldcase_id);
		   SET max_analysis_id = max_analysis_id - 1;
		END WHILE;
		select MAX(analysis_id) into max_analysis_id from category_option_analysis;
		SET max_oldcase_id = max_oldcase_id - 1;
	END WHILE;
END$$

DELIMITER ;