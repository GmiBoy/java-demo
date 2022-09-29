package com.sqlee.dubbo.framework.invoke;

import com.sqlee.dubbo.framework.Invocation;

/**
 * @author SQLee
 * @createTime 2022年9月29日-18:24:28
 * @description
 */
public interface Invoker {

    String invoke(Invocation invocation);

}
