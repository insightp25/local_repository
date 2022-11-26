package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용(CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 회원 관리 처리 API (POST /user/**)에 대해 CSRF 무시
        http.csrf()
                .ignoringAntMatchers("/user/**");

        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // 회원 관리 처리 api 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                // login 기능
                .formLogin() // 로그인 기능작동
                .loginPage("/user/login") // 사용자 정의 페이지 경로
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/") // 로그인 성공 시 이동 페이지 경로
                .failureUrl("/user/login?error") // 로그인 실패 시 이동 페이지 경로
                .permitAll()
                .and()
                // logout 기능
                .logout()
                .logoutUrl("/user/logout")
                .permitAll();
    }
}