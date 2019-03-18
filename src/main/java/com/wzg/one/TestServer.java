/******************************************************************
 *
 *    Powered By hhdd.com.
 *
 *    Copyright (c) 2001-2018
 *    https://kada.hhdd.com/
 *
 *    Package:     com.wzg.one
 *
 *    Filename:    TestServer
 *
 *    Description: 
 *
 *    Copyright:   Copyright (c) 2001-2018
 *
 *    Company:     hhdd.com
 *
 *    @author: 王志刚
 *
 *    @version: 1.0.0
 *
 *    Create at:   2019-03-18 09:58
 *
 *    Revision:
 *
 *    2019-03-18 09:58
 *        - first revision
 *
 *****************************************************************/
package com.wzg.one;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * TestServer
 *
 * @author wangzg
 * @version 1.0.0
 * @create 2019-03-18 09:58
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoopGroup, workLoopGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("httpServerCodec", new HttpServerCodec());
                            pipeline.addLast("testHttpServerHandler", new TestServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }

    }

}
