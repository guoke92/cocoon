package io.cocoon.flow.core.service.api;

import io.cocoon.flow.core.service.enums.LifecycleStageEnum;
import lombok.Data;

import java.util.Map;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
@Data
public abstract class Task implements Executable, Retryable, Lifecycle {

    private LifecycleStageEnum stage = LifecycleStageEnum.INIT;

    @Override
    public void start() {
        try {
            run();
            onComplete();
        } catch (Exception e) {
            onError(e);
        }
    }

    @Override
    public void retry() {
        start();
    }

    /**
     * 实际执行
     */
    abstract void run();

    /**
     * 异常
     */
    abstract void onError(Exception e);

    /**
     * 完成
     */
    abstract void onComplete();

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
