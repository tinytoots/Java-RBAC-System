package com.mmall.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

// 用lombok工具直接生成get set方法
@Getter
@Setter
public class JsonData {

    // 返回结果
    private boolean ret;

    // 异常消息
    private String msg;

    // 正常返回给前台的数据
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    // 全局方法
    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    // success时有时无需返回data
    public static JsonData success() {
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }






}
