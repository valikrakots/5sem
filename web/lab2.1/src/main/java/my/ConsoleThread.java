package my;

import java.util.Scanner;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

public class ConsoleThread implements Runnable{
	ChannelHandlerContext ctx;
	ChannelGroup channels;
	public ConsoleThread(  ChannelGroup channels) {
		super();		
		this.channels=channels;
	}

	public void run() {
		System.out.println("Start console thread  "+ Thread.currentThread().getName());
	try(Scanner in = new Scanner(System.in);)
	{
    while (true) {
        if (in.hasNext()) {
            String msg = in.nextLine();
     
            for (Channel ch : channels) {      
            	ch.pipeline().context(StartInboundHandler.class).fireUserEventTriggered(msg);
                    }
            System.out.println(msg);  
            System.out.println("From console thread   " + Thread.currentThread());
            if (msg.equals("exit")) {      
                break;
            }  
        }    
    }
	}
}
}
