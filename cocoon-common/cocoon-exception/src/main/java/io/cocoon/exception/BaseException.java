package io.cocoon.exception;

import java.text.MessageFormat;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-09-24 16:27
 **/
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = -7453540216985054651L;

    private String code;
    private String msg;

    public BaseException(String code, String msg, Throwable cause, Object... args) {
        super(MessageFormat.format(msg, args), cause);

        this.code = code;
        this.msg = msg;
    }

    public BaseException(IResultCode resultCode, Throwable cause, Object... args) {
        super(MessageFormat.format(resultCode.getMsg(), args), cause);

        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public BaseException(String code, String msg, Object... args) {
        super(MessageFormat.format(msg, args));

        this.code = code;
        this.msg = msg;
    }

    public BaseException(IResultCode resultCode, Object... args) {
        super(MessageFormat.format(resultCode.getMsg(), args));

        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

}
