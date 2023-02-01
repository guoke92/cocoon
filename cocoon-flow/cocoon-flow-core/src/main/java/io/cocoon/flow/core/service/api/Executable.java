package io.cocoon.flow.core.service.api;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface Executable {

    void preProc();

    void process();

    void postProc();

    void onFail();

    void onSuccess();

}
