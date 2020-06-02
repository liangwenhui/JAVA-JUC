package juc.useUnsafe;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Cas 不需要加锁如何保证原子性？
 * 底层使用 unsafe，对内存进行直接操作。是CPU原语操作，不会有并发问题。
 * unsafe不允许我们使用，我们不能直接创建unsafe类，只能通过反射获取
 */
public class TestUnsafe {
    public  int i = 0;

    @Test
    public void  createUnsafe() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe)field.get(null);
        TestUnsafe integer = (TestUnsafe)unsafe.allocateInstance(TestUnsafe.class);
        System.out.println(integer.i);
        unsafe.getAndAddInt(integer.i,0,3);
        System.out.println(integer.i);
    }


}
