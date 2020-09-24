package com.example.demo.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author lx
 */
public class StreamDemo {


    public static void main(String[] args) {

        // 流获取方式
//        Stream<Person> stream = Person.getData().stream();
//        Stream<List<Person>> data = Stream.of(Person.getData());
//        Stream<Integer> integerStream = Stream.of(1, 2, 3);

        Stream<Person> s1 = Person.getData().stream();

        // 最终操作：1.collect
//        List<Person> personList = s1.collect(Collectors.toList());
//        Set<Person> personSet = s1.collect(Collectors.toSet());
//        Map<String, Integer> personMap1 = s1.collect(Collectors.toMap(e1 -> e1.getName(), e2 -> e2.getScore()));
//        Map<String, Integer> personMap2 = s1.collect(Collectors.toMap((Person::getName), Person::getAge));


        // 最终操作：2.reduce 归约操作
        Stream<Integer> s2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        Optional<Integer> reduce1 = s2.reduce((e1, e2) -> e1 + e2);
        Optional<Integer> reduce2 = s2.reduce(Integer::sum);
        System.out.println(reduce2.get());

        Person temp = new Person();
        Optional<Person> reduce3 = s1.reduce((e1, e2) -> temp.setScore(e1.getScore() + e2.getScore()));
        System.out.println(reduce3.get());


        // 最终操作：3.max&min
//        Optional<Person> max1 = s1.max((e1, e2) -> e1.getScore() - e2.getScore());
//        System.out.println(max1);
//        Optional<Person> max2 = s1.max(Comparator.comparingInt(Person::getScore));
//        System.out.println(max2);

//        Optional<Person> min1 = s1.min((e1, e2) -> e1.getScore() - e2.getScore());
//        System.out.println(min1);
//        Optional<Person> min2 = s1.min(Comparator.comparingInt(Person::getScore));
//        System.out.println(min2);


        // 最终操作：4.Matching 匹配 allMatch/anyMatch/noneMatch
        // 所有都大于60分
//        boolean b1 = s1.allMatch(e1 -> e1.getScore() > 60);
//        System.out.println(b1);

        // 有一个大于60分
//        boolean b2 = s1.anyMatch(e1 -> e1.getScore() > 60);
//        System.out.println(b2);

        // 都不大于60分
//        boolean b3 = s1.noneMatch(e1 -> e1.getScore() > 60);
//        System.out.println(b3);


        // 最终操作：5.count 统计
//        long count = s1.count();
//        System.out.println("元素总数：" + count);


        // 最终操作：6.forEach 循环
//        s1.forEach(System.out::println);

        // 最终操作注意事项：使用过最终操作之后，流会被关闭，如果此时再使用同一个流，则会报错


        // 中间操作：1.filter 过滤，满足条件则留下
        // 大于60分
//        s1.filter(e1 -> e1.getScore() > 60).forEach(System.out::println);


        // 中间操作：2.distinct 去重，首先比较 hashcode，如果一样则比较equals
//        s1.distinct().forEach(System.out::println);

//        s1.sorted((e1, e2) -> e1.getScore() - e2.getScore()).forEach(System.out::println);
        // 反序
//        s1.sorted((e1, e2) -> e2.getScore() - e1.getScore()).forEach(System.out::println);

//        s1.sorted(Comparator.comparingInt(Person::getScore)).forEach(System.out::println);
        // 反序
//        s1.sorted(Comparator.comparingInt(Person::getScore).reversed()).forEach(System.out::println);

        // 中间操作：3.skip&limit 跳过与获取前N个
//        s1.skip(5).forEach(System.out::println);
//        s1.limit(3).forEach(System.out::println);


        // 中间操作：4.map 映射/暂存。用指定元素来替换流中元素，类型随意。
//        s1.map(Person::getName).forEach(System.out::println);
//        s1.map(item -> {
//            if (item.getScore() > 20) {
//                return null;
//            } else {
//                return item.getName();
//            }
//        }).forEach(System.out::println);



        // 并行流。并行执行，在数据量比较大的时候，可以提高程序效率。多线程
        // 会分割成若干个部分，不同的部分由不同的线程操作
//        s1.parallel();
//        Person.getData().parallelStream();

        // 串行耗费时间：29001
//        long start = System.currentTimeMillis();
//        LongStream.rangeClosed(0L, 50000000000L).sum();
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);


        // 并行耗费时间：15748
//        long start = System.currentTimeMillis();
//        LongStream.rangeClosed(0L, 50000000000L).parallel().sum();
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);


        // 最终操作：7.findAny&findFirst
        // 在串行下，两个都一样；在并行下，findAny 就看谁先执行了
//        System.out.println(s1.findAny().get());
//        System.out.println(s1.findFirst().get());
//        System.out.println(s1.parallel().findAny().get());


        // 中间操作：5.flatMap 将每一个值转换为另一个流，然后把所有流汇总成一个流
//        String[] array = {"hello", "world"};
        // 输出两个 5
//        Arrays.stream(array).map(item -> item.split("")).forEach(item -> System.out.println(item.length));
        // 输出每个字符
//        Arrays.stream(array).flatMap(item -> Arrays.stream(item.split(""))).forEach(System.out::println);




        // Collectors 工具类，里面有多种多样的方法
        // maxBy&minBy
//        Optional<Person> collect1 = s1.collect(Collectors.maxBy((e1, e2) -> e1.getScore() - e2.getScore()));
//        System.out.println(collect1.get());
//        Optional<Person> collect2 = s1.collect(Collectors.minBy((e1, e2) -> e1.getScore() - e2.getScore()));
//        System.out.println(collect2.get());

        // joining 字符串拼接
//        String str1 = s1.map(Person::getName).collect(Collectors.joining());
//        System.out.println(str1);
//        String str1 = s1.map(Person::getName).collect(Collectors.joining("-"));
//        System.out.println(str1);
//        String str1 = s1.map(Person::getName).collect(Collectors.joining("-","(",")"));
//        System.out.println(str1);


        // summingInt 将集合中的元素转为 Integer 并且求和
//        Integer collect = s1.collect(Collectors.summingInt(Person::getScore));
//        Integer collect = s1.mapToInt(Person::getScore).sum();
//        System.out.println(collect);

        // averagingInt 平均分
//        Double collect = s1.collect(Collectors.averagingInt(Person::getScore));
//        System.out.println(collect);



        // summarizingInt 对 int 类型元素作了一些描述
//        IntSummaryStatistics collect = s1.collect(Collectors.summarizingInt(Person::getScore));
//        System.out.println(collect);
//        System.out.println(collect.getMax());
//        System.out.println(collect.getCount());
//        System.out.println(collect.getAverage());
//        System.out.println(collect.getMin());
//        System.out.println(collect.getSum());


        // 其他东西自己去研究

    }


}

class Person {
    private String name;
    private Integer age;
    private Integer score;


    public static List<Person> getData() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("小明", 12, 100));
        personList.add(new Person("小红", 15, 90));
        personList.add(new Person("小蓝", 21, 80));
        personList.add(new Person("小刚", 21, 70));
        personList.add(new Person("小美", 71, 10));
        personList.add(new Person("小天", 14, 60));


        // 使用 distinct 中间操作就放开
//        personList.add(new Person("小天", 14, 60));
        return personList;
    }

    public Person() {
    }

    public Person(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Person setScore(Integer score) {
        this.score = score;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(score, person.score);


        // 使用 distinct 中间操作就放开
//        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, score);


        // 使用 distinct 中间操作就放开
//        return new Random().nextInt(10);
    }

}
