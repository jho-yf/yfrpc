package cn.jho.yfrpc.serialization.jdk;

import cn.jho.yfrpc.common.exception.SerializerException;
import cn.jho.yfrpc.serialization.api.Serialization;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>JdkSerialization class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class JdkSerialization implements Serialization<Serializable> {

    @Override
    public <T extends Serializable> byte[] serialize(T obj) {
        if (obj == null) {
            throw new SerializerException("Serialize object could not be null.");
        }

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(obj);
            return os.toByteArray();
        } catch (IOException e) {
            throw new SerializerException(e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T deserialize(byte[] data, Class<T> cls) throws SerializerException {
        if (data == null) {
            throw new SerializerException("Deserialize data could not be null.");
        }

        try (ByteArrayInputStream is = new ByteArrayInputStream(data);
                ObjectInputStream in = new ObjectInputStream(is)) {
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializerException(e.getMessage(), e);
        }
    }

}
