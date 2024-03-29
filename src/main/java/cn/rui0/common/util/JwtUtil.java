package cn.rui0.common.util;

import cn.rui0.core.model.vo.common.TokenModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ruilin on 2018/4/10.
 */

/**
 * Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 */


@Component
public class JwtUtil {
    final String base64EncodedSecreKey = "cnVpbGlu";//私钥
    final long TOKEN_EXPIRES_HOUR = 1;

    private TokenModel tokenModel=new TokenModel();

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redis;



    @Bean
    public RedisTemplate redisTemplateInit() {
        //设置序列化Key的实例化对象
        redis.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redis.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redis;
    }

    /**
     * 创建token
     * 通过jjwt生成token
     * redis根据userId存储token并设置有效期
     *
     * @param userName
     * @param userId
     * @return
     */
    public TokenModel creatToken(String userName, Long userId) {
        String token = Jwts.builder()
                .setSubject(userName)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                // 签名设置
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecreKey)
                .compact();
        tokenModel.setTokenModel(userId, userName, token);
        redis.boundValueOps("user_"+String.valueOf(userId)).set(token, TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return tokenModel;
    }


    /**
     * 检查用户token
     * 如果存在延长使用期限
     *
     * @param userToken
     * @return
     * @throws ServletException
     */
    public boolean checkToken(String userToken) throws ServletException {
        if (userToken == null)
            return false;
        Long userId = getUserId(userToken);
        String token = (String) redis.boundValueOps("user_"+String.valueOf(userId)).get();
        if (!userToken.equals(token))
            return false;
        redis.boundValueOps("user_"+String.valueOf(userId)).expire(TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    /**
     * 用户注销 直接删除token
     *
     * @param userToken
     * @throws ServletException
     */
    public void deleteToken(String userToken) throws ServletException {

        if (userToken != null) {
            Long userId = getUserId(userToken);
            redis.delete("user_"+String.valueOf(userId));
        }

    }


    /**
     * 踢出用户登陆状态
     * @param userId
     * @throws ServletException
     */
    public void userLogout(Integer userId) throws ServletException {

        if (userId != null) {
            redis.delete("user_"+String.valueOf(userId));
        }
    }

    /**
     * 解密token获取userId
     *
     * @param userToken
     * @return
     * @throws ServletException
     */

    public Long getUserId(String userToken) throws ServletException {
        try {
            if (userToken != null) {
                Long userId;
                userId = Long.parseLong(Jwts.parser().setSigningKey(base64EncodedSecreKey).parseClaimsJws(userToken).getBody().get("userId").toString());
                return userId;
            }
        } catch (Exception e) {
            throw new ServletException("token exception");
        }
        return null;
    }

    /**
     * 获取当前登陆的所有用户id
     *
     * @return
     */
    public List<Long> getAllCurrentUser(){
        Set userSet=redis.keys("user_*");
        List<Long> userIdList=new ArrayList<Long>();
        for (Object user:userSet.toArray()) {
            userIdList.add(Long.valueOf(user.toString().substring(5)));
        }
        return userIdList;
    }

}
