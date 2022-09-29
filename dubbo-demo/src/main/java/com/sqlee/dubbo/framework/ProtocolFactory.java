package com.sqlee.dubbo.framework;

import com.sqlee.dubbo.framework.protocol.http.HttpProtocol;

/**
 * @author SQLee
 * @createTime 2022年9月29日-15:57:25
 * @description
 */
public class ProtocolFactory {
    
    public static Protocol getProtocolObj(String name) {
        if (name == null || name.equals("")) {
            return new HttpProtocol();
        }
        if (name.equals("http")) {
            return new HttpProtocol();
        } else {
            return null;
        }
    }

}
