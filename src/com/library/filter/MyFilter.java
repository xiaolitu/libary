
//暂停使用过滤器

/*package com.library.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.User;

public class MyFilter implements Filter{
    String url;
	@Override
	public void destroy() {
		
	}
//过滤器
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//设置编码（解决乱码）
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse responset = (HttpServletResponse)arg1;
		User user = (User)request.getSession().getAttribute("user");
		String path = request.getServletPath();
		//满足括号里的条件就放行
		if(url.contains(path) || path.contains("/img/")){
			arg2.doFilter(arg0, arg1);
			return;
		}
		if(user==null){
		//加上项目的全路径
			responset.sendRedirect(request.getContextPath()+"index.jsp");
			return;
		}
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		url =arg0.getInitParameter("url");
	}

}*/