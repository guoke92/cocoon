package io.cocoon.flow.core.service.api;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface Task extends Executable, Retryable, Lifecycle {

    @Override
    default void start() {
        preProc();
        proc();
        postProc();
    }

    @Override
    default void retry() {
        start();
    }


    /**
     * 前置处理
     */
    void preProc();

    /**
     * 执行
     */
    void proc();

    /**
     * 后置处理
     */
    void postProc();

}
