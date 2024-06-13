package study.book_effective_java.item73;

// 하위 레벨 클래스
public class Item73LowerLevelComponent {

    // 예외를 발생시키는 문제 메서드
    void randomMethod() {
        throw new LowerLevelException();
    }

    // exception chaining 처리
    void performExceptionChaining() {
        try {
            // 예외를 발생시키는 문제 메서드 호출
            randomMethod();
        } catch (LowerLevelException e) {
            // 상위 레벨 예외로 exception chaining 처리
            throw new HigherLevelException(e.getMessage(), e);
        }
    }
}
