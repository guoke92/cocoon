package io.cocoon.exception;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-09-24 16:28
 **/
public interface IExceptionAssert extends IAssert, IResultCode{

    @Override
    default BaseException newException(Object... args) {
        return new BaseException(this, args);
    }

    @Override
    default BaseException newException(Throwable throwable, String... args) {
        return new BaseException(this, throwable, args);
    }
}
