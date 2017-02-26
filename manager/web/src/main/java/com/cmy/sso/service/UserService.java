package com.cmy.sso.service;

import com.cmy.mapper.UserMapper;
import com.cmy.model.User;
import com.cmy.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by Lankidd on 2017/2/26.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void userLogin(String userName, String passwd) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)) {
            return;
        }

        User user = userList.get(0);
        if (!user.getPassword().equals(passwd)) {
            return;
        }

        String token = UUID.randomUUID().toString();
    }
}
