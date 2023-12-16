package cn.jho.yfrpc.codec;

import static cn.jho.yfrpc.constants.RpcConstants.MAX_SERIALIZER_TYPE_COUNT;

import cn.jho.yfrpc.common.utils.SerializerUtils;
import cn.jho.yfrpc.constants.RpcConstants;
import cn.jho.yfrpc.protocol.RpcProtocol;
import cn.jho.yfrpc.protocol.RpcType;
import cn.jho.yfrpc.protocol.header.RpcHeader;
import cn.jho.yfrpc.protocol.request.RpcRequest;
import cn.jho.yfrpc.protocol.response.RpcResponse;
import cn.jho.yfrpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>RpcDecoder class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcDecoder extends ByteToMessageDecoder implements RpcCodec {

    @Override
    public final void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
            throws Exception {
        if (byteBuf.readableBytes() < RpcConstants.HEADER_TOTAL_LEN) {
            return;
        }
        byteBuf.markReaderIndex();

        short magic = byteBuf.readShort();
        if (magic != RpcConstants.MAGIC) {
            throw new IllegalArgumentException("Illegal magic number: " + magic);
        }

        byte msgType = byteBuf.readByte();
        RpcType rpcType = RpcType.findByType(msgType);
        if (rpcType == null) {
            throw new UnsupportedOperationException("Unsupported for Rpc Type '" + msgType + "'");
        }

        byte status = byteBuf.readByte();
        long requestId = byteBuf.readLong();

        ByteBuf serializationTypeByteBuf = byteBuf.readBytes(MAX_SERIALIZER_TYPE_COUNT);
        String serializationType = SerializerUtils.substring(serializationTypeByteBuf.toString(StandardCharsets.UTF_8));

        int dataLen = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLen) {
            byteBuf.resetReaderIndex();
            throw new IllegalArgumentException("Illegal data format.");
        }

        byte[] data = new byte[dataLen];
        byteBuf.readBytes(data);

        RpcHeader header = new RpcHeader();
        header.setMagic(magic);
        header.setStatus(status);
        header.setRequestId(requestId);
        header.setMsgType(msgType);
        header.setSerializationType(serializationType);
        header.setMsgLength(dataLen);

        Serialization<Serializable> serialization = getJdkSerialization();
        switch (rpcType) {
            case REQUEST -> {
                RpcRequest request = serialization.deserialize(data, RpcRequest.class);
                if (request != null) {
                    RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
                    protocol.setHeader(header);
                    protocol.setBody(request);
                    list.add(protocol);
                }
            }
            case RESPONSE -> {
                RpcResponse response = serialization.deserialize(data, RpcResponse.class);
                if (response != null) {
                    RpcProtocol<RpcResponse> protocol = new RpcProtocol<>();
                    protocol.setHeader(header);
                    protocol.setBody(response);
                    list.add(protocol);
                }
            }
            case HEARTBEAT -> {
            }
        }
    }

}
