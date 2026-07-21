package com.finedge.finedge.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse =(HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        httpServletResponse.setHeader("Pragma","no-cache");
        httpServletResponse.setDateHeader("Expires",0);
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
