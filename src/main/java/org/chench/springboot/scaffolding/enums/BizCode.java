package org.chench.springboot.scaffolding.enums;

/**
 * 以枚举方式定义业务状态码
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.enums.BizCode
 * @date 2023.07.17
 */
public enum BizCode {
    // 仿照http状态码定义业务状态码
    // 2000: 请求执行成功
    // 4000-4999: 客户端错误，可以扩展具体的参数错误，如：参数错误，禁止访问等
    // 5000-5999: 服务端错误，可以扩展具体的执行错误，也可以统一使用50000表示服务端错误

    /** 成功 */
    SUCCESS(2000, "Success"),
    /** 参数错误 */
    PARAM_ERROR(4000, "Param error"),
    /** 未授权 */
    UNAUTHORIZED(4001, "Unauthorized"),
    /** 用户名或密码错误 */
    PAYMENT_REQUIRED(4002, "User name or password error"),
    /** 禁止访问 */
    FORBIDDEN(40003, "Forbidden"),
    /** 资源未找到 */
    NOT_FOUND(40004, "Not Found"),
    /** 访问不允许被访问 */
    METHOD_NOT_ALLOWED(4005, "Method Not Allowed"),
    /** 服务内部错误 */
    INTERNAL_SERVER_ERROR(5000, "Internal Server Error"),
    /** 未知错误 */
    UNKNOWN_ERROR(5001, "Unknown Error");

    private int code = 0;
    private String message = "";

    BizCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
