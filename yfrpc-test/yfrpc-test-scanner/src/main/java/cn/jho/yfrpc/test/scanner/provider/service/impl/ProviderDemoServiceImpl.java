package cn.jho.yfrpc.test.scanner.provider.service.impl;

import cn.jho.rpc.anno.RpcService;
import cn.jho.yfrpc.test.scanner.provider.service.DemoService;

/**
 * <p>ProviderDemoServiceImpl class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
@RpcService(
        interfaceClass = DemoService.class,
        interfaceClassName = "cn.jho.yfrpc.test.scanner.service.DemoService",
        version = "1.0.0",
        group = "rpc-test-group")
public class ProviderDemoServiceImpl implements DemoService {

}
