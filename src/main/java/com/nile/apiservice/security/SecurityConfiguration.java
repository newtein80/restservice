package com.nile.apiservice.security;

import com.nile.apiservice.member.config.CCustomAccessDeniedHandler;
import com.nile.apiservice.member.config.CCustomAuthenticationEntryPoint;
import com.nile.apiservice.member.config.JwtRequestFilter;
import com.nile.apiservice.member.service.MyUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // ! https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/

    // private final JwtTokenProvider jwtTokenProvider;

    // ! https://velog.io/@ehdrms2034

    // private final MyUserDetailsService myUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    private final CCustomAuthenticationEntryPoint ccustomAuthenticationEntryPoint;

    private final CCustomAccessDeniedHandler ccustomAccessDeniedHandler;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ! https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/
        // http
        //     .httpBasic().disable() // rest api ????????? ???????????? ????????????. ??????????????? ???????????? ???????????? ???????????? ??????????????? ??????.
        //     .csrf().disable() // rest api????????? csrf ????????? ?????????????????? disable??????.
        //     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token?????? ????????????????????? ???????????????????????? ????????????.
        //     // .and()
        //     //     .authorizeRequests() // ?????? ??????????????? ?????? ???????????? ??????
        //     //         .antMatchers("/*/signin", "/*/signin/**", "/*/signup", "/*/signup/**", "/social/**").permitAll() // ?????? ??? ?????? ????????? ????????? ????????????
        //     //         .antMatchers(HttpMethod.GET, "/exception/**","/helloworld/**", "/actuator/health").permitAll() // ????????? GET?????? ???????????? ????????? ????????????
        //     //         .anyRequest().hasRole("USER") // ?????? ????????? ????????? ?????? ????????? ????????? ?????? ??????
        //     .and()
        //         .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
        //     .and()
        //         .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        //     .and()
        //         .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token ????????? id/password ?????? ?????? ?????? ?????????.

        // ! // ! https://velog.io/@ehdrms2034
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .authenticationEntryPoint(ccustomAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(ccustomAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/v1/nileapi/event/**").permitAll()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/verify/**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/test/user").hasRole("USER")
                .antMatchers("/test/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override // ignore swagger security config
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");

    }
}