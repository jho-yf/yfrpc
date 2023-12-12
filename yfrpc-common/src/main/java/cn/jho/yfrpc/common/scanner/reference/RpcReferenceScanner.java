package cn.jho.yfrpc.common.scanner.reference;

import cn.jho.rpc.anno.RpcReference;
import cn.jho.yfrpc.common.scanner.server.RpcServiceScanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link cn.jho.rpc.anno.RpcReference} 注解扫描器
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcReferenceScanner extends RpcServiceScanner {

    private static final Logger LOG = LoggerFactory.getLogger(RpcReferenceScanner.class);

    public static Map<String, Object> scanRpcReference(String scanPkg) throws IOException {
        Map<String, Object> handerMap = new HashMap<>();

        List<String> classNames = getClassNames(scanPkg);
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Stream.of(clazz.getDeclaredFields()).forEach(field -> {
                    RpcReference rpcReference = field.getAnnotation(RpcReference.class);
                    if (rpcReference != null) {
                        LOG.info("Rpc Reference => {}", clazz.getName());
                        LOG.info("Field Name => {}", field.getName());
                        LOG.info("Rpc Service Meta => {}", rpcReference);
                    }
                });
            } catch (ClassNotFoundException e) {
                LOG.error("Failed to scan classes: {}", e.getMessage(), e);
            }
        }

        return handerMap;
    }


}
