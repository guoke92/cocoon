package io.cocoon.flow.core.service.api;

import io.cocoon.flow.core.service.enums.LifecycleStageEnum;

/**
 * 抽象任务
 *
 * @author 15420
 */
public abstract class AbstractTask implements Task {

    private LifecycleStageEnum stage = LifecycleStageEnum.INIT;

    @Override
    public void start() {
        stage = LifecycleStageEnum.START;
        run();
    }

    protected abstract void run();

    @Override
    public void stop() {
        stage = LifecycleStageEnum.STOP;
    }

    @Override
    public void finish() {
        stage = LifecycleStageEnum.FINISH;
    }

    @Override
    public LifecycleStageEnum getStage() {
        return stage;
    }
}
