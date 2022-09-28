package com.sqlee.dubbo.framework.protocol.http;

import com.sqlee.dubbo.framework.Invocation;
import com.sqlee.dubbo.framework.register.LocalRegister;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            // 从本地注册中，拿到接口对应的实现类
            Class implClazz = LocalRegister.get(invocation.getInterfaceName());
            // 定位到实现类中要调用的方法
            Method method = implClazz.getMethod(invocation.getMethodName(), invocation.getParamType());
            // 通过反射调用该方法，并拿到返回值
            String result = (String) method.invoke(implClazz.newInstance(), invocation.getParam());
            // 将返回值写回消费者
            resp.getOutputStream().write(result.getBytes());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }

    }

}
