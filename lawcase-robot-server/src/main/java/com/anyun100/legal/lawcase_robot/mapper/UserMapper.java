package com.anyun100.legal.lawcase_robot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anyun100.legal.lawcase_robot.domain.User;
public interface UserMapper {
	
	@Select("SELECT * FROM user")
	@Results({
		@Result(property = "uid",  column = "uid"),
		@Result(property = "openid", column = "openid"),
		@Result(property = "reg_time", column = "reg_time")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM user WHERE openid = #{openid}")
	@Results({
		@Result(property = "uid",  column = "uid"),
		@Result(property = "openid", column = "openid"),
		@Result(property = "reg_time", column = "reg_time")
	})
	User getOne(String openid);
	
	@Select("select max(uid) max_uid from user;")
	@Results({
		@Result(column = "max_uid", javaType=java.lang.Long.class)
	})
	Long getMaxUid();

	@Insert("INSERT INTO user(uid,openid,reg_time) VALUES(#{uid}, #{openid}, #{reg_time})")
	void insert(User user);

	@Update("UPDATE user SET uid=#{uid},openid=#{openid} WHERE uid =#{uid}")
	void update(User user);

	@Delete("DELETE FROM user WHERE uid =#{uid}")
	void delete(Long uid);
	
	@Delete("DELETE FROM user")
	void deleteAll();

}