package producer;

import com.alibaba.fastjson.JSON;

public class JSONParser {
    public static <Type> String toJSONString(Type object) {
        // 将某种(Class-Type)对象(Type : Object)转换成JSON String对象
        try {
            return JSON.toJSONString(object);
        }catch (Exception e){
            System.out.println("当前Java对象（实例）无法转换成json格式 - 1");

        }
        return "当前Java对象（实例）无法转换成json格式 - 2";

    }

//    public static JSON toJSON(String str) {
//        return "当前Java对象（实例）无法转换成json格式";
//    }

}
