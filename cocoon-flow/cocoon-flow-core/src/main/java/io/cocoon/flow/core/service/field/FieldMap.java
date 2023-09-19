package io.cocoon.flow.core.service.field;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 15420
 */
@Data
@Builder
public class FieldMap implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * flowId
     */
    String flowId;
    /**
     * 绑定在source task 上
     */
    String sourceTaskId;
    /**
     * 绑定在target task 上
     */
    String targetTaskId;
    /**
     * 源字段ID
     */
    String sourceFieldId;
    /**
     * 目标字段ID
     */
    String targetFieldId;
}
