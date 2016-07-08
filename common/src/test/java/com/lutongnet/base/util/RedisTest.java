package com.lutongnet.base.util;

import java.util.List;

import redis.clients.jedis.*;

public class RedisTest {

	public static void main(String[] args) {
		Jedis redis = new Jedis("192.168.92.128", 6379);// 连接redis

		// String
		redis.del("user");
		redis.set("user", "piaolingzxh");
		System.out.println(redis.get("user"));
		
		redis.set("user", "123");
		System.out.println(redis.get("USER_ACTION_LOG"));
		
		
		
		redis.append("user", "-ge");
		System.out.println(redis.get("user"));

		redis.del("linked-list");
		redis.rpush("linked-list", "1", "2", "3");
		List<String> eduList = redis.lrange("linked-list", 0, -1);
		for (String str : eduList) {
			System.out.print(str + "\t");
		}
		System.out.println();

		
		
		// redis.rpush("linked-list", "1", "2", "3");
		for (; redis.llen("linked-list") > 0L;) {

			System.out.println(redis.lpop("linked-list"));

		}

		System.out.println();

	}
}
