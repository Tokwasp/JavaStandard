package chapter14.lambda;

public class LambdaEx2 {
    public static void main(String[] args) {
        MyFunction f = () ->{};
        Object obj = (MyFunction)(() -> {});
        String str = ((Object)(MyFunction)(() -> {})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);
//        System.out.println(() -> {});
        System.out.println((MyFunction)(() ->{}));
//        System.out.println((MyFunction)(() ->{}).toString());
        System.out.println((Object)((MyFunction)(() -> {})).toString());
    }
}
