package com.apple.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder EncoderDi(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        FilterChain : 모든 유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드 담는 곳
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
//                아무나 접속 허용
        );
//        폼으로 로그인하기
        http.formLogin((formLogin) -> formLogin.loginPage("/login")
                .defaultSuccessUrl("/")
        );

//        로그아웃
        http.logout( logout -> logout.logoutUrl("/logout") );

        return http.build();

//        함수 빠르게 만드려면 ()->{}
    }

//    20250529
//    1. 남이 만든 클래스를 수정하고 싶으면 원본을 수정하는 것 보다는 extends로 복사해서 새로 클래스를 만드는 방법도 있습니다.
//
//2. extends로 복사할 때 super를 사용해서 constructor도 복사해서 완전범죄를 꾀하는게 좋습니다.
//
//3. 세션 유효기간 설정도 가능
//
//4. 세션 데이터를 안전하게 DB에 저장도 가능
//
//5. CSRF 기능 켜는 법 배움
//
//6. 라이브러리 세부 문법들은 AI에게 물어보거나 찾아보면 되는 것일 뿐 외울 필요 없습니다.
}
