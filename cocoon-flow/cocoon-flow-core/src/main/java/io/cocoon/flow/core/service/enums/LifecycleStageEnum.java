package io.cocoon.flow.core.service.enums;

/**
 * 生命周期各阶段枚举
 * @author 15420
 */
public enum LifecycleStageEnum {

    /**
     * 生命周期各阶段枚举
     */
    INIT(0, "初始化"),
    START(1, "开始"),
    STOP(2, "停止"),
    FINISH(3, "结束");

    private final Integer code;
    private final String desc;

    LifecycleStageEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
