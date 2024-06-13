package study.book_effective_java.item73;

// 상위 레벨 클래스
public class Item73HigherLevelComponent {

    // 하위 레벨 인스턴스 생성
    Item73LowerLevelComponent lower = new Item73LowerLevelComponent();

    // 하위 레벨 인스턴스의 메서드 호출
    void callLowerLevelComponent() {
        lower.performExceptionChaining();
    }
}
