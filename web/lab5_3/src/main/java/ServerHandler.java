import java.util.ArrayList;
import java.util.List;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    // List of connected client channels.
    static final List<Channel> channels = new ArrayList<Channel>();
    Logger logger = LogManager.getLogger(ServerHandler.class.getName());

    /*
     * Whenever client connects to server through channel, add his channel to the
     * list of channels.
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        logger.info(" Server handler added for new client.");
        System.out.println("Client joined - " + ctx);
        channels.add(ctx.channel());

    }

    /*
     * When a message is received from client, send that message to all channels.
     * FOr the sake of simplicity, currently we will send received chat message to
     * all clients instead of one specific client. This code has scope to improve to
     * send message to specific client as per senders choice.
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String msg1 = "Hi";
        ctx.channel().writeAndFlush("-> " + msg1 + '\n');
        logger.info(" Message posted.");

    }

    /*
     * In case of exception, close channel. One may chose to custom handle exception
     * & have alternative logical flows.
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Closing connection for client - " + ctx);
        logger.info(" Exception for client caused.");
        ctx.close();
    }
}