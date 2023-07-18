package org.chench.springboot.scaffolding.vo;

/**
 * 返回的json数据格式封装
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.vo.JsonVO
 * @date 2023.07.17
 */
public class JsonVO {
    // 业务状态码
    private int code = 0;
    // 消息描述
    private String message = "";
    // 数据
    private Object data = null;

    public static JsonVO build() {
        JsonVO vo = new JsonVO(0, "", null);
        return vo;
    }

    public int getCode() {
        return code;
    }

    public JsonVO setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonVO setData(Object data) {
        this.data = data;
        return this;
    }

    private JsonVO() {}

    private JsonVO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
