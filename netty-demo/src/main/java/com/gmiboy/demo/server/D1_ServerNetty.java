package com.gmiboy.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author gmiboy
 * @date 2022/8/29 22:13
 * @description
 **/
public class D1_ServerNetty {

    public static void main(String[] args) {
        D1_ServerNetty server = new D1_ServerNetty();
        System.out.println("开始启动服务器...");
        server.start();
        System.out.println("服务器运行结束！！！");
    }


    public void start() {
        // 定义事件循环组 类似于 selector
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        // 定义服务端的启动器
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap
                    // 定义服务端通道类型
                    .channel(NioServerSocketChannel.class)
                    // 关联事件循环组
                    .group(loopGroup)
                    // 本机绑定端口
                    .localAddress(new InetSocketAddress(9999))
                    // 定义通道处理器及类型
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 向pipeline中间添加自定义的处理器
                            sc.pipeline().addLast(new D1_ServerHandler());
                        }
                    });
            // 进行绑定 因为返回的异步 所以需要进行阻塞
            ChannelFuture future = serverBootstrap.bind().sync();
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
