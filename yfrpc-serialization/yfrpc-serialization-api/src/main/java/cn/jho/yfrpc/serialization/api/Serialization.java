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
     *
     * @param <T> 序列化对象类型
     * @param obj 序列化对象
     * @return 序列化结果
     * @throws SerializerException 序列化异常
     */
    <T extends E> byte[] serialize(T obj) throws SerializerException;

    /**
     * 反序列化
     *
     * @param <T>  反序列化对象类型
     * @param data 需要反序列化的数据
     * @param cls  反序列化目标 Class 对象
     * @return 反序列化结果
     * @throws SerializerException 反序列化异常
     */
    <T extends E> T deserialize(byte[] data, Class<T> cls) throws SerializerException;

}
