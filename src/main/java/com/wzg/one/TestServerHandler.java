/******************************************************************
 *
 *    Powered By hhdd.com.
 *
 *    Copyright (c) 2001-2018
 *    https://kada.hhdd.com/
 *
 *    Package:     com.wzg.one
 *
 *    Filename:    TestServerHandler
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
 *    Create at:   2019-03-18 10:08
 *
 *    Revision:
 *
 *    2019-03-18 10:08
 *        - first revision
 *
 *****************************************************************/
package com.wzg.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * TestServerHandler
 *
 * @author wangzg
 * @version 1.0.0
 * @create 2019-03-18 10:08
 */
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            ByteBuf byteBuf = Unpooled.wrappedBuffer("wzg".getBytes("utf-8"));
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            ctx.writeAndFlush(response);
        }

    }

}
