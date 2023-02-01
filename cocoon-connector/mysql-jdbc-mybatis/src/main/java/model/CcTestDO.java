package model;

import lombok.Data;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-10-08 10:48
 **/
@Data
public class CcTestDO {

    private Long id;
    private String name;
    private int age;
    private boolean sex;
    private String createTime;
    private String updateTime;

}
