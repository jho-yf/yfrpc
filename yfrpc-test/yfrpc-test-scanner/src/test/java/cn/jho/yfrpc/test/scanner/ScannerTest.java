package cn.jho.yfrpc.test.scanner;

import cn.jho.yfrpc.common.scanner.ClassScanner;
import cn.jho.yfrpc.common.scanner.server.RpcServiceScanner;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * <p>ScannerTest class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
class ScannerTest extends AbstractTests {

    @Test
    void testScannerClassNames() throws IOException {
        List<String> classNames = ClassScanner.getClassNames("cn.jho.yfrpc.test.scanner");
        classNames.forEach(System.out::println);
        assertFalse(classNames.isEmpty());
    }

    @Test
    void testScannerClassNamesByRpcService() throws IOException {
        assertDoesNotThrow(() -> RpcServiceScanner.scanRpcService("cn.jho.yfrpc.test.scanner"));
    }

}
