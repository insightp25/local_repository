package study.book_effective_java.item73;

// 상위 레벨 예외
public class HigherLevelException extends RuntimeException {

    // 저수준 레벨의 cause를 고수준 예외에 전달
    HigherLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
