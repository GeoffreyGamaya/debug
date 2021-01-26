package org.geogre;

class De {
    {System.out.println("A");}
    public De() {System.out.println("B");}
}


class Ca extends De {
    static {System.out.println("C");}
    public Ca() {System.out.println("D");}
    {System.out.println("E");}
    static {System.out.println("F");}
}

public class Fever extends De {

    public static void main(String[] args) {
        System.out.println("G");
        new Fever();
        System.out.println("H");
    }

}
