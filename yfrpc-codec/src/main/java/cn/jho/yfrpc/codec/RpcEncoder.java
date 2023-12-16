package cn.jho.yfrpc.codec;

import cn.jho.yfrpc.common.utils.SerializerUtils;
import cn.jho.yfrpc.protocol.RpcProtocol;
import cn.jho.yfrpc.protocol.header.RpcHeader;
import cn.jho.yfrpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * <p>RpcEncoder class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<Serializable>> implements RpcCodec {

    @Override
    public final void encode(ChannelHandlerContext channelHandlerContext,
            RpcProtocol<Serializable> protocol, ByteBuf byteBuf) throws Exception {
        RpcHeader header = protocol.getHeader();
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getMsgType());
        byteBuf.writeByte(header.getStatus());
        byteBuf.writeLong(header.getRequestId());

        String serializationType = header.getSerializationType();
        byteBuf.writeBytes(SerializerUtils.paddingString(serializationType).getBytes(StandardCharsets.UTF_8));

        // TODO serialization可扩展
        Serialization<Serializable> serialization = getJdkSerialization();
        byte[] data = serialization.serialize(protocol.getBody());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }
}
