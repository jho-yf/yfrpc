package cn.jho.yfrpc.common.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID工厂类
 *
 * @author JHO xu-jihong@qq.com
 */
public class IdFactory {

    private static final AtomicLong REQUEST_ID_GEN = new AtomicLong(0);

    private IdFactory() {
    }

    public static long getId() {
        return REQUEST_ID_GEN.incrementAndGet();
    }

}
