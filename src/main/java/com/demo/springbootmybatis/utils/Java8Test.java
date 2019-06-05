//package com.demo.springbootmybatis.utils;
//
//import com.demo.springbootmybatis.entity.Apple;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//
//public class Java8Test {
//
//    public static void main(String[] args) {
////        listAscSort();
////        listDescSort();
////        listMoreSort();
////        Arrays.asList("p","k","q","r","s","t").forEach(e -> System.out.println(e));
//        streamFilter();
//    }
//
//    /**
//     * java8对list里面某些字段进行排序
//     * jdk默认排序，升序
//     * > return 1
//     * = return 0
//     * < return -1
//     */
//    public static void  listAscSort() {
//        List<Apple> apples = Arrays.asList(
//                new Apple("green", 1.5),
//                new Apple("red", 1.3),
//                new Apple("yellow", 1.7));
//        apples.sort(Comparator.comparing(Apple :: getWeight));
//        apples.forEach(System.out :: println);
//    }
//    public static void  listDescSort() {
//        List<Apple> apples = Arrays.asList(
//                new Apple("green", 1.5),
//                new Apple("red", 1.3),
//                new Apple("yellow", 1.7));
//        apples.sort(Comparator.comparing(Apple :: getWeight).reversed());
//        apples.forEach(System.out :: println);
//    }
//    public static void  listMoreSort() {
//        List<Apple> apples = Arrays.asList(
//                new Apple("green", 1.5),
//                new Apple("red", 1.5),
//                new Apple("yellow", 1.7));
//        apples.sort(Comparator.comparing(Apple :: getWeight).reversed().thenComparing(Apple::getColor));
//        apples.forEach(System.out :: println);
//    }
//
//    public static void streamFilter() {
//        List<String> stringCollection = new ArrayList<>();
//        stringCollection.add("ddd2");
//        stringCollection.add("aaa2");
//        stringCollection.add("bbb1");
//        stringCollection.add("aaa1");
//        stringCollection.add("bbb3");
//        stringCollection.add("ccc");
//        stringCollection.add("bbb2");
//        stringCollection.add("ddd1");
//
////        stringCollection.stream().forEach(System.out::println);
//        stringCollection.stream().filter(
//                (s)->!s.startsWith("a")
//        ).forEach(System.out::println);
//        System.out.println("-------");
//        stringCollection.stream().sorted().filter(
//                (s)->s.startsWith("b")
//        ).forEach(System.out::println);
//    }
//}
