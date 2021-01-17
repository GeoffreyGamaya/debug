package org.geogre;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalProgramming {

    interface One {
        default void method() {
            System.out.println("One");
        }
    }

    interface Two {
        default void method() {
            System.out.println("Two");
        }
    }

    static class Three implements One, Two {
        @Override
        public void method() {
            One.super.method();
        }
    }

    public static Integer compine2and3(BiFunction<Integer, Integer, Integer> func) {
        return func.apply(2, 3);
    }

    public static Function<Integer, Integer> createMultiplier(Integer x) {
        return (Integer y) -> y * x;
    }

    static class Employee {
        public final String name;
        public final Integer age;
        public final String jobTitle;
        public final Float salary;
        public Employee(String name, Integer age, String jobTitle, Float salary) {
            this.name = name;
            this.age = age;
            this.jobTitle = jobTitle;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        functionalCourse();
        streamCourse();
    }

    private static void streamCourse() {
        Integer[] integers = {1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(integers);

        Predicate<Integer> isEven = x -> x % 2 == 0;
        Function<Integer, Integer> function = (x) -> 2*x;
        List<Integer> newList = list.stream()
                .filter(isEven)
                .map(function)
                .collect(Collectors.toList());
        System.out.println(newList);

        BinaryOperator<Integer> getSum = Integer::sum;
        Integer reduced = list.stream().reduce(0, getSum);
        System.out.println(reduced);

        FunctionalProgramming.Employee[] employeesArr = {
                new FunctionalProgramming.Employee("John", 34, "developer", 80000f),
                new FunctionalProgramming.Employee("Hannah", 24, "developer", 95000f),
                new FunctionalProgramming.Employee("Bart", 50, "sales executive", 100000f),
                new FunctionalProgramming.Employee("Sophie", 49, "construction worker", 40000f),
                new FunctionalProgramming.Employee("Darren", 38, "writer", 50000f),
                new FunctionalProgramming.Employee("Nancy", 29, "developer", 75000f),
        };

        List<FunctionalProgramming.Employee> employees = Arrays.asList(employeesArr);

        Map<String, Float> averagePerEmploye = employees
                .parallelStream()
                .collect(Collectors.groupingBy(em -> em.jobTitle))
                .entrySet()
                .parallelStream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        m -> m.getValue()
                                .parallelStream()
                                .map(em -> em.salary)
                                .reduce(0f, Float::sum) / m.getValue().size()
                ));

        System.out.println(averagePerEmploye);

        Function<Integer, Integer> fun1 = (x) -> 2*x;
        Function<Integer, Integer> fun2 = (x) -> x + 1;
        Function<Integer, Integer> fun3 = fun1.andThen(fun2);
        Function<Integer, Integer> fun4 = fun2.andThen(fun1);
        Function<Integer, Integer> fun5 = fun1.andThen(fun1);

        System.out.println(fun3.apply(7));
        System.out.println(fun4.apply(7));
        System.out.println(fun5.apply(7));
    }

    private static void functionalCourse() {
        One three = new Three();
        three.method();

        Function<Integer, Integer> doubling = x -> x * 2;
        var result2 = doubling.apply(3);
        System.out.println(result2);

        BiFunction<Integer, Integer, String> printsum = (x, y) -> "It is " + x + " and " + y;
        var resultstr = printsum.apply(3, 5);
        System.out.println(resultstr);

        TriFunction<Integer, Integer, Integer, Integer> adding = (x, y, z) -> x + y + z;
        var result3 = adding.apply(3, 5, 4);
        System.out.println(result3);

        NoArgFunction<String> thereIsAStr = () -> "thereIsAStr";
        String result4 = thereIsAStr.apply();
        System.out.println(result4);

        boolean s = true;
        String a = s ? "yes" : "no";
        System.out.println(a);
        System.out.println(FunctionalProgramming.compine2and3((x, y) -> 2 * x + 3 * y));

        Function<Integer, Integer> multiplyTwo = x -> x * 2;
        Function<Integer, Integer> multiplyThree = x -> x * 3;
        Function<Integer, Integer> multiplyFour = FunctionalProgramming.createMultiplier(4);
        System.out.println(multiplyTwo.apply(6));
        System.out.println(multiplyThree.apply(6));
        System.out.println(multiplyFour.apply(6));

        NoArgFunction<NoArgFunction<String>> createGreater = () -> () -> "function chain";
        System.out.println(createGreater.apply().apply());

        BiFunction<Float, Float, Float> divide = (x, y) -> x / y;
        Function<BiFunction<Float, Float, Float>, BiFunction<Float, Float, Float>> checkArgument =
                (func) -> (x, y) -> {
                    if (y == 0f) {
                        System.out.println("Error second arg is zero");
                        return 0f;
                    }
                    return func.apply(x, y);
                };

        BiFunction<Float, Float, Float> safeDivide = checkArgument.apply(divide);
        System.out.println(safeDivide.apply(6f, 1f));
    }

}