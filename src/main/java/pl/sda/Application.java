package pl.sda;

import java.util.*;

public class Application {
//    public static void main(String[] args) {
//        String name = null;
//        int age = 5;
//        if (age > 10) {
//            name = "bla bla";
//        }
//        System.out.println(countWords(name, " "));
//    }
//
//    private static int countWords(String text, String separator) {
//        return Optional.ofNullable(text).orElseGet(() -> "")
//                .split(separator).length;
//    }

    //    public static void main(String[] args) {
//        List<String> texts = Arrays.asList("Ala", "ma", null, "kota", null, "abc");
//        for (String text: texts){
//            Optional <String> optional = Optional.ofNullable(text);
//            //ma wiecej niz 2 litery
//            //else sout "Stop"
//
//            System.out.println(optional.filter(e -> e.length() > 2)
//                    .orElse("Stop"));
//
//        }
//
//    }
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>() {{
            put(2, "dwa");
            put(3, "trzy");
            put(4, "cztery");
            put(5, null);
            put(6, "");
        }};
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Optional<String> value = Optional.ofNullable(entry.getValue());
            //wypisywanie 2: 3 (ile liter)
            //3: 4
            //4: 6
            //5: 0
            //5: 0
            // uzycie metody filter, or else zwraca 0.
        }
    }
}
