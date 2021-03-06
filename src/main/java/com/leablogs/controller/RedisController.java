package com.leablogs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/redis")
public class RedisController {
	@Autowired
	private RedisTemplate redisTemplate = null;
	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;

	@RequestMapping("/stringAndHash")
	@ResponseBody
	public Map<String, Object> testStringAndHash() {
		redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForValue().set("int", "1");
		stringRedisTemplate.opsForValue().set("int", "1");
		Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
		jedis.decr("int");
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("field", "value1");
		hash.put("field1", "value2");
		stringRedisTemplate.opsForHash().putAll("hash", hash);
		stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> redisList() {
		stringRedisTemplate.opsForList().leftPushAll("list", "v1", "v3", "v5", "v7");
		stringRedisTemplate.opsForList().rightPushAll("list1", "2", "4", "6", "v8");
		BoundListOperations listOperations = stringRedisTemplate.boundListOps("list1");
		Object result = listOperations.rightPop();
		Object result1 = listOperations.index(1);
		listOperations.leftPush("v0");
		Long size = listOperations.size();
		List elements = listOperations.range(0, size - 2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

	@RequestMapping("/set")
	@ResponseBody
	public Map redisSet() {
		stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
		stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
		BoundSetOperations setops = stringRedisTemplate.boundSetOps("set2");
		setops.add("v6", "v7");
		setops.remove("v1", "v7");
		Set set1 = setops.members();
		long size = setops.size();
		Set inter = setops.intersect("set2");
		setops.intersectAndStore("set2", "inter");
		Set diff = setops.diff("set2");
		setops.diffAndStore("set2", "diff");
		Set union = setops.union("set2");
		setops.diffAndStore("set2", "union");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;

	}

	@RequestMapping("/mulit")
	@ResponseBody
	public Map<String, Object> redisMulti() {
		redisTemplate.opsForValue().set("key1", "value1");
		List list = (List) redisTemplate.execute((RedisOperations operations) -> {
			operations.watch("key1");
			operations.multi();
			operations.opsForValue().set("key2", "value2");
			operations.opsForValue().increment("key1", 1);
			Object value2 = operations.opsForValue().get("key2");
			System.out.println("命令在队列，所以value为null【" + value2 + "】");
			operations.opsForValue().set("key3", "value3");
			Object value3 = operations.opsForValue().get("key3");
			System.out.println("命令在队列，所以value为null【" + value3 + "】");
			return operations.exec();
		});

		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

	@RequestMapping("/pipeline")
	@ResponseBody
	public Map redisPipeline() {
		Long start = System.currentTimeMillis();
		List list = (List) redisTemplate.executePipelined((RedisOperations operations) -> {
			for (int i = 1; i <= 10000000; i++) {
				operations.opsForValue().set("pipeline_1" + i, "value_" + i);
				String value = (String) operations.opsForValue().get("pipeline_" + i);
				if (i == 10000000) {
					System.out.println("命令指示进入队列，所以值为空【" + value + "】");
				}
			}
			return null;
		});
		Long end = System.currentTimeMillis();
		System.out.println("耗时:" + ((end - start) / 1000) + "秒");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

	@RequestMapping("/redisLua")
	@ResponseBody
	public Map<String, Object> redisLua() {
		DefaultRedisScript<String> rs = new DefaultRedisScript<String>();
		rs.setScriptText("return 'Hello Redis'");
		rs.setResultType(String.class);
		RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
		String str = (String) redisTemplate.execute(rs, stringRedisSerializer, stringRedisSerializer, null);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("str", str);
		return map;
	}

	@RequestMapping("/redisLua1")
	@ResponseBody
	public Map<String, Object> redisLua1(String key1, String key2, String value1, String value2) {
		String lua = "redis.call('set',KEYS[1],ARGV[1]) \nredis.call('set',KEYS[2],ARGV[2]) \n"
				+ "local str1 = redis.call('get',KEYS[1]) \nlocal str2 = redis.call('get',KEYS[2]) \n"
				+ "if str1 == str2 then return 1 \nend \nreturn 0 \n";
		System.out.println(lua);
		DefaultRedisScript<Long> rs = new DefaultRedisScript<Long>();
		rs.setScriptText(lua);
		rs.setResultType(Long.class);
		RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
		List<String> keyList = new ArrayList<String>();
		keyList.add(key1);
		keyList.add(key2);
		Long result = (Long) redisTemplate.execute(rs, stringSerializer, stringSerializer, keyList, value1, value2);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("result", result);
		return map;
	}

}
