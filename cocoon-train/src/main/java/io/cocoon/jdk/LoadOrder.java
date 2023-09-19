package io.cocoon.jdk;

public class LoadOrder {

    public static void main(String[] args) {
        new Child();
    }
}

class Parent {
    public Parent() {
        System.out.println("Parent constructor");
    }

    {
        System.out.println("Parent block");
    }

    static {
        System.out.println("Parent static block");
    }

    static int staticParentA = 1;
    int parentA = 1;
}

class Child extends Parent {

    public Child() {
        System.out.println("Child constructor");
    }

    int fieldA = 1;

    {
        System.out.println("staticParentA = " + staticParentA);
        System.out.println("staticA = " + staticA);
        System.out.println("parentA = " + parentA);
        System.out.println("fieldA = " + fieldA);
//        System.out.println("fieldB = " + fieldB); // compile error: Illegal forward reference
        System.out.println("Child block");
    }

    int fieldB = 1;

    {
        System.out.println("Child block2");
        System.out.println("fieldB = " + fieldB);
    }

    private static int staticA = 1;

    static {
        System.out.println("staticParentA = " + staticParentA);
        System.out.println("staticA = " + staticA);
//        System.out.println("staticB = " + staticB); // compile error: Illegal forward reference
//        System.out.println("parentA = " + parentA);// compile error: Non-static field 'fieldA' cannot be referenced from a static context
//        System.out.println("fieldA = " + fieldA);// compile error: Non-static field 'fieldA' cannot be referenced from a static context
        System.out.println("Child static block");
    }

    private static int staticB = 1;
}
