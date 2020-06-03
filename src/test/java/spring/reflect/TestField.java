package spring.reflect;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class TestField {
@Validated
    public  void testSpringANotBlank(){

    }

    @Test
    public void t() throws Exception {
//        Field[] fields = TestAutow.class.getDeclaredFields();
//        Field field = TestAutow.class.getDeclaredField("i");
//        System.out.println(field);
//        System.out.println(fields.length);
//        System.out.println(fields[0]);
        TestAutow ta = new TestAutow();
        Class<? extends  TestAutow> clazz = ta.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.asList(fields).stream().forEach(System.out::println);
        System.out.println(ta.getI()+":"+ta.getStr());

        Field field_i=clazz.getDeclaredField("i");
        //获得许可
        field_i.setAccessible(true);
        field_i.set(ta,7);
        System.out.println(ta.getI()+":"+ta.getStr());


    }
    @Test
    public void  t2() throws  Exception{
        TestAutow ta = new TestAutow();
        Class<? extends  TestAutow> clazz = ta.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.asList(fields).stream().forEach(System.out::println);
        System.out.println(ta.getI()+":"+ta.getStr());
        Field field_i=clazz.getDeclaredField("i");
        String iname = field_i.getName();

        Method setI =  clazz.getMethod("set"+iname.substring(0,1).toUpperCase()+iname.substring(1),int.class);
        setI.invoke(ta,8);
        System.out.println(ta.getI()+":"+ta.getStr());
    }

    /**
     * IOC demo code
     */
    @Test
    public void testLwhAtw(){
        UserService us = new UserService();
         System.out.println(us.getUser());
        Class clazz = us.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.asList(fields).stream().forEach(field -> {
            LwhAutoWired l = field.getAnnotation(LwhAutoWired.class);
            if(l!=null){
                System.out.println(field.getName()+" is @LwhAutoWired ");
                field.setAccessible(true);
                try {
                    Object o = field.getType().getConstructor().newInstance();
                    field.set(us,o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

        });
    System.out.println(us.getUser());
    }




}

class TestAutow{
    @Getter
    @Setter
    private int i;
    @Getter
    @Setter
    private String str;


}
