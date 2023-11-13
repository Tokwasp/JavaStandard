package chapter14.stream;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class StreamEx7 {
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
        System.out.printf("1. 단순분할(성별로 분할)%n");
        Map<Boolean, List<CollectStudent>> stuBySex = Stream.of(stuArr).collect(partitioningBy(CollectStudent::isMale));

        List<CollectStudent> maleStudent = stuBySex.get(true);
        List<CollectStudent> femaleStudent = stuBySex.get(false);

        for(CollectStudent s: maleStudent) System.out.println(s);
        for(CollectStudent s : femaleStudent) System.out.println(s);

        System.out.printf("2. 단순분할 + 통계(성별 학생수) %n");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr).collect(partitioningBy(CollectStudent::isMale,counting()));
        System.out.println("남자 학생수 : " + stuNumBySex.get(true));
        System.out.println("여자 학생수 : " + stuNumBySex.get(false));

        System.out.printf("%n3. 단순분할 + 통계(성별 1등)%n");
        Map<Boolean,Optional<CollectStudent>> topScoreBySex = Stream.of(stuArr)
                .collect(partitioningBy(CollectStudent::isMale, Collectors.maxBy(Comparator.comparingInt(CollectStudent::getScore))));
        System.out.println("남자 1등 점수 : " + topScoreBySex.get(true).get());
        System.out.println("여자 1등 점수 : " + topScoreBySex.get(false).get());

        Map<Boolean, CollectStudent> topScoreBysex2 = Stream.of(stuArr).collect(partitioningBy(CollectStudent::isMale,
                collectingAndThen(maxBy(Comparator.comparingInt(CollectStudent::getScore)),Optional::get)));

        System.out.println("남자 1등 점수 : " + topScoreBySex.get(true));
        System.out.println("여자 1등 점수 : " + topScoreBySex.get(false));

        System.out.printf("%n4. 다중분할(성별 불합격자, 100점이하)%n");
        Map<Boolean,Map<Boolean,List<CollectStudent>>> failedStuBySex = Stream.of(stuArr).
                collect(partitioningBy(CollectStudent::isMale,Collectors.partitioningBy(s -> s.getScore() <= 100)));

        List<CollectStudent> failedMaleStu = failedStuBySex.get(true).get(true);
        List<CollectStudent> failedFemaileStu = failedStuBySex.get(false).get(true);

        for(CollectStudent s : failedMaleStu) System.out.println(s);
        for(CollectStudent s : failedFemaileStu) System.out.println(s);
    }

}
class CollectStudent {
    String name;
    boolean isMale;
    int hak;
    int ban;
    int score;
    CollectStudent(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    String getName() {return name;}
    boolean isMale() {return isMale;}
    int getHak() {return hak;}
    int getBan() {return ban;}
    int getScore() { return score;}

    public String toString(){
        return String.format("[%s, %s, %d학년 %d반, %3d점]", name, isMale ? "남":"여", hak, ban, score);
    }

    enum Level{HIGH, MID, LOW}
}