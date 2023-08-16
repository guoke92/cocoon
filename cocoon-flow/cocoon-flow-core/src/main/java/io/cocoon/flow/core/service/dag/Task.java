package io.cocoon.flow.core.service.dag;

import java.util.Map;

/**
 * @author 15420
 */
public class Task {
    String id;
    String name;
    String type;
    String desc;
    Map<String, Object> params;
    String executeId;
    String script;
}