package com.pcg.common.annotation;

import com.alibaba.fastjson.JSONObject;
import com.pcg.common.utils.CommonUtils;
import com.pcg.common.vo.User;
import com.pcg.common.vo.VisitInfoVo;
import com.pcg.service.VisitInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created by pcg on 16/1/21.
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private VisitInfoService visitInfoService;

    @Pointcut("execution(* com.pcg.controller..*.*(..))")
    public void controllerAspect(){

    }

    @Before("controllerAspect()")
    public void before(){
        logger.info("into before ...............");
    }

    @AfterReturning(pointcut="controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        handlerLog(joinPoint,null);
    }

    @AfterThrowing(pointcut = "controllerAspect()",throwing = "e")
    public void doAfter(JoinPoint joinPoint,Exception e){
        handlerLog(joinPoint,e);
    }

    private void handlerLog(JoinPoint joinPoint,Exception e){
        try{
            ActionControllerLog acConLog = getAnnotion(joinPoint);
            if(acConLog == null){
                return;
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User user = (User)request.getSession().getAttribute("user");
            String ip = CommonUtils.getClientIP(request);
            String browser = request.getHeader("User-Agent");//浏览器

            VisitInfoVo visitInfoVo = new VisitInfoVo();
            visitInfoVo.setIp(ip);
            visitInfoVo.setBrowser(browser);
            visitInfoVo.setTime(new Date());
            visitInfoVo.setUrl(request.getRequestURI());
            if(user != null){
                visitInfoVo.setUserId(user.getId());
            }else{
                visitInfoVo.setUserId(0l);
            }
            if(e != null){
                visitInfoVo.setErrorInfo(e.getMessage());
            }
            /** 注解 title值*/
            visitInfoVo.setModule(acConLog.title());
            //请求参数
            Map map = request.getParameterMap();
            String params = JSONObject.toJSONString(map);
            visitInfoVo.setDesc(params);
            visitInfoService.insertVisitInfo(visitInfoVo);
        }catch (Exception e1){
            logger.error("保存日志信息失败",e1);
        }
    }


    private static ActionControllerLog getAnnotion(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if(method != null){
            logger.info(method.getAnnotation(RequestMapping.class).toString());
            return method.getAnnotation(ActionControllerLog.class);
        }
        return null;
    }

}
