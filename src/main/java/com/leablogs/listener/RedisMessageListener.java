package com.leablogs.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		String body = new String(message.getBody());
		String topic = new String(pattern);
		System.out.println(body);
		System.out.println(topic);
	}

}
