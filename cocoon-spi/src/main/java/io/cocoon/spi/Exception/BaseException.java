package io.cocoon.spi.Exception;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-08-24 17:26
 **/
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = -8995083880193193548L;

    private String code;
    private String msg;

    public BaseException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(BaseCode baseCode, Throwable cause) {
        super(baseCode.getMsg(), cause);
        this.code = baseCode.getCode();
        this.msg = baseCode.getMsg();
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(BaseCode baseCode) {
        super(baseCode.getMsg());
        this.code = baseCode.getCode();
        this.msg = baseCode.getMsg();
    }

}
