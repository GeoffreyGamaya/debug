package org.geogre;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

public class NewSyntax {

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


    public static class Square {
        int borderLength;
        public Square(int in) {
            this.borderLength = in;
        }
        public int area() {
            return borderLength * borderLength;
        }
    }

    public static String getName() {
        System.out.println("currentThread " + Thread.currentThread().getName());
        System.out.println("Inside");
        return "static name";
    }

    public static void main(String[] args) {

        int ii = -1;
        ii = ii >> 1;

        System.out.println(ii);

        HelloWorld hello = new HelloWorld();
        HelloWorld hello2 = new HelloWorld();

        hello.aaa();

        One three = new Three();
        three.method();

        Function<Square, Integer> methRef1 = Square::area;
        int ww = methRef1.apply(new NewSyntax.Square(2));
        System.out.println(ww);

        Runnable methRef2 = NewSyntax::getName;
        Thread thread = new Thread(methRef2);
        System.out.println("currentThread " + Thread.currentThread().getName());
        thread.start();

        List immutableList = List.of("one", "two", "three");
        Map immutableMap = Map.of(5, "map1", 3, "map2");
        System.out.println(immutableList.get(1));
        System.out.println(immutableMap.get(5));

        Executor exe = CompletableFuture.delayedExecutor(50L, TimeUnit.SECONDS);
        List<String> strings = List.of("first", "second", "*");

        IntStream.iterate(1, i -> i < 10, i -> i + 1)
                .forEach(System.out::println);

        int valu = 5;
        var today = switch (valu) {
            case 1, 2 -> "Weekend day";
            case 3, 4, 5 -> "Working day";
            default -> throw new IllegalArgumentException("Invalid day: " + valu);
        };
        System.out.println(today);

        String html = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;

        System.out.println(html);
    }

}
