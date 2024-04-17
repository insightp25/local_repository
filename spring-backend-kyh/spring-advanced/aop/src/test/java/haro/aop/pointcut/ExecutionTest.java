package haro.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import haro.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method haroMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        haroMethod = MemberServiceImpl.class.getMethod("haro", String.class);
    }

    @Test
    void printMethod() {
        // public java.lang.String haro.aop.member.MemberServiceImpl.haro(java.lang.String)
        log.info("haroMethod={}", haroMethod);
    }

    @Test
    void exactMatch() {
        // public java.lang.String haro.aop.member.MemberServiceImpl.haro(java.lang.String)
        pointcut.setExpression("execution(public String haro.aop.member.MemberServiceImpl.haro(String))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* haro(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* ha*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *ar*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* nope(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch1() {
        pointcut.setExpression("execution(* haro.aop.member.MemberServiceImpl.haro(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(* haro.aop.member.*.*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchFalse() {
        pointcut.setExpression("execution(* haro.aop.*.*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* haro.aop.member..*.*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* haro.aop..*.*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* haro.aop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(* haro.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* haro.aop.member.MemberServiceImpl.*(..))");

        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* haro.aop.member.MemberService.*(..))");

        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    // String 타입 인수 허용
    // (String)
    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    // 파라미터가 없어야 함
    // ()
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isFalse();
    }

    // 정확히 하나의 파라미터 허용, 모든 타입 허용
    // (xxx)
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    // 숫자와 무관하게 모든 인수, 모든 타입 허용
    // (), (xxx), (xxx, xxx)
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }

    // String 타입으로 시작, 숫자와 무관하게 모든 인수, 모든 타입 허용
    // (String), (String, xxx), (String, xxx, xxx)
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(haroMethod, MemberServiceImpl.class)).isTrue();
    }
}
