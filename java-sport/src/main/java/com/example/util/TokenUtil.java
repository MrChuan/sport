package com.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆拿到Token,然后访问系统资源
 * @author chuan
 */
@Slf4j
@Component
public class TokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * 传入用户信息，生成Token
     * @param details
     * @return
     */
    public String generateToken(UserDetails details){
        Map<String,Object> map = new HashMap<>(2);
        map.put(CLAIM_KEY_USERNAME, details.getUsername());
        map.put(CLAIM_KEY_CREATED,new Date());
        return generateJwt(map);

    }
    /**
     * @param map
     * @return
     */
    public String generateJwt(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,secret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();

    }
    /**
     * 通过Token获取荷载信息
     * @param token
     * @return
     */
    public Claims getTokenBody(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 根据token获取用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token){
        return getTokenBody(token).getSubject();
    }
    /**
     * 判断token是否过期
     */
    public boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    public String refresh(String token){
        Claims claims = this.getTokenBody(token);
        claims.setExpiration(new Date());
        return generateJwt(claims);
    }

}
