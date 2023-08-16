package util.concurrent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class CompareTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add(null);
        list.add("3");
        list.add("2");
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("===========================================");

        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }

            @Override
            public boolean equals(Object obj) {
                return this.equals(obj);
            }
        };


//        Comparator<String> stringComparator1 = String::compareToIgnoreCase;
//        list.sort(stringComparator1);
//
//        Comparator<String> compareTo = Comparator.comparingInt(Integer::valueOf);
//        list.sort(compareTo);

    }
}
