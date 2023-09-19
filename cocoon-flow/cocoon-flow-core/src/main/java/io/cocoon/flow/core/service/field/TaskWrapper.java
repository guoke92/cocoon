package io.cocoon.flow.core.service.field;

import io.cocoon.flow.core.utils.IdUtil;

/**
 * @author 15420
 */
public class TaskWrapper {

    private final String wrapperId;

    private String taskName;

    public TaskWrapper(String task) {
        this.wrapperId = IdUtil.getId("WRAPPER");
        this.taskName = task;
    }

    public String getWrapperId() {
        return wrapperId;
    }
}
