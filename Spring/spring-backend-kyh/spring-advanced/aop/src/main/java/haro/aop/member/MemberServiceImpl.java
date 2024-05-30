package haro.aop.member;

import haro.aop.member.annotation.ClassAop;
import haro.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test value")
    public String haro(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
