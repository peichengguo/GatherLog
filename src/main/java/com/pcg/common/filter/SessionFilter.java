package com.pcg.common.filter;


import com.pcg.common.vo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPass(HttpServletRequest request){
		boolean flag = false;
		String[] url = new String[]{"download","admin"};
		for(String u : url){
			if(request.getRequestURL().indexOf(u) != -1){
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
		
        if(this.isPass(request)){
        	User user = (User)session.getAttribute("user");
			
        	if(user != null && user.getId() != null){
        		filter.doFilter(request, response);
        	}else{
        		request.getRequestDispatcher("/to_login.jspx").forward(request, response);
        	}
		}else{
            filter.doFilter(request, response);
        }

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
