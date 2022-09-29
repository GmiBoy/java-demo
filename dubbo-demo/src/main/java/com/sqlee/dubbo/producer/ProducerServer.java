package com.sqlee.dubbo.producer;

import com.sqlee.dubbo.framework.Protocol;
import com.sqlee.dubbo.framework.ProtocolFactory;
import com.sqlee.dubbo.framework.URL;
import com.sqlee.dubbo.framework.protocol.http.DispatcherServlet;
import com.sqlee.dubbo.framework.register.LocalRegister;
import com.sqlee.dubbo.framework.register.RemoteRegister;
import com.sqlee.dubbo.framework.service.UserService;
import com.sqlee.dubbo.producer.impl.UserServiceImpl;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @author lishuqi
 * @date 2022/9/28 13:53
 * @description 生产者服务
 */
public class ProducerServer {

    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 获取配置的协议
        String protocol = System.getProperty("protocol");
        // 进行本地服务注册
        LocalRegister.put(UserService.class.getName(), UserServiceImpl.class);
        // 进行注册中心服务注册
        RemoteRegister.reg(UserService.class.getName(), new URL( protocol, "localhost", 8089, UserService.class.getName(), UserServiceImpl.class));
        Protocol server = ProtocolFactory.getProtocolObj(protocol);
        // 启动服务
        server.start();
    }
}
