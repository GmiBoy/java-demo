package com.sqlee.dubbo.framework;

import java.io.Serializable;

/**
 * @author lishuqi
 * @date 2022/9/28 17:13
 * @description 通信协议内容
 */
public class Invocation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String interfaceName;

    private String methodName;

    private Class[] paramType;

    private Object[] param;

    // 支持一个接口多个实现类，即指定要调用的实现类
    private Integer version;

    public Invocation(String interfaceName, String methodName, Class[] paramType, Object[] param) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramType = paramType;
        this.param = param;
    }

    public Invocation(String interfaceName, String methodName, Class[] paramType, Object[] param, Integer version) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramType = paramType;
        this.param = param;
        this.version = version;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }

    public Object[] getParam() {
        return param;
    }

    public void setParam(Object[] param) {
        this.param = param;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
