package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = RedisTest.getJedis();
        /*Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(jedis.ping());*/
        jedis.hset("user:1001","name","zhouchenglong");
        Map<String, String> stringStringMap = jedis.hgetAll("user:1001");
        for (Map.Entry<String, String> stringStringEntry : stringStringMap.entrySet()) {
            System.out.println(stringStringEntry.getKey()+":"+stringStringEntry.getValue());
        }
        jedis.close();
    }
    private static JedisPool jedisPool=null;
    public static Jedis getJedis(){
        if (jedisPool==null){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(100);
            jedisPoolConfig.setMaxIdle(30);
            jedisPoolConfig.setMinIdle(20);
            jedisPoolConfig.setBlockWhenExhausted(true);
            jedisPoolConfig.setMaxWaitMillis(5000);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPool = new JedisPool("hadoop102",6379);

        }
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
