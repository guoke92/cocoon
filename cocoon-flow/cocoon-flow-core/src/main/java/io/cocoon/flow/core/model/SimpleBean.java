package io.cocoon.flow.core.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 15420
 */
@Data
@Builder
public class SimpleBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String sex;

    private Integer age;

    private Double height;
}
