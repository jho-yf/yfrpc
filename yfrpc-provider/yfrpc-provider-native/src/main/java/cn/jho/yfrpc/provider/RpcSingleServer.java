package cn.jho.yfrpc.provider;

import cn.jho.yfrpc.common.scanner.server.RpcServiceScanner;
import cn.jho.yfrpc.provider.common.server.base.BaseServer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>RpcSingleServer class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcSingleServer extends BaseServer {

    private static final Logger LOG = LoggerFactory.getLogger(RpcSingleServer.class);

    public RpcSingleServer(String serverAddress, String scanPackage) {
        super(serverAddress);
        try {
            this.handlerMap = RpcServiceScanner.scanRpcService(scanPackage);
        } catch (IOException e) {
            LOG.error("RPC Server init error: {}", e.getMessage(), e);
        }
    }

}
