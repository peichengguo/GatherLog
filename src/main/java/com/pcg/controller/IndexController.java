package com.pcg.controller;

import com.pcg.common.annotation.ActionControllerLog;
import com.pcg.common.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pcg on 16/1/9.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("index")
    @ActionControllerLog(title = "访问主页")
    public String toIndex(HttpServletRequest request,Model model){
        String ip = CommonUtils.getClientIP(request);
        model.addAttribute("ip",ip);
        return "index";
    }

}
