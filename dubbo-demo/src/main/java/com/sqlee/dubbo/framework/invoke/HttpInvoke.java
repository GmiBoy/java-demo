package com.sqlee.dubbo.framework.invoke;

import java.net.http.HttpClient;

import com.sqlee.dubbo.framework.Invocation;
import com.sqlee.dubbo.framework.URL;
import com.sqlee.dubbo.framework.utils.HttpClientUtils;

/**
 * @author SQLee
 * @createTime 2022年9月29日-18:29:22
 * @description
 */
public class HttpInvoke implements Invoker{

    private URL url;

    public HttpInvoke(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        return HttpClientUtils.send(url.getHost(), url.getPort(), invocation);
    }
    
}
