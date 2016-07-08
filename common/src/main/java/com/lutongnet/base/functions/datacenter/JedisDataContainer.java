package com.lutongnet.base.functions.datacenter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.util.ObjectUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisDataContainer implements DataContainer, DataContainerNotify {

	private static Logger		log	= LoggerFactory.getLogger(JedisDataContainer.class);

	private ShardedJedisPool	shardedJedisPool;

	public JedisDataContainer(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	@Override
	public <T> T get(String dataName, Class<T> clazz) {
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		try {
			byte[] valueBytes = shardedJedis.get(dataName.getBytes());
			log.debug("JedisDataContainer get dataName=" + dataName + "; value=" + valueBytes);
			return (T) ObjectUtils.toObject(valueBytes);
		} catch (Exception e) {
			log.error("JedisDataContainer get error:", e);
			throw new RuntimeException("JedisDataContainer get error:", e);
		} finally {
			shardedJedis.close();
		}
	}

	@Override
	public void notify(Map<String, Object> dataMap) {
		setToMemcache(dataMap);
	}

	private void setToMemcache(Map<String, Object> datasMap) {
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		for (Map.Entry<String, Object> entry : datasMap.entrySet()) {
			try {
				byte[] valueBytes = ObjectUtils.getBytes(entry.getValue());
				shardedJedis.set(entry.getKey().getBytes(), valueBytes);
			} catch (Exception e) {
				log.error("add map error.", e);
				shardedJedis.close();
				throw new RuntimeException("add map error.", e);
			}
		}
		shardedJedis.close();
	}
}
