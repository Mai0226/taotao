package com.taotao.content.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/*@Component*/
public class JedisClusterImpl implements JedisClusterClient {
   /* @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(String key, String value) {
        String result = jedisCluster.set(key, value);
        jedisCluster.close();
        return null;
    }

    @Override
    public String get(String key) {
        String result = jedisCluster.get(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Boolean result = jedisCluster.exists(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Long result = jedisCluster.expire(key, seconds);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Long result = jedisCluster.ttl(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Long result = jedisCluster.incr(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Long result = jedisCluster.hset(key, field, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        String result = jedisCluster.hget(key, field);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Long result = jedisCluster.hdel(key, field);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Long result = jedisCluster.del(key);
        jedisCluster.close();
        return result;
    }*/
}
