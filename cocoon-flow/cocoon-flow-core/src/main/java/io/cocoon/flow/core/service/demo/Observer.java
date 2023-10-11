package io.cocoon.flow.core.service.demo;

/**
 * @author 15420
 */
public abstract class Observer implements Observable{

    public abstract void onExecute();
    public abstract void onError();
    public abstract void onCompleted();

}
