package com.pcg.common.utils;

import com.pcg.common.vo.VisitInfoVo;
import com.pcg.service.VisitInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

/**
 * Created by pcg on 16/1/9.
 */
public class CommonUtils {

    @Autowired
    private VisitInfoService visitInfoService;

    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    public static String getClientIP(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }

        String browser = request.getHeader("User-Agent");
        String url = request.getRequestURL().toString();

//        VisitInfoVo vo = new VisitInfoVo();
//        vo.setIp(fromSource+":"+ip);
//        vo.setBrowser(browser);
//        vo.setUrl(url);
//        visitInfoService.insertVisitInfo(vo);
        log.info("App Client IP: "+ip+", fromSource: "+fromSource + ",other: "+browser + url);
        return ip;
    }
}
