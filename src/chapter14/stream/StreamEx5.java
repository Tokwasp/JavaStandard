package chapter14.stream;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx5 {
    public static void main(String[] args) {
        String[] strArr = {
                "Inheritance", "Java", "Lambda", "stream",
                "OptionalDouble", "IntStream", "count", "sum"
        };
        Stream.of(strArr).forEach(System.out::println);

        boolean noEmptyStr = Stream.of(strArr).noneMatch(s -> s.length() ==0);
        Optional<String> sWord = Stream.of(strArr)
                .filter(s -> s.charAt(0) =='s').findFirst();

        System.out.println("noEmptyStr=" + noEmptyStr);
        System.out.println("sWord=" + sWord.get());

        //Stram <String[]> 을 IntStream으로 변환
        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);
        //DoubleStream도 reduce 반환값이 OptionalDouble 일까?
        DoubleStream doubleStream1 = Stream.of(strArr).mapToDouble(String::length);

        int count = intStream1.reduce(0, (a,b) -> a+1);
        int sum = intStream2.reduce(0, (a,b) -> a+b);

        OptionalInt max = intStream3.reduce(Integer::max);
        OptionalInt min = intStream4.reduce(Integer::min);
        OptionalDouble Doublemax = doubleStream1.reduce(Double::max);

        System.out.println("count=" + count);
        System.out.println("sum=" + sum);
        System.out.println("max=" + max.getAsInt());
        System.out.println("min=" + min.getAsInt());
        System.out.println("Doublemax=" + Doublemax.getAsDouble());
    }
}
