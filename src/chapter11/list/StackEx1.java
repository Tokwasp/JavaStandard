package chapter11.list;

import java.util.Stack;

//코드 안보고 구현해보기
public class StackEx1 {
    public static Stack back = new Stack();
    public static Stack forward = new Stack();

    public static void main(String[] args) {
        goURL("1.네이트");
        goURL("2.야후");
        goURL("3.네이버");
        goURL("4.다음");

        printStatus();

        goBack();
        System.out.println("=뒤로 버튼을 누른후=");
        printStatus();

        goBack();
        System.out.println("=뒤로 버튼을 누른후=");
        printStatus();

        goForward();
        System.out.println("=앞으로 버튼을 누른후=");
        printStatus();

        goURL("codechono.com");
        System.out.println("=새로운 주소로 이동 후=");
        printStatus();
    }
    public static void printStatus(){
        System.out.println("back:" + back);
        System.out.println("forward:" + forward);
        System.out.println("현재 화면은 '" + back.peek() + "' 입니다.");
        System.out.println();
    }

    public static void goURL(String url){
        if(!forward.empty()) forward.clear();
        back.push(url);
    }

    public static void goForward(){
        if(!forward.empty()) back.push(forward.pop());
    }
    public static void goBack(){
        if(!back.empty()) forward.push(back.pop());
    }
}
