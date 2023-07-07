package com.example.java8test.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // todo 输出所有
        eval(list, n -> true);
        // todo 输出偶数
        System.out.println("输出偶数----------------------------");
        eval(list, n -> n % 2 == 0);
        // todo 输出奇数
        System.out.println("输出奇数----------------------------");
        eval(list, n -> n % 2 == 1);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + "\n");
            }
        }
    }
}
