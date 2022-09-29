package com.sqlee.dubbo.framework.protocol.http;

import com.sqlee.dubbo.framework.Protocol;
import com.sqlee.dubbo.framework.URL;
import com.sqlee.dubbo.framework.invoke.HttpInvoke;
import com.sqlee.dubbo.framework.invoke.Invoker;
import com.sqlee.dubbo.framework.register.LocalRegister;
import com.sqlee.dubbo.framework.register.RemoteRegister;
import com.sqlee.dubbo.framework.service.UserService;
import com.sqlee.dubbo.producer.impl.UserServiceImpl;

/**
 * @author SQLee
 * @createTime 2022年9月29日-18:33:09
 * @description
 */
public class HttpProtocol implements Protocol {

    @Override
    public void export(URL url) {
        // 进行本地服务注册
        LocalRegister.put(UserService.class.getName(), UserServiceImpl.class);
        // 进行注册中心服务注册
        RemoteRegister.reg(UserService.class.getName(), url);
        // 启动服务
        new HttpServer().start();
    }

    @Override
    public Invoker refere(URL url) {
        return new HttpInvoke(url);
    }

}
