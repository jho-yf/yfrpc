package cn.jho.yfrpc.common.exception;

import java.io.Serial;

/**
 * <p>SerializerException class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class SerializerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2153837919518692222L;

    public SerializerException(Throwable cause) {
        super(cause);
    }

    public SerializerException(String message) {
        super(message);
    }

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }

}
