package io.cocoon.flow.core.service.api;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface Retryable {

    boolean retryable();

    void retry();

    default void retry(int times) {
        for (int i = 0; i < times; i++) {
            retry();
        }
    }

}
