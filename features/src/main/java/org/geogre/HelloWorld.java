package org.geogre;

public class HelloWorld {

    static {
        System.out.println("in static");
    }

    public String sayHelloWorld() {
        return "Hello World!";
    }

    protected void aaa() {
        System.out.println("Aaa");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}