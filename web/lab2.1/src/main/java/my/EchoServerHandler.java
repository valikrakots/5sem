package my;

import java.nio.charset.Charset;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;


//@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {	
	ChannelHandlerContext context;
	Charset utf8 = Charset.forName("UTF-8");
	
	 public void beforeAdd(ChannelHandlerContext ctx) {
        this.context = ctx;
	 }
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        }

    @Override
	public void userEventTriggered(ChannelHandlerContext ctx,
            Object evt){
		
		System.out.println("From echoServerHandler     " + Thread.currentThread());		  
		ByteBuf buf = Unpooled.copiedBuffer((String)evt, utf8);		
		ctx.writeAndFlush( buf);
	
	
    }
    
    
}
