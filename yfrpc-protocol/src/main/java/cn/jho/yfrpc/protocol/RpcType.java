package cn.jho.yfrpc.protocol;

/**
 * Rpc消息枚举类型
 *
 * @author JHO xu-jihong@qq.com
 */
public enum RpcType {

    /**
     * 请求消息
     */
    REQUEST(1),

    /**
     * 响应消息
     */
    RESPONSE(2),

    /**
     * 心跳
     */
    HEARTBEAT(3);

    private final int type;

    RpcType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static RpcType findByType(int type) {
        for (RpcType value : RpcType.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return null;
    }

}
