
//��ͣʹ�ù�����

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
//������
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//���ñ��루������룩
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse responset = (HttpServletResponse)arg1;
		User user = (User)request.getSession().getAttribute("user");
		String path = request.getServletPath();
		//����������������ͷ���
		if(url.contains(path) || path.contains("/img/")){
			arg2.doFilter(arg0, arg1);
			return;
		}
		if(user==null){
		//������Ŀ��ȫ·��
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