package io.cocoon.flow.core.service.api;

import io.cocoon.flow.core.service.enums.LifecycleStageEnum;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface Lifecycle {

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 结束
     */
    void finish();

    /**
     * 获取当前生命周期阶段
     * @return 当前生命周期阶段
     */
    LifecycleStageEnum getStage();
}
