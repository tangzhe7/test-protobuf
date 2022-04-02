package cc.protobuf;


import java.io.IOException;

import com.google.gson.Gson;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

/**
 * 相互转化的两个对象的getter和setter字段要完全的匹配。
 * 此外，对于ProtoBean中的enum和bytes，与POJO转化时遵循如下的规则：
 * <ol>
 *     <li>enum -> String</li>
 *     <li>bytes -> base64 String</li>
 * </ol>
 * @author Yang Guanrong
 * @date 2019/08/18 23:44
 */
public class ProtoBeanUtils {

    /**
     * 将ProtoBean对象转化为POJO对象
     *
     * @param destPojoClass 目标POJO对象的类类型
     * @param sourceMessage 含有数据的ProtoBean对象实例
     * @param <PojoType> 目标POJO对象的类类型范型
     * @return
     * @throws IOException
     */
    public static <PojoType> PojoType toPojoBean(Class<PojoType> destPojoClass, Message sourceMessage)
            throws IOException {
        if (destPojoClass == null) {
            throw new IllegalArgumentException
                    ("No destination pojo class specified");
        }
        if (sourceMessage == null) {
            throw new IllegalArgumentException("No source message specified");
        }
        String json = JsonFormat.printer().print(sourceMessage);
        return new Gson().fromJson(json, destPojoClass);
    }

    /**
     * 将POJO对象转化为ProtoBean对象
     *
     * @param destBuilder 目标Message对象的Builder类
     * @param sourcePojoBean 含有数据的POJO对象
     * @return
     * @throws IOException
     */
    public static void toProtoBean(Message.Builder destBuilder, Object sourcePojoBean) throws IOException {
        if (destBuilder == null) {
            throw new IllegalArgumentException
                    ("No destination message builder specified");
        }
        if (sourcePojoBean == null) {
            throw new IllegalArgumentException("No source pojo specified");
        }
        String json = new Gson().toJson(sourcePojoBean);
        JsonFormat.parser().merge(json, destBuilder);
    }
}