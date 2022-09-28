package com.sqlee.dubbo.framework.register;

import com.sqlee.dubbo.framework.URL;

import java.io.*;
import java.util.*;

/**
 * @author lishuqi
 * @date 2022/9/28 15:08
 * @description 远程注册中心注册
 */
public class RemoteRegister {
    // key -> 接口名  value -> 可用的服务列表
    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    /**
     * 服务注册
     * @param interfaceName 接口名
     * @param url 服务属性实体
     */
    public static void reg(String interfaceName, URL url) {
        List<URL> urls = REGISTER.get(interfaceName);
        if (urls == null || urls.size() == 0) {
            List<URL> urlList = new ArrayList<>();
            urlList.add(url);
            REGISTER.put(interfaceName, urlList);
        } else {
            urls.add(url);
            REGISTER.put(interfaceName, urls);
        }
        // 将服务列表发送到远程的注册中心如：zk，redis；这里持久化到文件
        serviceRegister();
    }

    /**
     * 从服务列表中选择一个可用的服务
     * @param interfaceName 接口名
     * @return 可用服务
     */
    public static URL get(String interfaceName) {
        REGISTER = serviceFound();
        List<URL> urls = REGISTER.get(interfaceName);
        // 可以读取用户配置，采用什么轮询算法，默认使用随机算法
        int randomInt = new Random().nextInt(urls.size());
        return urls.get(randomInt);
    }

    /**
     * 服务注册 -> 本地文件
     */
    private static void serviceRegister() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./REGISTER.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(REGISTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 服务发现 -> 本地文件
     * @return
     */
    private static Map<String, List<URL>> serviceFound() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./REGISTER.txt");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
