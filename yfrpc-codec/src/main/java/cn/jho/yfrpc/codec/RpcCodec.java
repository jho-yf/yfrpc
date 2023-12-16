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

    default Serialization<Serializable> getJdkSerialization() {
        return new JdkSerialization();
    }

}
