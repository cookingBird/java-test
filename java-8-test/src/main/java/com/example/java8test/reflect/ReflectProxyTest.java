package com.example.java8test.reflect;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ReflectProxyTest {
    public static void main(String[] args) {
        System.out.println("类加载器： " +
                new ReflectProxyTest().getClass().getClassLoader().getClass().getName()
        );

        MyInvocationHandler inv = new MyInvocationHandler();
        SubjectA subject = (SubjectA) inv.bind(new SubjectA());
        System.out.println("SubjectA say " + subject.say("Hello World", 18));
    }
}


//定义项目接口
abstract class Subject {
    static {
        System.out.println("interface Subject register");
    }

    public String say(String name, int age) {
        return null;
    }
}

class SubjectA extends Subject {
    @Override
    public String say(String name, int age) {
        return "name: " + name + ";" + "age: " + age + ";";
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;

    public Object bind(Object ctx) {
        obj = ctx;
        if(obj.getClass().getInterfaces().length == 0){
            throw new RuntimeException("class MyInvocationHandler bind target without Interface");
        }
        Arrays.stream(obj.getClass().getInterfaces()).map(i->i.getSimpleName());
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MyInvocationHandler invoke method:" + method.getName());
        return method.invoke(this.obj, args);
    }
}