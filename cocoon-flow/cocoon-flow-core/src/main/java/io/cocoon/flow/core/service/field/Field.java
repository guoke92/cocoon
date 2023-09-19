package io.cocoon.flow.core.service.field;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 15420
 */
@Data
@Builder
public class Field implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 子段ID
     */
    String fieldId;
    /**
     * 字段名称
     */
    String name;
    /**
     * 中文名称
     */
    String cnMame;
    /**
     * 字段类型
     */
    String type;
    /**
     * 字段注释描述
     */
    String comment;
    /**
     * 是否可为空
     */
    Boolean nullable;
    /**
     * 默认值
     */
    Object defaultValue;
    /**
     * 字段长度
     */
    Integer length;
    /**
     * 字段顺序编号
     */
    Integer order;

    // TODO 创建人、更新人、创建时间、更新时间 等

}
