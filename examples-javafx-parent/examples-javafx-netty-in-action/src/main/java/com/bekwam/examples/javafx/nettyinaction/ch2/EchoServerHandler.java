package com.bekwam.examples.javafx.nettyinaction.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

	private Logger logger = LoggerFactory.getLogger( EchoServerHandler.class );
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		
		String in_s = in.toString(CharsetUtil.UTF_8);
		String uc = in_s.toUpperCase();
		if( logger.isInfoEnabled() ) {
			logger.info("[READ] read " + in_s + ", writing " + uc);
		}
		in.setBytes(0,  uc.getBytes(CharsetUtil.UTF_8));
		ctx.write(in);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		if(logger.isDebugEnabled() ) {
			logger.debug("[CHANNEL ACTIVE]");
		}
		ctx.channel().closeFuture().addListener(f -> logger.debug("[CLOSE]"));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error( "error in echo server", cause);
		ctx.close();
	}
}
	