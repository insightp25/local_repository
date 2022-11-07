package study.studygeneral.pkg;

public class ModifierTest {
    private void messageInside() {
        System.out.println("this is private modifier.");
    }

    public void messageOutside() {
        System.out.println("this is public modifier.");
    }

    protected void messageProtected() {
        System.out.println("This is protected modifer.");
    }
}
