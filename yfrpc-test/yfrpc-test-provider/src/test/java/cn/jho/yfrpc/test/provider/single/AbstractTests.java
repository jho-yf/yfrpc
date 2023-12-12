package cn.jho.yfrpc.test.provider.single;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

/**
 * <p>AbstractTests class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public abstract class AbstractTests extends Assertions {

    @BeforeAll
    static void init() {
        // 显式配置Log4j默认环境
        BasicConfigurator.configure();
    }

}
