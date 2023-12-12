package cn.jho.yfrpc.common.scanner.server;

import cn.jho.rpc.anno.RpcService;
import cn.jho.yfrpc.common.scanner.ClassScanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link cn.jho.rpc.anno.RpcService} 注解扫描器
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcServiceScanner extends ClassScanner {

    private static final Logger LOG = LoggerFactory.getLogger(RpcServiceScanner.class);

    /**
     * 扫描指定包下带有@{@link RpcService}的所有类
     *
     * @param scanPkg 指定包路径
     * @return Rpc服务
     * @throws IOException 获取包路径下所有全限定类名异常
     */
    public static Map<String, Object> scanRpcService(String scanPkg) throws IOException {
        Map<String, Object> handlerMap = new HashMap<>();
        List<String> classNames = getClassNames(scanPkg);
        if (classNames.isEmpty()) {
            return handlerMap;
        }

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                RpcService rpcService = clazz.getAnnotation(RpcService.class);
                if (rpcService != null) {
                    LOG.info("Rpc Service => {}", clazz.getName());
                    LOG.info(
                            "Rpc Service Meta => \ninterfaceClass: {} \ninterfaceClassName: {} \nversion: {}\ngroup: {}",
                            rpcService.interfaceClass(), rpcService.interfaceClassName(),
                            rpcService.version(), rpcService.group());
                }
            } catch (ClassNotFoundException e) {
                LOG.error("Failed to scan classes: {}", e.getMessage(), e);
            }
        }

        return handlerMap;
    }


}
