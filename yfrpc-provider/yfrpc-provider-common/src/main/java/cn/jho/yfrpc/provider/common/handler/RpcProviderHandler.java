package cn.jho.yfrpc.provider.common.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>RpcProviderHandler class.</p>
 *
 * @author JHO xujihong@bingosoft.net
 */
public class RpcProviderHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(RpcProviderHandler.class);

    private final Map<String, Object> handlerMap;

    public RpcProviderHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOG.info("RpcProviderHandler receive msg: {}", msg);
        LOG.info("handlerMap: {}", handlerMap);
        ctx.writeAndFlush(msg);
    }

}
