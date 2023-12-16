package cn.jho.yfrpc.common.utils;

import static cn.jho.yfrpc.constants.RpcConstants.MAX_SERIALIZER_TYPE_COUNT;

import java.util.stream.IntStream;

/**
 * 序列化工具类
 *
 * @author JHO xu-jihong@qq.com
 */
public class SerializerUtils {

    /**
     * 序列化拼接字符
     */
    public static final String PADDING_STRING = "0";

    private SerializerUtils() {
    }

    /**
     * 为长度不足16的字符串后面补0
     *
     * @param str 原始字符串
     * @return 补0后的字符串
     */
    public static String paddingString(String str) {
        str = transNullToEmpty(str);
        if (str.length() >= MAX_SERIALIZER_TYPE_COUNT) {
            return str;
        }
        int paddingCount = MAX_SERIALIZER_TYPE_COUNT - str.length();
        StringBuilder sb = new StringBuilder(str);
        IntStream.range(0, paddingCount).forEach(i -> sb.append(PADDING_STRING));
        return sb.toString();
    }

    public static String substring(String str) {
        str = transNullToEmpty(str);
        return str.replace(SERIALIZER_TYPE_PADDING_STRING, "");
    }

    public static String transNullToEmpty(String str) {
        return str == null ? "" : str;
    }

}
