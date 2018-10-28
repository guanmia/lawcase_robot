insert into case_analysis (case_id, analysis_id) values('22244c8728804a84b8a4a0268fba695b', 1);
insert into case_analysis (case_id, analysis_id) values('329ac7b63d8447bb9f8a65c269388afe', 2);
insert into category_option_analysis (analysis_id, category_id, options, analysis_summary) values (1, 17, '[{"option_id":12,"value":3,"type":"select"},{"option_id":13,"value":1, "type":"select"},{"option_id":14,"value":1, "type":"select"}]', '分析了155个案件，其中5个案件要素对此案件影响较大');
insert into category_option_analysis (analysis_id, category_id, options, analysis_summary) values (2, 12, '[{"option_id":4,"value":5,"type":"select"},{"option_id":16,"value":5000, "type":"int"}]', '分析了255个案件，其中8个案件要素对此案件影响较大');
insert into oldcase (oldcase_id, court_datetime, case_num, court, case_content) values(1, str_to_date("2010-11-23 14:39:51",'%Y-%m-%d %H:%i:%s'), 'case_num_1', '北京中级法院', '王某容留他人
吸毒罪一审刑事判决书\r\n      发布日期：2017-03-28  浏览：190208次 点击下载文书  点击打印文书  \r\n\r\n \r\n江苏省泰兴市人民法院\r\n刑 事 判 决 书\r\n（2017）苏1283刑初44号\r\n公诉机关泰兴市人民检察院。\r\n被告人王某，男，大专文化，个体经营，曾因寻衅滋事，于2007年8月7日被泰州市公安局高港分局行政拘留十日；因犯聚众斗殴罪、寻衅滋事罪，于2009年7月15日被本院判处有期徒刑三年九个月，2011年5月26日刑满释放；因吸毒，分别于2012年4月25日、2013年3月1日被泰兴市公安局行政拘留五日、十五日；
因吸毒，于2013年3月11日被泰兴市公安局决定社区戒毒三年；因吸毒，于2016年10月21日');
insert into oldcase (oldcase_id, court_datetime, case_num, court, case_content) values(2, str_to_date("2015-12-23 14:39:51",'%Y-%m-%d %H:%i:%s'), 'case_num_1', '北京高级法院', '王某容留他人
吸毒罪一审刑事判决书\r\n      发布日期：2017-03-28  浏览：190208次 点击下载文书  点击打印文书  \r\n\r\n \r\n江苏省泰兴市人民法院\r\n刑 事 判 决 书\r\n（2017）苏1283刑初44号\r\n公诉机关泰兴市人民检察院。\r\n被告人王某，男，大专文化，个体经营，曾因寻衅滋事，于2007年8月7日被泰州市公安局高港分局行政拘留十日；因犯聚众斗殴罪、寻衅滋事罪，于2009年7月15日被本院判处有期徒刑三年九个月，2011年5月26日刑满释放；因吸毒，分别于2012年4月25日、2013年3月1日被泰兴市公安局行政拘留五日、十五日；
因吸毒，于2013年3月11日被泰兴市公安局决定社区戒毒三年；因吸毒，于2016年10月21日');
insert into analysis_oldcase(analysis_id, oldcase_id) values(1, 1);
insert into analysis_oldcase(analysis_id, oldcase_id) values(1, 2);
insert into analysis_oldcase(analysis_id, oldcase_id) values(2, 1);
insert into analysis_oldcase(analysis_id, oldcase_id) values(2, 2);