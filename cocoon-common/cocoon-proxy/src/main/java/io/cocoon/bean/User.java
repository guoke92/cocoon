package io.cocoon.bean;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class User implements IUser {
    private Long age;
    private String name;

    private String desc;

    public void init(String desc){
        this.desc = desc;
    }

    public User(){

    }

    public User(Long age, String name) {
        this.age = age;
        this.name = name;
    }

    public void saySth() {
        System.out.println("age = " + age);
        System.out.println("name = " + name);
        System.out.println("desc = " + desc);
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
