package cn.jho.yfrpc.protocol;

import cn.jho.yfrpc.protocol.header.RpcHeader;
import java.io.Serial;
import java.io.Serializable;

/**
 * Rpc协议类
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcProtocol<T extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = 292789485166173277L;

    private RpcHeader header;
    private T body;

    public RpcHeader getHeader() {
        return header;
    }

    public void setHeader(RpcHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
