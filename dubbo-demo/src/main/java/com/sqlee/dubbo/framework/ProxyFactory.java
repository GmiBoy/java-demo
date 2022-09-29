package com.sqlee.dubbo.framework;

import java.lang.reflect.Proxy;

import com.sqlee.dubbo.framework.invoke.Invoker;
import com.sqlee.dubbo.framework.register.RemoteRegister;

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
                    Protocol protocol = ProtocolFactory.getProtocolObj(url.getProtocol());
                    Invoker refere = protocol.refere(url);
                    // 发送请求并拿到响应结果
                    return refere.invoke(invocation);
                });
    }
}
