package com.anyun100.legal.lawcase_robot.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.anyun100.legal.lawcase_robot.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper UserMapper;

	@Test
	public void testInsert() throws Exception {
		UserMapper.deleteAll();
		UserMapper.insert(new User(1L, "a123456", Timestamp.valueOf(LocalDateTime.now())));
		UserMapper.insert(new User(2L, "b123456", Timestamp.valueOf(LocalDateTime.now())));
		UserMapper.insert(new User(3L, "b123456", Timestamp.valueOf(LocalDateTime.now())));

		Assert.assertEquals(3, UserMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<User> users = UserMapper.getAll();
		System.out.println(users.toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		User user = UserMapper.getOne("a123456");
		System.out.println(user.toString());
	}
	
	@Test
	public void testMaxUid() throws Exception {
		long uid = UserMapper.getMaxUid();
		System.out.println(uid);
	}

}