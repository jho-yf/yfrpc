package cn.jho.yfrpc.test.scanner.consumer.service.impl;

import cn.jho.rpc.anno.RpcReference;
import cn.jho.yfrpc.test.scanner.consumer.service.ConsumerBusinessService;
import cn.jho.yfrpc.test.scanner.provider.service.DemoService;

/**
 * <p>ConsumerBusinessServiceImpl class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class ConsumerBusinessServiceImpl implements ConsumerBusinessService {

    @RpcReference(registryType = "zookeeper",
            registryAddress = "127.0.0.1:1205",
            version = "1.0.0",
            group = "rpc-test-group")
    private DemoService demoService;

}
