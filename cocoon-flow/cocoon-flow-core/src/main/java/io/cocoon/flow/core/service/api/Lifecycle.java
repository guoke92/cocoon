package io.cocoon.flow.core.service.api;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface Lifecycle {

    void init();

    void start();

    void stop();

    void retry();

    void destroy();

    String getStage();

}
