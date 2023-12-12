package cn.jho.yfrpc.protocol.response;

import cn.jho.yfrpc.protocol.base.RpcMessage;
import java.io.Serial;

/**
 * <p>RpcResponse class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class RpcResponse extends RpcMessage {

    @Serial
    private static final long serialVersionUID = 6561881622914479509L;

    private String error;
    private Object result;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
