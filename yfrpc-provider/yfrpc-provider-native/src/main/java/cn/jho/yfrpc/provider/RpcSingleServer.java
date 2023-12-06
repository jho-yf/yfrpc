package cn.jho.yfrpc.provider;

import cn.jho.yfrpc.provider.common.server.base.BaseServer;

/**
 * <p>RpcSingleServer class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcSingleServer extends BaseServer {

    public RpcSingleServer(String serverAddress, String scanPackage) {
        super(serverAddress);
    }

}
