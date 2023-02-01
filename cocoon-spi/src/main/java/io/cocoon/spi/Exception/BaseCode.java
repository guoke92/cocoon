package io.cocoon.spi.Exception;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-08-24 17:29
 **/
public enum BaseCode {

    /**
     * S
     */
    S0001("S0001", "系统异常");

    private String code;
    private String msg;

    BaseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
