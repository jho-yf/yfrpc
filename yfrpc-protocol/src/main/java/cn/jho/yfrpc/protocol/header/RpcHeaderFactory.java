package cn.jho.yfrpc.protocol.header;

import cn.jho.yfrpc.common.id.IdFactory;
import cn.jho.yfrpc.constants.RpcConstants;
import cn.jho.yfrpc.protocol.RpcType;

/**
 * {@link RpcHeader} 工厂类
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcHeaderFactory {

    private RpcHeaderFactory() {
        
    }

    public static RpcHeader getRequestHeader(String serializationType) {
        RpcHeader header = new RpcHeader();
        header.setMagic(RpcConstants.MAGIC);
        header.setRequestId(IdFactory.getId());
        header.setMsgType((byte) RpcType.REQUEST.getType());
        header.setStatus((byte) 0x1);
        header.setSerializationType(serializationType);
        return header;
    }

}
