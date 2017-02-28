package com.cmy.web.controller;

import com.cmy.model.User;
import com.cmy.web.cache.JedisDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lankidd on 2017/2/27.
 */
@RestController
@RequestMapping("cache")
public class JedisController {
    @Autowired
    JedisDemo jedisDemo;

    @RequestMapping(value = "setValOpsStr", method = RequestMethod.POST)
    public void setValOpsStr(String key, String value) {
        jedisDemo.setValOpsStr(key, value);
    }

    @RequestMapping(value = "setValOps", method = RequestMethod.POST)
    public void setValOps(Integer userId) {
        jedisDemo.setValOps(userId);
    }

    @RequestMapping(value = "getStrVal", method = RequestMethod.GET)
    public String getStrVal(String key) {
        return jedisDemo.getStrVal(key);
    }

    @RequestMapping(value = "getObjVal", method = RequestMethod.GET)
    public Object getObjVal(String key) {
        return jedisDemo.getObjVal(key);
    }

    @RequestMapping(value = "findUserById", method = RequestMethod.GET)
    public User findUserById(Long userId) {
        return jedisDemo.findUserById(userId);
    }

    @RequestMapping(value = "modifyUser", method = RequestMethod.PUT)
    public User modifyUser(@RequestBody User user) {
        return jedisDemo.modifyUser(user);
    }

    @RequestMapping(value = "removeUser", method = RequestMethod.DELETE)
    public void removeUser(Long userId) {
        jedisDemo.removeUser(userId);
    }
}
