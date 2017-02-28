package com.cmy.web.cache;

import com.cmy.mapper.UserMapper;
import com.cmy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lankidd on 2017/2/22.
 */
@Service
public class JedisDemo {
    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps;

    public void setValOpsStr(String key, String value) {
        valOpsStr.set(key, value);
    }

    public void setValOps(Integer userId) {
        User user = userMapper.selectByPrimaryKey((long) userId);
        valOps.set(user.getId().toString(), user);
    }

    public String getStrVal(String key) {
        return valOpsStr.get(key);
    }

    public Object getObjVal(String key) {
        return valOps.get(key);
    }

    @Cacheable(value = "mysql:user", key = "#userId.toString()" /*keyGenerator = "simpleKey"*/)
    public User findUserById(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @CachePut(value = "mysql:user", key = "#user.id.toString()" /*keyGenerator = "simpleKey"*/)
    public User modifyUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @CacheEvict(value = "mysql:user", key = "#userId.toString()" /*keyGenerator = "simpleKey"*/)
    public void removeUser(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }
}