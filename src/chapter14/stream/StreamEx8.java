package chapter14.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class StreamEx8 {
    public static void main(String[] args) {
        CollectStudent[] stuArr = {
                new CollectStudent("나자바", true, 1, 1, 300),
                new CollectStudent("김지미", false, 1, 1, 250),
                new CollectStudent("김자바", true, 1, 1, 200),
                new CollectStudent("이지미", false, 1, 2, 150),
                new CollectStudent("남자바", true, 1, 2, 100),
                new CollectStudent("안지미", false, 1, 2, 50),
                new CollectStudent("황지미", false, 1, 3, 100),
                new CollectStudent("강지미", false, 1, 3, 150),
                new CollectStudent("이자바", true, 1, 3, 200),

                new CollectStudent("나자바", true, 2, 1, 300),
                new CollectStudent("김지미", false, 2, 1, 250),
                new CollectStudent("김자바", true, 2, 1, 200),
                new CollectStudent("이지미", false, 2, 2, 150),
                new CollectStudent("남자바", true, 2, 2, 100),
                new CollectStudent("안지미", false, 2, 2, 50),
                new CollectStudent("황지미", false, 2, 3, 100),
                new CollectStudent("강지미", false, 2, 3, 150),
                new CollectStudent("이자마", true, 2, 3, 200)
        };

        System.out.printf("1. 단순그룹화(반별로 그룹화)%n");
        Map<Integer, List<CollectStudent>> stuByBan = Stream.of(stuArr).collect(groupingBy(CollectStudent::getBan));

        for(List<CollectStudent> ban : stuByBan.values()){
            for(CollectStudent s : ban)
                System.out.println(s);
        }

        System.out.printf("%n2. 단순그룹화(성적별로 그룹화) %n");
        Map<CollectStudent.Level, List<CollectStudent>> stuByLevel = Stream.of(stuArr)
                .collect(groupingBy( s ->
                        {
                            if(s.getScore() >= 200) return CollectStudent.Level.HIGH;
                            else if(s.getScore() >= 100) return CollectStudent.Level.MID;
                            else return CollectStudent.Level.LOW;
                        }
                ));
        TreeSet<CollectStudent.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(CollectStudent.Level key : keySet){
            System.out.println("["+key+"]");

            for(CollectStudent s : stuByLevel.get(key))
                System.out.println(s);
            System.out.println();
        }
        System.out.printf("%n3. 단순그룹화 + 통계 (성적별 학생수) %n");
        Map<CollectStudent.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s -> {
                    if(s.getScore() >= 200) return CollectStudent.Level.HIGH;
                    else if(s.getScore() >= 100) return CollectStudent.Level.MID;
                    else return CollectStudent.Level.LOW;
                }, counting()));
        for(CollectStudent.Level key: stuCntByLevel.keySet()){
            System.out.printf("%s, - %d명 ", key, stuCntByLevel.get(key));
            System.out.println();
        }
        for(List<CollectStudent> level : stuByLevel.values()){
            System.out.println();
            for(CollectStudent s : level){
                System.out.println(s);
            }
        }

        System.out.printf("%n4. 다중그룹화(학년별, 반별)");
        Map<Integer, Map<Integer, List<CollectStudent>>> stuByHakAndBan = Stream.of(stuArr)
                .collect(groupingBy(CollectStudent::getBan,groupingBy(CollectStudent::getHak)));

        for(Map<Integer, List<CollectStudent>> hak : stuByHakAndBan.values()){
            for(List<CollectStudent> ban : hak.values()){
                System.out.println();
                for(CollectStudent s : ban)
                    System.out.println(s);
            }
        }
        System.out.printf("%n5. 다중그룹화 + 통계(학년별, 반별 1등)%n");
        Map<Integer, Map<Integer,CollectStudent>> topStuByHakAndBan =
                Stream.of(stuArr).collect(groupingBy(CollectStudent::getHak,
                                groupingBy(CollectStudent::getBan,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(CollectStudent::getScore))
                                , Optional::get
                        )
                )
                ));

        for(Map<Integer,CollectStudent> ban : topStuByHakAndBan.values()){
            for(CollectStudent s : ban.values())
                System.out.println(s);
        }

        System.out.printf("%n6. 다중그룹화 + 통계(학년별, 반별 성적그룹)%n");
        Map<String,Set<CollectStudent.Level>> stuByScoreGroup = Stream.of(stuArr)
                .collect(groupingBy( s-> s.getHak() + "-" + s.getBan(),mapping(s -> {
                    if(s.getScore() >= 200) return CollectStudent.Level.HIGH;
                    else if(s.getScore() >= 100) return CollectStudent.Level.MID;
                    else return CollectStudent.Level.LOW;
                }, toSet())
                ));
        Set<String> keySet2 = stuByScoreGroup.keySet();

        for(String key : keySet2){
            System.out.println("["+key+"]" +stuByScoreGroup.get(key));
        }
    }
}
