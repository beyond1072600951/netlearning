package com.school.netlearning.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.netlearning.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        super.setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String loginName = request.getParameter("ln");
        String passWord = request.getParameter("pd");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginName, passWord, new ArrayList<>()));
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        logger.info("jwtUser:" + jwtUser.toString());
        //一个用户可能存在多个角色
        List<String> roleList = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            roleList.add(authority.getAuthority());
        }
        String token = JwtTokenUtils.createToken(jwtUser.getId(), jwtUser.getUsername(), roleList, false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
//        response.setHeader("Authorization", JwtTokenUtils.TOKEN_PREFIX + token);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", JwtTokenUtils.TOKEN_PREFIX + token);
        String result = mapper.writeValueAsString(ResultUtil.success(hashMap));
        response.getWriter().write(result);
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String result = mapper.writeValueAsString(ResultUtil.fail("用户名或密码错误！"));
        response.getWriter().write(result);
//        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
