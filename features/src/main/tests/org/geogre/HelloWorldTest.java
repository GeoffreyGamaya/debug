package org.geogre;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class HelloWorldTest {

    @Test
    String sayHelloWorld() {
        String[] strArr = new String[]{"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"};

        List<Set<String>> aaa = List.of(strArr).stream()
                .map(str -> Arrays.asList(str.split(",")))
                .map(l -> l.stream().map(String::trim).collect(Collectors.toCollection(LinkedHashSet::new)))
                .collect(Collectors.toList());

        String[] bb = aaa.get(0).stream()
                .filter(str -> aaa.get(1).contains(str))
                .collect(Collectors.toList()).toArray(String[]::new);

//        String[] aa = input.stream().map(str -> str.split(","))
//                .flatMap(Stream::of)
//                .map(String::trim)
//                .collect(Collectors.toCollection(LinkedHashSet::new))
//                .toArray(String[]::new);
        System.out.println(String.join(",", bb));
        return String.join(",", bb);
    }

    public interface MorseEncoder {
        String DOT = ".";
        String DASH = "-";

        String encode(String message);
    }

    public static class ContinentalMorseEncoder implements MorseEncoder {

        public String[][] table = {
                {"A", MorseEncoder.DOT, MorseEncoder.DASH},
                {"B", MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"C", MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT},
                {"D", MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT},
                {"E", MorseEncoder.DOT},
                {"F", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT},
                {"G", MorseEncoder.DASH, MorseEncoder.DASH, MorseEncoder.DOT},
                {"H", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"I", MorseEncoder.DOT, MorseEncoder.DOT},
                {"J", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DASH, MorseEncoder.DASH},
                {"K", MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DASH},
                {"L", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT},
                {"M", MorseEncoder.DASH, MorseEncoder.DASH},
                {"N", MorseEncoder.DASH, MorseEncoder.DOT},
                {"O", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"P", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"Q", MorseEncoder.DASH, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DASH},
                {"R", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT},
                {"S", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"T", MorseEncoder.DASH},
                {"U", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DASH},
                {"V", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DASH},
                {"W", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DASH},
                {"X", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"Y", MorseEncoder.DASH, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"Z", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT}
        };

        public String[][] getTable() {
            return this.table;
        }

        @Override
        public String encode(String message) {
            List<String> list = new ArrayList<String>();
            for(char c : message.toCharArray()) {
                list.add(String.valueOf(c));
            }

            List<String> morseParts = list.stream()
                    .map(this::getMorseForLetter)
                    .collect(Collectors.toList());
            return String.join("\n", morseParts);
        }

        private String getMorseForLetter(String letter) {
            String x = String.valueOf(letter.toUpperCase().charAt(0));
            for (String[] strings : this.getTable()) {
                if (x.equals(strings[0])) {
                    String[] morsePart = Arrays.copyOfRange(strings, 1, strings.length);
                    StringBuffer sb = new StringBuffer();
                    for(int i = 0; i < morsePart.length; i++) {
                        sb.append(morsePart[i]);
                    }
                    return sb.toString();
                }
            }
            return "";
        }

        public static void main(String[] args) {
            String toEncode = "hello";
            System.out.println("String to be encoded in continental morse: " + toEncode);

            MorseEncoder encoder = new InternationalMorseEncoder();
            String encoded = encoder.encode(toEncode);
            System.out.println("Encoded message is:");
            System.out.println(encoded);
        }
    }

    public static class InternationalMorseEncoder extends ContinentalMorseEncoder {

        private String[][] table = {
                {"E", MorseEncoder.DOT},
                {"H", MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT, MorseEncoder.DOT},
                {"L", MorseEncoder.DOT, MorseEncoder.DASH, MorseEncoder.DOT, MorseEncoder.DOT},
                {"O", MorseEncoder.DASH, MorseEncoder.DASH, MorseEncoder.DASH},
                };

        public String[][] getTable() {
            return this.table;
        }

    }

    @Test
    void sayHelloWorld2() {

        System.out.println(aggregator(5));
    }

    public static int aggregator(int n) {
        int aggreg = 0;
        do {
            aggreg = aggreg + n;
            n = n - 1;
        }
        while (n > 0);
        return aggreg;
    }
}