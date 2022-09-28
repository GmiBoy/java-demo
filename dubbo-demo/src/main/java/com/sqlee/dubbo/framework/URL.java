package com.sqlee.dubbo.framework;

import java.io.Serializable;

/**
 * @author lishuqi
 * @date 2022/9/28 15:12
 * @description 远程注册实体
 */
public class URL implements Serializable {
    private static final long serialVersionUID = 1L;

    private String protocol;

    private String host;

    private Integer port;

    private String interfaceName;

    private Class implClazz;

    public URL(String protocol, String host, Integer port, String interfaceName, Class implClazz) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.interfaceName = interfaceName;
        this.implClazz = implClazz;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Class getImplClazz() {
        return implClazz;
    }

    public void setImplClazz(Class implClazz) {
        this.implClazz = implClazz;
    }
}
