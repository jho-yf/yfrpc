package cn.jho.yfrpc.test.provider.single;

import cn.jho.yfrpc.provider.RpcSingleServer;
import org.junit.jupiter.api.Test;

/**
 * <p>RpcSingleServerTest class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
class RpcSingleServerTest extends AbstractTests {

    @Test
    void startRpcSingleServer() {
        RpcSingleServer server = new RpcSingleServer("127.0.0.1:1206", "cn.jho.yfrpc.test");
        assertDoesNotThrow(server::startNettyServer);
    }

}
