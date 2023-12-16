package cn.jho.yfrpc.serialization.api;

import cn.jho.yfrpc.common.exception.SerializerException;

/**
 * 序列化接口
 *
 * @author JHO xu-jihong@qq.com
 */
public interface Serialization<E> {

    /**
     * 序列化
     */
    <T extends E> byte[] serialize(T obj) throws SerializerException;

    /**
     * 反序列化
     */
    <T extends E> T deserialize(byte[] data, Class<T> cls) throws SerializerException;

}
