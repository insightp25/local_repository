package ch11;

import java.util.Stack;

public class StackEx1 {
    public static Stack back    = new Stack();
    public static Stack forward = new Stack();

    public static void main(String[] args) {
        goURL("1. NATE");
        goURL("2. YAHOO");
        goURL("3. NAVER");
        goURL("4. DAUM");

        printStatus();

        goBack();
        System.out.println("== '뒤로' 버튼을 누른 후 ==");
        printStatus();

        goBack();
        System.out.println("== '뒤로' 버튼을 누른 후 ==");
        printStatus();

        goForward();
        System.out.println("== '앞으로' 버튼을 누른 후 ==");
        printStatus();

        goURL("codechobo.com");
        System.out.println("== 새로운 주소로 이동 후 ==");
        printStatus();
    }

    public static void printStatus() {
        System.out.println("back: " + back);
        System.out.println("forward: " + forward);
        System.out.println("current page is '" + back.peek() + "'.");
        System.out.println();
    }

    public static void goURL(String url) {
        back.push(url);
        if(!forward.empty())
            forward.clear();
    }

    public static void goForward() {
        if (!forward.empty()) {
            back.push(forward.pop());
        }
    }

    public static void goBack() {
        if (!back.empty())
            forward.push(back.pop());
    }
}
