package com.gmiboy.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @author gmiboy
 * @date 2022/8/29 22:34
 * @description
 **/
public class D1_ServerHandler extends ChannelInboundHandlerAdapter {

    // 监听通道 - 有数据时触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        System.out.println("server accept:" + bb.toString(Charset.defaultCharset()));
        ctx.writeAndFlush(bb);
        // 关闭连接
        ctx.close().sync();
    }
}
