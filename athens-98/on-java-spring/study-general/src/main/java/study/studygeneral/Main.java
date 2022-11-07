package study.studygeneral;

import study.studygeneral.pkg.ModifierTest;

class Child extends ModifierTest {
    void callParentProtectedMember() {
        System.out.println("call my parent's protected method");
        super.messageProtected();
    }
}

public class Main {
    public static void main(String[] args) {
        ModifierTest modifierTest = new ModifierTest();

        modifierTest.messageOutside();
//        modifierTest.messageInside(); // compile error
//        modifierTest.messageProtected(); // compile error

        Child child = new Child();
        child.callParentProtectedMember();
    }


}
