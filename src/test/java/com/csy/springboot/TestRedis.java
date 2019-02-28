package com.csy.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author chen san yang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedis(){
		stringRedisTemplate.opsForValue().set("author", "chen san yang");
		Assert.assertEquals("chen san yang", stringRedisTemplate.opsForValue().get("author"));
	}
}
