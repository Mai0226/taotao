package com.taotao.content.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class MyTest {
    public static void main(String[] args) {
       /* Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.235.120", 7001));
        nodes.add(new HostAndPort("192.168.235.120", 7002));
        nodes.add(new HostAndPort("192.168.235.120", 7003));
        nodes.add(new HostAndPort("192.168.235.120", 7004));
        nodes.add(new HostAndPort("192.168.235.120", 7005));
        nodes.add(new HostAndPort("192.168.235.120", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        // 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
        jedisCluster.set("helloa", "100");
        String result = jedisCluster.get("helloa");
        // 第三步：打印结果
        System.out.println(result);
        // 第四步：系统关闭前，关闭JedisCluster对象。
        jedisCluster.close();*/
    test1();
    }
    public static void test1(){
        Jedis jedis = new Jedis("192.168.235.120",6379);
        String ad1 = jedis.get("AD1");
        System.out.println(ad1);
    }

}
