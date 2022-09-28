package com.sqlee.dubbo.producer;

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

    public static void main(String[] args) {
        // 进行本地服务注册
        LocalRegister.put(UserService.class.getName(), UserServiceImpl.class);
        // 进行注册中心服务注册
        RemoteRegister.reg(UserService.class.getName(), new URL("http", "localhost", 8089, UserService.class.getName(), UserServiceImpl.class));
        // 启动服务器
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(8089);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        Host host = new StandardHost();
        host.setName("localhost");

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
