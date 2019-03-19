package net.tf.java8.stream;

import net.tf.java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/16 10:06
 * @desc 流的基本操作
 */
public class TestStreamAPI1 {

    List<Employee> employees = Arrays.asList(
            new Employee("xx", 59, 666.33),
            new Employee("xx", 59, 666.33),
            new Employee("xx", 59, 666.33),
            new Employee("ss", 36, 62.33),
            new Employee("zz", 18, 6699.33),
            new Employee("gg", 65, 6689.33),
            new Employee("gg", 38, 6689.33)
    );

    List<Employee> employees1 = Arrays.asList(
            new Employee("hy", 35, 6689.33)
    );

    @Test
    public void test() {
        //筛选

        //中间操作，不调用，不执行(惰性求值，延迟加载)
        Stream<Employee> employeeStream = employees1.stream()
                .filter((x) -> x.getAge() == 35);

        //终止操作，调用才执行中间操作
        employeeStream.forEach(Employee::test);
    }

    /**
     * 创建Stream
     */
    @Test
    public void test1() {
        //可以通过Collection 系列集合提供的stream() (串行流) parallelStream() (并行流)
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //通过Arrays的stream() 方法获取流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        //通过Stream 类的of()方法获取
        Stream<String> stream2 = Stream.of("xx", "bb");

        //创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(2, (x) -> x + 2);
        stream3
                //限制为20个
                .limit(20)
                .forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random);
        stream4
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 内部迭代
     */
    @Test
    public void test2() {
        //筛选

        //中间操作，不调用，不执行(惰性求值，延迟加载)
        Stream<Employee> employeeStream = employees.stream()
                .filter((x) -> x.getAge() >= 35);

        //终止操作，调用才执行中间操作
        employeeStream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test3() {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test4() {
        employees.stream()
                .filter((x) -> x.getAge() >= 35)
                //跳过第一个元素
                .skip(1)
                //短路操作，当找到两个符合条件的employee时立刻停止迭代，提高效率
                .limit(2)
                //根据hashcode() 和equals()去重
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    public void test5() {
        List<String> stringList = Arrays.asList("aaa", "bbb", "ccc");
        stringList.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        //用来提取薪水
        employees.stream()
                .map(Employee::getSalary)
                .forEach(System.out::println);

        //方式一
//        for (String s : stringList
//             ) {
//            Stream<Character> characterStream = filterCharacter(s);
//            characterStream.forEach(System.out::println);
//        }

//        //方式二
//        Stream<Stream<Character>> streamStream = stringList.stream()
//                .map(TestStreamAPI1::filterCharacter);
//        streamStream.forEach((stream) -> stream.forEach(System.out::println));

        //方式三,使用flatMap
        Stream<Character> stream = stringList.stream()
                .flatMap(TestStreamAPI1::filterCharacter);
        stream.forEach(System.out::println);


    }

    /**
     * 输入一个字符串流，返回该字符串流的字符集流
     */
    private static Stream<Character> filterCharacter(String str) {
        List<Character> characterList = new ArrayList<>();
        for (char s : str.toCharArray()
             ){
            characterList.add(s);
        }
        return characterList.stream();
    }




    /**
     * 输入一个字符串流，返回该字符串流的字符集流(再次练习)
     */
   private static Stream<Character> filterCharacterAgain(String str) {
       List<Character> list = new ArrayList<>();
       for (Character c : str.toCharArray()
            ) {
           list.add(c);
       }
       return list.stream();
   }

   @Test
    public void test6() {
       List<String> stringList = Arrays.asList("aaa", "bbb", "ccc");
       Stream<Stream<Character>> streamStream = stringList.stream()
               .map(TestStreamAPI1::filterCharacterAgain);
       streamStream
               .forEach((s) -> s.forEach(System.out::println));
   }


   @Test
    public void test7() {
       List<String> stringList = Arrays.asList("aaa", "ccc", "bbb");
       //自然排序，按照comparable排序
       stringList.stream()
               .sorted()
               .forEach(System.out::println);

       employees.stream()
               .sorted((x, y) -> {
                   Integer x1 = x.getAge();
                   Integer y1 = y.getAge();
                   if (x1.equals(y1)) {
                       return x.getName().compareTo(y.getName());
                   } else {
                      return x1.compareTo(y1);
                   }
               }).forEach(System.out::println);
   }

}

