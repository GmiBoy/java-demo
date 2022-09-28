package com.sqlee.dubbo.framework.utils;

import com.sqlee.dubbo.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lishuqi
 * @date 2022/9/28 17:54
 * @description
 */
public class HttpClientUtils {


    public static String send(String host, Integer port, Invocation invocation) {
        try {
            URL url = new URL("http", host, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
