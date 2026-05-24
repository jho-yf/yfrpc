package cn.jho.yfrpc.codec;

import cn.jho.yfrpc.serialization.api.Serialization;
import cn.jho.yfrpc.serialization.jdk.JdkSerialization;
import java.io.Serializable;

/**
 * <p>RpcCodec class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public interface RpcCodec {

    /**
     * 获取JDK序列化器
     *
     * @return {@link JdkSerialization} 对象
     */
    default Serialization<Serializable> getJdkSerialization() {
        return new JdkSerialization();
    }

}
