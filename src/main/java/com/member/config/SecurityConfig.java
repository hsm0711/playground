package com.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.member.filter.JwtFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security filter를 filterchain에 등록
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf().disable().headers().xssProtection().and().contentSecurityPolicy("script-src 'slef").and().frameOptions().sameOrigin().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X
        .and().formLogin().disable() // 화면 없으니 설정
        .httpBasic().disable().authorizeRequests().antMatchers("/szs/me", "/szs/scrap", "szs/refund").authenticated().anyRequest().permitAll() // 위 3개의 url을 제외한 나머지는 다 접근 가능 설정
        .and().addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();

  }

}
