package com.sqlee.dubbo.producer;

import com.sqlee.dubbo.framework.Protocol;
import com.sqlee.dubbo.framework.ProtocolFactory;
import com.sqlee.dubbo.framework.URL;
import com.sqlee.dubbo.framework.service.UserService;
import com.sqlee.dubbo.producer.impl.UserServiceImpl;

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
        String protocolName = System.getProperty("protocol");
        URL url = new URL( protocolName, "localhost", 8089, UserService.class.getName(), UserServiceImpl.class);
        Protocol protocol = ProtocolFactory.getProtocolObj(protocolName);
        // 启动服务
        protocol.export(url);
    }
}
