package com.stu.ssm.controller;

import com.stu.ssm.domain.SysLog;
import com.stu.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * AOP日志处理
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//访问时间
    private Long executionTime; //执行时长
    private Class clazz; //访问的类
    private Method method; //访问方法

    /**
     * AOP切面：主要获取开始时间，执行的类是哪一个，执行哪一个方法
     * @param jp
     * @throws Exception
     */
    @Before("execution(* com.stu.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception{
        //1.执行前获取当前系统访问时间
        visitTime = new Date();
        //2.获取具体要访问的类对象
        clazz = jp.getTarget().getClass();
        //3.获取具体的类执行的方法名称
        String methodName = jp.getSignature().getName();
        //4.获取访问的方法参数
        Object[] args = jp.getArgs();
        //5.判断方法是否存在参数
        if(args==null && args.length==0){
            //5.1无参数，直接获取无参数方法即可
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        }else {
            //5.2创建Class数组对象
            Class[] classArgs = new  Class[args.length];
            for (int i = 0; i < args.length; i++) {
                //5.2.1遍历获取到的所有方法参数，获取每个对应参数方法的Class，放入Class数组中
                    classArgs[i] = args[i].getClass();
            }
            //5.3 获取参数方法
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * AOP切面:主要获取ip、操作者、url执行时长
     * @param jp
     * @throws Exception
     */
    @After("execution(* com.stu.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        //1.获取方法的执行时长
        executionTime = new Date().getTime() - visitTime.getTime();
        //2.获取URL
        String url ="";
            //2.1判断是否获取到了class、method、并且不为当前class
            if(clazz!=null && method!=null && clazz!=LogAop.class){

                //2.2获取类上的注解：@RequestMapping("/user")
                RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                //2.3 判断是否获取到对应的注解类
                if(classAnnotation!=null){
                    //2.3.1 获取该RequestMapping类的value，就是注解的value，class的值
                    String[] classValue = classAnnotation.value();

                    //2.3.2 获取方法上的@RequestMapping("xxx")
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if(methodAnnotation!=null){
                        //2.3.4 获取RequestMapping方法上的value,就是注解value，method的值
                        String[] methodValue = methodAnnotation.value();

                        //2.3.5 拼接获取访问的url的值
                        url=classValue[0]+methodValue[0];

                        /*2.3.6访问ip的获取方案：
                            1、使用spring的Listener监听器，需要在web.xml中引入监听类：org.springframework.web.context.request.RequestContextListener
                         */
                        String ip = request.getRemoteAddr();

                        /*2.3.7 获取用户名的两种方法：
                        * 1、根据spring-security中的User对象获取:User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        * 2、根据request方法获取:request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                        * */
                        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        String userName = user.getUsername();

                        //2.3.8 把输入封装进SysLog对象：
                        SysLog sysLog = new SysLog();
                        sysLog.setExecutionTime(executionTime);
                        sysLog.setIp(ip);
                        sysLog.setMethod("[类名 ]"+clazz.getName()+"[方法名 ]"+method.getName());
                        sysLog.setUrl(url);
                        sysLog.setUsername(userName);
                        sysLog.setVisitTime(visitTime);

                        //2.3.9 调用ServiceImpl完成操作：
                        sysLogService.save(sysLog);
                    }
                }
            }

    }
}
