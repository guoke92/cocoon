package io.cocoon.flow.core.service.enums;

/**
 * @author 15420
 */

public enum TaskType {

    BEGIN("BEGIN", "开始节点"),
    END("END", "终止节点"),
    FLOW("FLOW", "子流程节点"),
    ;


    private final String name;
    private final String desc;

    TaskType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
