package com.example.java8test.optional;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    public static void main(String[] args) {
        // todo ofNullable
        System.out.println("ofNullable: " +
                sum(Optional.ofNullable(null), Optional.ofNullable(null))
        );
        System.out.println("ofNullable: " +
                sum(Optional.ofNullable(2), Optional.ofNullable(null))
        );
        // todo of
        System.out.println("of: " +
                sum(Optional.of(2), Optional.of(3))
        );
        System.out.println("of: " +
                sum(Optional.of(2), Optional.of(null))
        );
    }

    public static int sum(Optional<Integer> a, Optional<Integer> b) {
        Integer p1 = a.orElse(1);
        Integer p2 = a.orElse(2);
        return p1 + p2;
    }
}
