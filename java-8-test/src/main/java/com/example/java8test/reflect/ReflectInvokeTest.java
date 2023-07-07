package com.example.java8test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectInvokeTest {
    private String property = null;

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.example.java8test.reflect.ReflectInvokeTest");
        // 调用TestReflect类中的reflect1方法
        Method method = clazz.getMethod("reflect1");
        method.invoke(clazz.newInstance());
        // Java 反射机制 - 调用某个类的方法1.
        // 调用TestReflect的reflect2方法
        method = clazz.getMethod("reflect2", int.class, String.class);
        method.invoke(clazz.newInstance(), 20, "张三");
        // Java 反射机制 - 调用某个类的方法2.
        // age -> 20. name -> 张三
        method = clazz.getMethod("reflect3");
        method.invoke(clazz);
        changePropertyTest();
    }

    public static void reflect3() {
        System.out.println("Java 反射机制 - 调用某个类的静态方法3.");
    }

    public void reflect1() {
        System.out.println("Java 反射机制 - 调用某个类的方法1.");
    }

    public void reflect2(int age, String name) {
        System.out.println("Java 反射机制 - 调用某个类的方法2.");
        System.out.println("age -> " + age + ". name -> " + name);
    }

    public static void changePropertyTest() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.example.java8test.reflect.ReflectInvokeTest");
        Field f = clazz.getDeclaredField("property");
        f.setAccessible(true);
        Object obj = clazz.newInstance();
        f.set(obj, "JAVA Reflect");
        System.out.println(obj);
    }

    @Override
    public String toString() {
        return "ReflectInvokeTest{" +
                "proprety='" + property + '\'' +
                '}';
    }
}
