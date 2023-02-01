package io.cocoon.exception;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-10-08 11:27
 **/
public enum ResultCode implements IExceptionAssert{
    SYSTEM_EXCEPTION("9999", "系统其他异常"),
    NOT_NULL("0001", "{0}不能为空"),
    IS_NULL("0001", "{0}必须为空"),

    ;

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
