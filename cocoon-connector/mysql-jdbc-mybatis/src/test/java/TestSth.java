/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class TestSth {

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {

        Class.forName("org.apache.ibatis.binding.MapperProxy");
        //Method privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);

        //System.out.println("privateLookupIn = " + privateLookupIn);

    }
}
