package io.cocoon.flow.core.service.field;

import io.cocoon.flow.core.model.SimpleBean;

import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws Exception {

        SimpleBean simpleBean = SimpleBean.builder()
                .name("张三")
                .age(19)
                .sex("男")
                .height(179.0)
                .build();

        Field nameField = Field.builder().fieldId("1").name("name").cnMame("姓名").build();
        Field ageField = Field.builder().fieldId("2").name("age").cnMame("年龄").build();
        Field sexField = Field.builder().fieldId("3").name("sex").cnMame("性别").build();
        Field heightField = Field.builder().fieldId("4").name("height").cnMame("身高").build();

        String name = (String) getFiledValue(simpleBean, nameField);
        System.out.println("name = " + name);
    }

    private static Object getFiledValue(SimpleBean simpleBean, Field field){
        try {
            Class<SimpleBean> simpleBeanClass = SimpleBean.class;
            java.lang.reflect.Field declaredField = simpleBeanClass.getDeclaredField(field.name);
            declaredField.setAccessible(true);
            return declaredField.get(simpleBean);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getFiledValue(Map<String,Object> map, Field field) {
        return map.get(field.name);
    }

    private static Object getFiledValue(List list, Field field, int index) {
        if(null == list || list.size() <= index){
            return null;
        }
        Object item = list.get(0);
        if(item instanceof Map){
            Map<String, Object> map = (Map<String, Object>) item;
            return getFiledValue(map, field);
        } else {
            // TODO
            return null;
        }
    }
}
