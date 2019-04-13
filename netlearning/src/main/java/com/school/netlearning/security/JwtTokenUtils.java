package com.school.netlearning.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "score";

    private static final String ISS = "echisan";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;
//    private static final long EXPIRATION = 10L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 添加角色的key
    private static final String ROLE_CLAIMS = "rol";

    //设置当前用户id
    private static final String CURRENT_USER_ID = "current_user_id";

    // 创建token
    public static String createToken(Long id, String username, List<String> roleList, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roleList);
        map.put(CURRENT_USER_ID, id);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                //将用户角色放入token
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public static List<String> getUserRole(String token) {
        return (List<String>) getTokenBody(token).get(ROLE_CLAIMS);
    }

    public static Long getUserId(String token) {
        return Long.parseLong(getTokenBody(token).get(CURRENT_USER_ID).toString());
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
