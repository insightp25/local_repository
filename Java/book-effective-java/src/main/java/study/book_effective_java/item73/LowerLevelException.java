package study.book_effective_java.item73;

// 하위 레벨 예외
public class LowerLevelException extends RuntimeException{
    LowerLevelException() {
        super("하위 계층에서 발생한 예외입니다");
    }
}
