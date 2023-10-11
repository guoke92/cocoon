package io.cocoon.flow.core.service.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @author 15420
 */
public abstract class Stream implements Executable, Observable{

    List<Observer> observers = new ArrayList<>();

    Stream log(){
        // new StreamLog
        return this;
    }

}
