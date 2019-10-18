package cn.rui0.core.config.jwt;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.util.JwtUtil;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.po.common.UserRole;
import cn.rui0.common.exception.RequestException;
import cn.rui0.core.service.common.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Ruilin on 2018/4/10.
 */
@Component
public class JwtAuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截器 从头部拿到认证信息并返回判断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    private final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type, Accept");
        // response.setHeader("Content-Type","multipart/form-data");
        // response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");


        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = request.getRequestURI();

        logger.info("Method: " + method.getName() + ", Access: " + method.isAnnotationPresent(Access.class));
        logger.info("requestPath: " + requestPath);



        Access access = method.getAnnotation(Access.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            writerResponse(response,ResponseCode.NOT_SING_IN.code,"无身份认证权限标示");
            return false;
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //throw new RequestException(ResponseCode.SIGN_IN_FAIL);
        }

        //取得token
        String token = authHeader.substring(7);
        try {
            if (jwtUtil.checkToken(token)) {
                //返回用户对象
                User user = userService.findByUserId(jwtUtil.getUserId(token));

                if (access.roles().length > 0) {
                    // 如果权限配置不为空, 则取出配置值
                    String[] roles = access.roles();
                    Set<String> authSet = new HashSet<>();
                    for (String role : roles) {
                        // 将权限加入一个set集合中
                        authSet.add(role);
                    }
                    // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验

                    List<UserRole> roleList=user.getRoleList();
                    for (UserRole role:roleList) {
                        if (StringUtils.isNotBlank(role.getRole())) {
                            if (authSet.contains(role.getRole())) {
                                //setAttribute
                                request.setAttribute("currentUser", user);
                                // 校验通过返回true, 否则拦截请求
                                return true;
                            }
                        }
                    }
                    writerResponse(response,ResponseCode.FAIL.code,"没有权限");
                    return false;
                }
            } else {
                //如果验证token失败，并且方法需要用户，返回401错误
                //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writerResponse(response,ResponseCode.NOT_SING_IN.code,"用户token验证失败");
                return false;
                //throw new RequestException(ResponseCode.SIGN_IN_FAIL);
            }
        } catch (Exception e) {
            writerResponse(response,ResponseCode.NOT_SING_IN.code,"用户token验证失败");
            return false;
            //throw new ServletException(e.getMessage());
            //throw new RequestException(ResponseCode.SIGN_IN_FAIL);
        }


        return false;

    }
    private void writerResponse(HttpServletResponse response,Integer status,String content){
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseResult.builder()
                    .status(status)
                    .msg(content)
                    .build()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}