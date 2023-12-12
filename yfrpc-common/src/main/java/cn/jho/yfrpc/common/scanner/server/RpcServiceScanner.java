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
     * 扫描指定包下带有 @{@link RpcService} 注解的所有类实例
     *
     * @param scanPkg 指定包路径
     * @return 带有 @{@link RpcService} 注解的所有类实例
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
                    String serviceName = getServiceName(rpcService);
                    String key = serviceName.concat(rpcService.version()).concat(rpcService.group());
                    handlerMap.put(key, clazz.getDeclaredConstructor().newInstance());
                }
            } catch (Exception e) {
                LOG.error("Failed to scan classes: {}", e.getMessage(), e);
            }
        }

        return handlerMap;
    }

    private static String getServiceName(RpcService rpcService) {
        Class<?> clazz = rpcService.interfaceClass();
        if (clazz == void.class || clazz == Void.class) {
            return rpcService.interfaceClassName();
        }

        String serviceName = clazz.getName();
        if (serviceName.trim().isEmpty()) {
            serviceName = rpcService.interfaceClassName();
        }

        return serviceName;
    }


}
