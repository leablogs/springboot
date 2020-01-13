package com.leablogs.redisscript;

public interface RedisScript<T> {
	String getShal();

	Class<T> getResultType();

	String getScriptAsString();
}
