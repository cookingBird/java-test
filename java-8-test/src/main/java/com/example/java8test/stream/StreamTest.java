package com.example.java8test.stream;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        System.out.println("使用JAVA7:");
        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表: " + strings);
        int count = getCountEmptyStringUsingJava7(strings);
        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava7(strings);
        System.out.println("字符长度为3的数量: " + count);
        //删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);
        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        System.out.println("合并字符串: " + mergedString);


        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        System.out.println("列表: " + numbers);
        System.out.println("列表中最大的数 : " + getMax(numbers));
        System.out.println("列表中最小的数 : " + getMin(numbers));
        System.out.println("所有数之和 : " + getSum(numbers));
        System.out.println("平均数 : " + getAverage(numbers));


        System.out.println("随机数: ");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("\n使用 Java 8:----------------------------------------------------------- ");
        System.out.println("列表: " + strings);

        System.out.println("空字符串数量为: " +
                strings.stream().filter(str -> str.isEmpty()).count()
        );
        System.out.println("字符串长度为3的数量为: " +
                strings.stream().filter(str -> str.length() == 3).count()
        );
        System.out.println("筛选后的列表: " +
                strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList())
        );
        System.out.println("合并后的字符串: " +
                strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.joining(", "))
        );
        System.out.println("平方后的列表: " +
                numbers.stream().map(num -> Math.pow(num, 2)).collect(Collectors.toList())
        );

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

        System.out.println("随机数: ");
        random.ints().limit(10).sorted().forEach(System.out::println);
        //todo 并行处理
        System.out.println("空字符串的数量： " +
                strings.parallelStream().filter(str -> str.isEmpty()).count()
        );
    }

    public static int getCountEmptyStringUsingJava7(@NotNull List<String> list) {
        int count = 0;
        for (String str : list) {
            if (str.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public static int getCountLength3UsingJava7(@NotNull List<String> list) {
        int count = 0;
        for (String str : list) {
            if (str.length() == 3) {
                count++;
            }
        }
        return count;
    }

    public static List<String> deleteEmptyStringsUsingJava7(@NotNull List<String> list) {
        List<String> filterList = new ArrayList<String>();
        for (String str : list) {
            if (!str.isEmpty()) {
                filterList.add(str);
            }
        }
        return filterList;
    }

    public static String getMergedStringUsingJava7(@NotNull List<String> list, @NotNull String separator) {
        StringBuilder strb = new StringBuilder();
        for (String str : list) {
            if (!str.isEmpty()) {
                strb.append(str).append(separator);
            }
        }
        return strb.toString();
    }

    public static List<Integer> getSquares(@NotNull List<Integer> list) {
        List<Integer> squaresList = new ArrayList<Integer>();
        for (Integer num : list) {
            squaresList.add(num * num);
        }
        return squaresList;
    }

    public static int getMin(@NotNull List<Integer> list) {
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (min > val) {
                min = val;
            }
        }
        return min;
    }

    public static int getMax(@NotNull List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (max < val) {
                max = val;
            }
        }
        return max;
    }

    public static int getSum(@NotNull List<Integer> list) {
        int sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    public static int getAverage(@NotNull List<Integer> list) {
        return getSum(list) / list.size();
    }
}
