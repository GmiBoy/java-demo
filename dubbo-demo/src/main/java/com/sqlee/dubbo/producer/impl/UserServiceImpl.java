package com.sqlee.dubbo.producer.impl;

import com.sqlee.dubbo.framework.service.UserService;

/**
 * @author lishuqi
 * @date 2022/9/28 14:00
 * @description
 */
public class UserServiceImpl implements UserService {

    @Override
    public String getAliaName(String no) {
        return no + "==》SQLee";
    }
}
