package com.sqlee.dubbo.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lishuqi
 * @date 2022/9/28 14:03
 * @description 本地注册
 */
public class LocalRegister {

    // key -> 接口名称   value -> 接口实现类
    private static Map<String, Class> localMap = new HashMap<>();

    public static void put(String name, Class clazz) {
        localMap.put(name, clazz);
    }

    public static Class get(String name) {
        return localMap.get(name);
    }
}
