package security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // 로그인 기능작동
                .loginPage("/user/login") // 사용자 정의 페이지 경로
                .defaultSuccessUrl("/") // 로그인 성공 시 이동 페이지 경로
                .failureUrl("/user/login?error") // 로그인 실패 시 이동 페이지 경로
                .permitAll();
    }
}