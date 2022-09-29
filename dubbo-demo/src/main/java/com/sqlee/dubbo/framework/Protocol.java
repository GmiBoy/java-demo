package com.sqlee.dubbo.framework;

import com.sqlee.dubbo.framework.invoke.Invoker;

/**
 * @author Administrator
 * @createTime 2022年9月29日-14:32:48
 * @description
 */
public interface Protocol {
    
    void export(URL url);

    Invoker refere(URL url);

}
