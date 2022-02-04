package my;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.CharsetUtil;

import io.netty.util.concurrent.EventExecutor;

public class StartInboundHandler extends ChannelInboundHandlerAdapter{
	ChannelPromise promise;
	ChannelHandlerContext context;
	EventExecutor eventExecutor; 
	public static int count;
	ChannelGroup channels;
	 public void beforeAdd(ChannelHandlerContext ctx) {
         this.context = ctx;
     }	 
	public StartInboundHandler(EventExecutor eventExecutor, ChannelGroup channels) {
		super();
		this.eventExecutor = eventExecutor;		
		this.channels=channels;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx){	
		 channels.add(ctx.channel());		
		System.out.println("From startInboundHandler     " + Thread.currentThread());
		count=count+1;
		}
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		System.out.println("From startInboundHandler  channel read  "+ctx);
			ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "Server received: " + in.toString(CharsetUtil.UTF_8));
            ctx.write(in); 
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
    	ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);   
    }	
}
