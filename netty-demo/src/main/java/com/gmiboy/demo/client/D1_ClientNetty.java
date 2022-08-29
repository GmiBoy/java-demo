package com.gmiboy.demo.client;

import com.gmiboy.demo.server.D1_ServerHandler;
import com.gmiboy.demo.server.D1_ServerNetty;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author gmiboy
 * @date 2022/8/29 22:38
 * @description
 **/
public class D1_ClientNetty {

    public static void main(String[] args) {
        D1_ClientNetty server = new D1_ClientNetty();
        System.out.println("开始启动服务器...");
        server.start();
        System.out.println("服务器启动完成！！！");
    }


    public void start() {
        // 定义事件循环组 类似于 selector
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        // 定义服务端的启动器
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap
                    // 定义服务端通道类型
                    .channel(NioSocketChannel.class)
                    // 绑定远程主机的IP + port
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 9999))
                    // 关联事件循环组
                    .group(loopGroup)
                    // 定义通道处理器 和 类型
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 添加自定义的通道处理器
                            sc.pipeline().addLast(new D1_ClientHandler());
                        }
                    });
            // 进行和服务端连接 因为返回的异步 所以需要进行阻塞
            ChannelFuture future = bootstrap.connect().sync();
            // 关闭服务器serverChannel 因为是异步 进行阻塞
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                loopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
