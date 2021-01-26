package com.hello;

import org.geogre.HelloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FromOtherProject {

    private static final Logger LOGGER = Logger.getLogger("Hello");

    interface Stuff {

    }

    public static class Foo implements Stuff {

    }
    public static class Bar extends Foo {

    }
    public static class Slash extends Bar {

    }

    public static void main(String arg[]) {
        System.out.println("hello");
        HelloWorld hello = new HelloWorld();
        System.out.println(hello.sayHelloWorld());

        List<Foo> arr2 = new ArrayList<>();
        arr2.add(new Foo());
        doStuff(arr2);
        doStuff2(arr2);
        doStuff3(arr2);

        List<Bar> arr1 = new ArrayList<>();
        arr1.add(new Bar());
        arr1.add(new Bar());
        doStuff(arr1);
        doStuff2(arr1);
        doStuff3(arr1);

        List<Stuff> arr3 = new ArrayList<>();
        arr3.add(new Slash());
        arr3.add(new Slash());
        doStuff(arr3);
        doStuff2(arr3);
        doStuff3(arr3);

    }

    public static void doStuff(List<? extends Stuff> arr) {
        System.out.println(arr.size());
    }

    public static void doStuff2(List<? super Bar> arr) {
        System.out.println(arr.size());
    }

    public static <T> void doStuff3(List<T> arr) {
        System.out.println(arr.size());
    }

}