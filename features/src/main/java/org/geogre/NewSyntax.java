package org.geogre;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NewSyntax {

    public static void main(String arg[]) {
        List immutableList = List.of("one", "two", "three");
        Map immutableMap = Map.of(5, "map1", 3, "map2");
        System.out.println(immutableList.get(1));
        System.out.println(immutableMap.get(5));

        Executor exe = CompletableFuture.delayedExecutor(50L, TimeUnit.SECONDS);
        List<String> strings = List.of("first", "second", "*");

        IntStream.iterate(1, i -> i < 10, i -> i + 1)
                .forEach(System.out::println);

        int aa = 5;
        var today = switch (aa) {
            case 1, 2 -> "Weekend day";
            case 3, 4, 5 -> "Working day";
            default -> throw new IllegalArgumentException("Invalid day: " + aa);
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
