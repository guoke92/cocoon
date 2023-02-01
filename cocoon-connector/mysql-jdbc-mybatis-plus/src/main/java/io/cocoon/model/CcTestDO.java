package io.cocoon.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-10-08 10:48
 **/
@Data
public class CcTestDO {

    @TableId
    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private int age;

    @TableField("sex")
    private boolean sex;

    @TableField("update_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

}
