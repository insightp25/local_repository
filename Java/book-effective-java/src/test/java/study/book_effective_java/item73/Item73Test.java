package study.book_effective_java.item73;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Item73Test {

    Item73HigherLevelComponent higher = new Item73HigherLevelComponent();

    @Test
    void 하위_계층_예외가_발생하면_예외_연쇄로_상위_계층_에외를_던진다() {
        Assertions.assertThatThrownBy(() -> {

            // 하위 계층 예외를 던지는 하위 계층 메서드 호출
            higher.callLowerLevelComponent();

        // 하위 계층에서 예외가 발생했지만 상위 계층의 예외로 번역되었음을 검증
        }).isInstanceOf(HigherLevelException.class);
    }

    @Test
    void 예외_연쇄_처리한_하위_계층의_정보를_상위_계층_예외에서_읽어들일_수_있다() {
        Assertions.assertThatThrownBy(() -> {

            // 예외를 던지는 하위 계층 메서드 호출
            higher.callLowerLevelComponent();

        // 상위 계층의 예외가 하위 계층의 예외임을 검증
        }).hasCauseInstanceOf(LowerLevelException.class);
    }

    @Test
    void 하위_계층_예외가_발생하면_예외_연쇄로_상위_계층_에외를_던지고_log_를_getMessage_으로_찍는다() {
        try {
            higher.callLowerLevelComponent();
        } catch (HigherLevelException e) {
            System.out.println("getMessage() 출력: " + e.getMessage());
        }
    }

    @Test
    void 하위_계층_예외가_발생하면_예외_연쇄로_상위_계층_에외를_던지고_log_를_toString_으로_찍는다() {
        try {
            higher.callLowerLevelComponent();
        } catch (HigherLevelException e) {
            System.out.println("toString() 출력: " + e.toString());
        }
    }

    @Test
    void 하위_계층_예외가_발생하면_예외_연쇄로_상위_계층_에외를_던지고_log_를_getStackTrace_로_찍는다() {
        try {
            higher.callLowerLevelComponent();
        } catch (HigherLevelException e) {
            System.out.println("getStacktrace() 출력: " + e.getStackTrace());
        }
    }

    @Test
    void 하위_계층_예외가_발생하면_예외_연쇄로_상위_계층_에외를_던지고_log_를_printStackTrace_로_찍는다() {
        try {
            higher.callLowerLevelComponent();
        } catch (HigherLevelException e) {
            System.out.println("printStackTrace() 출력: ");
            e.printStackTrace();
        }
    }
}
