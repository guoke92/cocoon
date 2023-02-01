package io.cocoon.exception;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-10-08 11:30
 **/
public class ExceptionTest {

    public static void main(String[] args) {

        ResultCode.IS_NULL.notBlank("  ", "姓名");
    }

}
