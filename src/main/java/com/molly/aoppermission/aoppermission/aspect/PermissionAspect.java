package com.molly.aoppermission.aoppermission.aspect;

import com.molly.aoppermission.aoppermission.Model.User;
import com.molly.aoppermission.aoppermission.annotation.RebateinPermission;
import com.molly.aoppermission.aoppermission.exception.RebateinException;
import com.molly.aoppermission.aoppermission.mapper.QueryPermissionMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class PermissionAspect {
    /**
     * 切入点
     * 切入点为包路径下的：execution(public * org.ylc.note.aop.controller..*(..))：
     * org.ylc.note.aop.Controller包下任意类任意返回值的 public 的方法
     * <p>
     * 切入点为注解的： @annotation(VisitPermission)
     * 存在 VisitPermission 注解的方法
     */
    @Pointcut("@annotation(com.molly.aoppermission.aoppermission.annotation.RebateinPermission)")
    private void permission() {

    }

    /**
     * 目标方法调用之前执行
     */
    @Before("permission()")
    public void doBefore() {
        System.out.println("================== step 2: before ==================");
    }

    /**
     * 目标方法调用之后执行
     */
    @After("permission()")
    public void doAfter() {
        System.out.println("================== step 4: after ==================");
    }

    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around("permission()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("================== step 1: around ==================");
        long startTime = System.currentTimeMillis();
        /*
         * 获取当前http请求中的token
         * 解析token :
         * 1、token是否存在
         * 2、token格式是否正确
         * 3、token是否已过期（解析信息或者redis中是否存在）
         * */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        String token = request.getHeader("username");
//        if (StringUtils.isEmpty(token)) {
//            throw new RuntimeException("非法请求，无效token");
//        }
        // 校验token的业务逻辑
        // ...

        /*
         * 获取注解的值，并进行权限验证:
         * redis 中是否存在对应的权限
         * redis 中没有则从数据库中获取权限
         * 数据空中也没有，抛异常，非法请求，没有权限
         * */
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        RebateinPermission rebateinPermission = method.getAnnotation(RebateinPermission.class);
        String username = request.getHeader("username");
        User user = new User(username);
        List<String> keyValue = QueryPermissionMapper.permissions(user);
        String value = rebateinPermission.permissionValue();
        // 校验权限的业务逻辑
        // List<Object> permissions = redis.get(permission)
        // db.getPermission
        // permissions.contains(value)
        // ...
        System.out.println(value);

        // 根据用户名查询他所具有的权限，是否包含该value，如果包含则认为有权限，否则则抛异常
        if (!keyValue.contains(value)){
            return new RebateinException();
        }
        // 执行具体方法
        Object result = proceedingJoinPoint.proceed();

        return result;
    }
}
