package ch06;

class Data4RefParam {
    int x;
}

public class ReferenceParamEx {
    public static void main(String[] args) {
        Data4RefParam d = new Data4RefParam();
        d.x = 10;
        System.out.println("main() : x = " + d.x);

        change(d);
        System.out.println("after change(d.x)");
        System.out.println("main() : x = " + d.x);

    }

    static void change(Data4RefParam d) {
        d.x = 1000;
        System.out.println("change() : x = " + d.x);
    }


}
