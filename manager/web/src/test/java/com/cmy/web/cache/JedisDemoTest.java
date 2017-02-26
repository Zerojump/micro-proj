package com.cmy.web.cache;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lankidd on 2017/2/22.
 */
public class JedisDemoTest {

    @Test
    public void testJedisSingle() throws Exception {
        //创建Jedis实例
        //调用Jedis方法
        //关闭Jedis实例
        try (Jedis jedis = new Jedis("192.168.38.129", 6379)) {
            jedis.set("a", "Jedis instance");
            System.out.println("jedis.get(\"a\") = " + jedis.get("a"));
        }
    }

    @Test
    public void testJedisPool() throws Exception {
        //创建Jedis连接池
        //从连接池获取Jedis实例
        try (JedisPool jedisPool = new JedisPool("192.168.38.129", 7002)) {
            try (Jedis jedis = jedisPool.getResource()) {
                //jedis.flushAll();
                System.out.println("jedis.get(\"a\") = " + jedis.get("test"));
            }
        }
    }

    @Test
    public void testJedisCluster() throws Exception {
        //JedisPoolConfig config = new JedisPoolConfig();
        //// 最大连接数
        //config.setMaxTotal(30);
        //// 最大连接空闲数
        //config.setMaxIdle(2);

        Set<HostAndPort> hostAndPortSet = new HashSet<>(6);
        String host = "192.168.38.129";
        int minPort = 7001;
        hostAndPortSet.add(new HostAndPort(host, minPort++));
        hostAndPortSet.add(new HostAndPort(host, minPort++));
        hostAndPortSet.add(new HostAndPort(host, minPort++));
        hostAndPortSet.add(new HostAndPort(host, minPort++));
        hostAndPortSet.add(new HostAndPort(host, minPort++));
        hostAndPortSet.add(new HostAndPort(host, minPort));

        try (JedisCluster cluster = new JedisCluster(hostAndPortSet)){
            //cluster.set("test", "Jedis Cluster");
            System.out.println("cluster.get(\"test\") = " + cluster.get("t"));
        }
    }
}