package cn.jho.rpc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RPC服务消费者
 *
 * @author JHO xu-jihong@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Autowired
public @interface RpcReference {

    /**
     * 版本号
     */
    String version() default "1.0.0";

    /**
     * 注册中心类型，支持类型：zookeeper、nacos、etcd、consul
     */
    String registryType() default "zookeeper";

    /**
     * 注册地址
     */
    String registryAddress() default "127.0.0.1:1205";

    /**
     * 负载均衡类型，默认基于zookeeper的一致性Hash
     */
    String loadBalanceType() default "zkconsistenthash";

    /**
     * 序列化类型，支持类型：protostuff、kryo、json、hessian2、fst
     */
    String serializetionType() default "protostuff";

    /**
     * 超时时间。默认5s
     */
    long timeout() default 5000;

    /**
     * 是否开启异步
     */
    boolean async() default false;

    /**
     * 是否单向调用
     */
    boolean oneway() default false;

    /**
     * 代理类型
     * <p>
     * jdk: JDK代理
     * <p>
     * javassist: javassist代理
     * <p>
     * cglib: cglib代理
     */
    String proxy() default "jdk";

    /**
     * 服务分组
     */
    String group() default "";

}
