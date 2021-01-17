package com.hello;

import org.geogre.HelloWorld;

import java.util.logging.Logger;

public class FromOtherProject {

    private static final Logger LOGGER = Logger.getLogger("Hello");

    public static void main(String arg[]) {
        System.out.println("hello");
        HelloWorld hello = new HelloWorld();
        System.out.println(hello.sayHelloWorld());
    }

}