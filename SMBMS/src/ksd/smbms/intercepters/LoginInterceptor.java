package ksd.smbms.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	    String userkey= (String)session.getAttribute("currentuser");
		if(userkey!=null){		
			return true;
		}
		else {
			response.sendRedirect("login.htm");
			return false;
		}
		
	}

	
}
