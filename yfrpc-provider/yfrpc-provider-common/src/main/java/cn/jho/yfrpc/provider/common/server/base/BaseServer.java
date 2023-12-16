package cn.jho.yfrpc.provider.common.server.base;

import cn.jho.yfrpc.codec.RpcDecoder;
import cn.jho.yfrpc.codec.RpcEncoder;
import cn.jho.yfrpc.provider.common.handler.RpcProviderHandler;
import cn.jho.yfrpc.provider.common.server.api.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <p>BaseServer class.</p>
 *
 * @author JHO xujihong@bingosoft.net
 */
public class BaseServer implements Server {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected String host = "127.0.0.1";
    protected int port = 1206;
    protected Map<String, Object> handlerMap = new HashMap<>();

    public BaseServer() {

    }

    public BaseServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public BaseServer(String serverAddress) {
        if (StringUtils.hasLength(serverAddress)) {
            String[] split = serverAddress.split(":");
            this.host = split[0];
            this.port = Integer.parseInt(split[1]);
        }
    }

    @Override
    public void startNettyServer() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                .addLast(new RpcEncoder())
                                .addLast(new RpcDecoder())
                                .addLast(new RpcProviderHandler(handlerMap));
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture future = bootstrap.bind(host, port).sync();
            log.info("RPC server started on {}:{}", host, port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("RPC server start error", e);
            Thread.currentThread().interrupt();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
