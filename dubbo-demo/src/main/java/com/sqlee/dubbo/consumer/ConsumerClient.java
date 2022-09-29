package com.sqlee.dubbo.consumer;

import com.sqlee.dubbo.framework.ProxyFactory;
import com.sqlee.dubbo.framework.service.UserService;

/**
 * @author lishuqi
 * @date 2022/9/28 13:46
 * @description 消费者服务
 */
public class ConsumerClient {

    public static void main(String[] args) {
        // 使用JDK的动态代理，生成接口的实现类
        UserService userService = ProxyFactory.getProxyObj(UserService.class);
        String gmiBoy = userService.getAliaName("隶属其");
        System.out.println(gmiBoy);
    }

}
