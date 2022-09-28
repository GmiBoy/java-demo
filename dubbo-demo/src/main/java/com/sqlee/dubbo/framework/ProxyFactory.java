package com.sqlee.dubbo.framework;

import com.sqlee.dubbo.framework.register.RemoteRegister;
import com.sqlee.dubbo.framework.utils.HttpClientUtils;

import java.lang.reflect.Proxy;

/**
 * @author lishuqi
 * @date 2022/9/28 18:02
 * @description 接口代理工厂，生成目标接口的实现类
 */
public class ProxyFactory<T> {

    public static <T> T getProxyObj(final Class clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                (proxy, method, args) -> {
                    // 通过接口名，从远程的注册中心拿到一个可用的服务
                    URL url = RemoteRegister.get(clazz.getName());
                    // 构造双方协议实体
                    Invocation invocation = new Invocation(clazz.getName(), method.getName(), method.getParameterTypes(), args);
                    // 发送请求并拿到响应结果
                    String result = HttpClientUtils.send(url.getHost(), url.getPort(), invocation);
                    return result;
                });
    }
}
