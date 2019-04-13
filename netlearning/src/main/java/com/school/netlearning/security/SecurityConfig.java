package com.school.netlearning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    // 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    // 加密密码的，安全第一
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //以下请求可直接通过，无需验证
//                .antMatchers("/static", "/img/*").permitAll()
                //以“/u”开头的需要验证通过才能访问
                .antMatchers("/user/**", "course/**").authenticated()
                //角色是“ADMIN”才能访问以下接口
                .antMatchers("/user/findAll", "/user/addOrUp", "/course/save", "/course/update", "/course/findAll").hasAnyRole("ADMIN")
//                .antMatchers("/u/**", "/upload/**").hasAnyRole("TEACHER")
//                .antMatchers("/u/**").hasAuthority("admin")
                // 其他都放行
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

/*        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/index.html").permitAll()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/loginValid")
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().permitAll();*/
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
