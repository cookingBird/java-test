package com.example.java8test.reflect;

import com.example.java8test.user.User;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflect implements Serializable {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
//        reflectGetNameTest();
//        reflectGetClassTest();
//        reflectGetParentClassAndInterface();
//        reflectCreateInstanceTest();
//        reflectGetFiledTest();
        reflectGetAllMethods();
    }


    public static void reflectGetNameTest() {
        TestReflect t = new TestReflect();
        System.out.println(t.getClass().getName());
    }

    public static void reflectGetClassTest() throws ClassNotFoundException {
        Class<?> clazz1 = Class.forName("com.example.java8test.reflect.TestReflect");
        Class<?> clazz2 = new TestReflect().getClass();
        Class<?> clazz3 = TestReflect.class;

        System.out.println("类clazz1名称： " + clazz1.getName());
        System.out.println("类clazz2名称： " + clazz2.getName());
        System.out.println("类clazz3名称： " + clazz3.getName());
    }

    public static void reflectGetParentClassAndInterface() throws ClassNotFoundException {
        Class<?> class1 = Class.forName("com.example.java8test.reflect.TestReflect");
        Class<?> parent = class1.getSuperclass();
        System.out.println("父类是：" +
                parent.getName()
        );
        //todo 获取所有接口
        Class<?>[] interfaces = class1.getInterfaces();
        for (Class<?> interface1Interface : interfaces) {
            System.out.println("实现的接口：" +
                    interface1Interface.getName()
            );
        }
    }

    public static void reflectCreateInstanceTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> class1 = Class.forName("com.example.java8test.user.User");
        User usr = (User) class1.newInstance();
        usr.setName("zhang san");
        usr.setAge(18);
        usr.setAddress("addr");
        System.out.println(usr);

        // todo 获取所有的构造函数
        Constructor<?>[] constructors = class1.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor: " + constructor.getName());
            // todo 获取所有的参数
            Class<?>[] ps = constructor.getParameterTypes();
            Arrays.stream(ps).map(t -> t.getName()).forEach(System.out::println);
        }
        ;
        usr = (User) constructors[0].newInstance("li si", 18, "addr2");
        System.out.println(usr);
        usr = (User) constructors[1].newInstance("wang er ma zi");
        System.out.println(usr);
        usr = (User) constructors[2].newInstance();
        System.out.println(usr);
    }

    public static void reflectGetFiledTest() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.java8test.user.User");
        System.out.println("====================实现的接口或者父类的所有属性======================");
        Field[] fs = clazz.getDeclaredFields();

        Arrays.stream(fs).forEach(f -> {
            System.out.println("\nmodify:" + f.getModifiers() + "\nname:" + f.getName() + "\ntype: " + f.getType().getTypeName() + f.getType().getName());
        });

        System.out.println("==========实现的接口或者父类的公开属性==========");
        Field[] pfs = clazz.getFields();
        Arrays.stream(pfs).forEach(f -> {
            System.out.println("\nmodify:" + f.getModifiers() + "\nname:" + f.getName() + "\ntype: " + f.getType().getTypeName() + f.getType().getName());
        });
    }

    public static void reflectGetAllMethods() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.java8test.user.User");
        Method[] ms = clazz.getDeclaredMethods();
        Arrays.stream(ms).forEach(m -> {
            System.out.println("====================method====================");
            System.out.println("\nmodify:" + m.getModifiers() + "\nname:" + m.getName() + "\ntype: " );
            System.out.println("--------------------paramTypes--------------------");
            Class<?>[] ps = m.getParameterTypes();
            Arrays.stream(ps).map(p->p.getSimpleName()).forEach(System.out::println);
            System.out.println("--------------------returnType--------------------");
            Class<?> r = m.getReturnType();
            System.out.println(r.getSimpleName() + "\n");
        });
    }

}
