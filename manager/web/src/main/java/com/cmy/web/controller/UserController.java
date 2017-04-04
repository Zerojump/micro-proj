package com.cmy.web.controller;

import com.cmy.mapper.UserMapper;
import com.cmy.model.User;
import com.cmy.model.UserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lankidd on 2017/2/19.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getById(@PathVariable Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public int saveUser(@RequestBody User user, Integer under) {
        int insert = userMapper.insert(user);
        int i = 3 / under;
        return insert;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<User> listUsers() {
        PageHelper.startPage(1, 10);
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        //Page<User> userPage = (Page<User>) userList;
        //log.info("pageNum:{}",userPage.getPageNum());
        //log.info("pages:{}",userPage.getPages());
        //log.info("pageSize:{}",userPage.getPageSize());
        PageInfo pageInfo = new PageInfo(userList);
        log.info("pageNum:{}", pageInfo.getPageNum());
        return userList;
    }
}
