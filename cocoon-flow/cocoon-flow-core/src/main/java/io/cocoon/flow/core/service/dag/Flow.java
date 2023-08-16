package io.cocoon.flow.core.service.dag;

/**
 * @author 15420
 */
public class Flow{
    String name;
    String flowId;
    Task head;

    Flow(String name) {
        this.name = name;
    }

    public void setHead(Task head) {
        this.head = head;
    }

    public Task getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}