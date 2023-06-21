package com.example.annotation;

import sun.misc.Regexp;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 *
 * @author liaoxuefeng
 */
@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) throws Exception {
        Person p1 = new Person("Bob", "Beijing", 20);
        Person p2 = new Person("", "Shanghai", 20);
        Person p3 = new Person("Alice", "Shanghai", 199);
        for (Person p : new Person[]{p1, p2, p3}) {
            try {
                check(p);
                System.out.println("Person " + p + " checked ok.");
            } catch (IllegalArgumentException e) {

                System.out.println("Person " + p + " checked failed: " + e);
            }
        }
    }

    static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        for (Field field : person.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range == null) {
                break;
            }
            Object value = field.get(person);
            if (!(value instanceof Integer)) {
                RuntimeException e = new RuntimeException("Range annotation usage for " + field + " error\n");
                System.out.println(e.toString());
                String[] res =  getFiledMsg(field.toString());
                throw e;
            }
            // TODO: validate
            if ((Integer) value < range.min() || (Integer) value > range.max()) {
                throw new IllegalArgumentException("range error");
            }
        }
    }

    public static String[] getFiledMsg(String filed) {
        Pattern p1 = Pattern.compile("java.lang.(\\S+)");
        return p1.split(filed);
    }
}
