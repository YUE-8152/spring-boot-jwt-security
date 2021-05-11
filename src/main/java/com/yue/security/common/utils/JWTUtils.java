package com.yue.security.common.utils;

import com.alibaba.fastjson.JSON;
import com.yue.security.bean.user.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.common.utils
 * @ClassName: JWTUtils
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 17:40
 * @Version: 1.0
 */
public class JWTUtils {
    private static final String JWT_PAYLOAD_USER_KEY = "user";

    /**
     * 私钥加密token
     *
     * @param user       载荷中的数据
     * @param privateKey 私钥
     * @return JWT
     */
    public static String generateToken(Object user, PrivateKey privateKey) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JSON.toJSONString(user))
                .setId(createJTI())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param user       载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位分钟
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(Object user, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JSON.toJSONString(user))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param user       载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位秒
     * @return JWT
     */
    public static String generateTokenExpireInSeconds(Object user, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JSON.toJSONString(user))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> TokenInfo<T> getTokenInfo(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        TokenInfo<T> tokenInfo = new TokenInfo<>();
        tokenInfo.setId(body.getId());
        tokenInfo.setUserInfo(JSON.parseObject(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        tokenInfo.setExpiration(body.getExpiration());
        return tokenInfo;
    }
}
