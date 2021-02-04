package com.km.security.jwt;

import com.km.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import sun.security.util.SecurityConstants;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Description: 生成 token的工具类
 *
 * @Author GaoKunW
 * @Date 2021/2/4
 */
public class JwtTokenUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class);

    private static final String CLAIMS_KEY_USERNAME = "claims_key_username";
    private static final String CLAIM_KEY_CREATED_DATE = "claim_key_created_date";

    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static String secretKey;

    /**
     * 静态变量注入
     * 从配置文件读取jjwt.key属性
     * 注入key，set方法不能是static
     *
     * @param secretKey
     */
    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey) {
        JwtTokenUtils.secretKey = secretKey;
    }

    private String generateToken(Map<String, Object> claims) {
        final Date createdDate = new Date();
        String tokenPrefix = Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("type", "JWT")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(createdDate)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .setExpiration(getExpirationDate())
                .compact();
        return tokenHead + tokenPrefix;
    }

    /**
     * 获取secretKey
     *
     * @return
     */
    private static SecretKey getSecretKey() {
        if (StringUtils.isBlank(secretKey)) {
            LOGGER.info("jjwt配置的密钥不能为空");
        }
        return Keys.hmacShaKeyFor(DatatypeConverter.parseBase64Binary(secretKey));
    }

    /**
     * 生成token的过期时间
     *
     * @return
     */
    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 根据token获取JWT负载
     *
     * @param token
     * @return
     */
    private static Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey()).build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("JWT格式校验失败:{}", token);
        }
        return claims;
    }

    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaims(token);
            username =  claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据user信息获取token
     *
     * @param user
     * @return
     */
    public String getToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_CREATED_DATE, new Date());
        return generateToken(claims);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

}
