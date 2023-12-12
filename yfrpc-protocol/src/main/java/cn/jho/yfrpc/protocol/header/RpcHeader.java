package cn.jho.yfrpc.protocol.header;

import java.io.Serial;
import java.io.Serializable;

/**
 * 消息头：固定32个字节
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcHeader implements Serializable {

    /*
    +---------------------------------------------------------------+
    | 魔数 2byte | 报文类型 1byte | 状态 1byte |     消息 ID 8byte      |
    +---------------------------------------------------------------+
    |           序列化类型 16byte      |        数据长度 4byte          |
    +---------------------------------------------------------------+
     */

    @Serial
    private static final long serialVersionUID = -918736188382829474L;

    /**
     * 魔数 2字节
     */
    private short magic;

    /**
     * 报文类型 1字节
     */
    private byte msgType;

    /**
     * 状态 1字节
     */
    private byte status;

    /**
     * 消息ID 8字节
     */
    private long requestId;

    /**
     * 序列化类型 16字节，不足16字节后面补0
     */
    private String serializationType;

    /**
     * 消息长度 4字节
     */
    private int msgLength;

    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getSerializationType() {
        return serializationType;
    }

    public void setSerializationType(String serializationType) {
        this.serializationType = serializationType;
    }

    public int getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(int msgLength) {
        this.msgLength = msgLength;
    }
}
