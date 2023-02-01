package io.cocoon.bean;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class People {
    private Long age;
    private String name;

    //public People(){
    //    this.age = 1L;
    //    this.name = "麻子";
    //}

    public People(Long age, String name) {
        this.age = age;
        this.name = name;
    }

    public void saySth() {
        System.out.println("age = " + age);
        System.out.println("name = " + name);
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
