package com.gmiboy.demo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author gmiboy
 * @date 2022/8/29 22:45
 * @description
 **/
public class D1_ClientHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, Object o) throws Exception {
        ByteBuf bb = (ByteBuf) o;
        System.out.println("client accept:" + bb.toString(Charset.defaultCharset()));
        cxt.close().sync();
    }

    // 当通道准备好时 触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String str = "netty hello!!!";
        ctx.writeAndFlush(Unpooled.copiedBuffer(str, Charset.defaultCharset()));
    }
}
