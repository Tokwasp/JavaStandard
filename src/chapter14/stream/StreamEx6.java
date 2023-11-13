package chapter14.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        };

        
        List<String> names = Stream.of(stuArr).map(Student::getName).collect(Collectors.toList());
        System.out.println(names);

        Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

        for(Student s: stuArr2)
            System.out.println(s);

        Map<String, Student> stuMap = Stream.of(stuArr).collect(toMap(s -> s.getName(), s -> s));
        for(String name : stuMap.keySet())
            System.out.println(name + "-" +stuMap.get(name));

        long count = Stream.of(stuArr).collect(Collectors.counting());
        long totalScore = Stream.of(stuArr).collect(summingInt(Student::getTotalScore));

        System.out.println("count =" + count);
        System.out.println("totalScore = " + totalScore);

        /* 이렇게 계산해도 되지않을까?
        totalScore = Stream.of(stuArr).mapToInt(Student::getTotalScore).sum();
        System.out.println(totalScore); */

        totalScore = Stream.of(stuArr).collect(reducing(0, Student::getTotalScore, Integer::sum));
        System.out.println("totalScore = " + totalScore);

        Optional<Student> topStudent = Stream.of(stuArr).collect(Collectors.maxBy(Comparator.comparingInt(Student::getTotalScore)));
        System.out.println("topStudent = " + topStudent);

        IntSummaryStatistics stat = Stream.of(stuArr).collect(summarizingInt(Student::getTotalScore));
        System.out.println(stat);

        String stuNames = Stream.of(stuArr).map(Student::getName).collect(joining(",","{","}"));
        System.out.println(stuNames);
    }
}
