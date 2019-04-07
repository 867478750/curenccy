package org.nlb.currency;

import lombok.extern.slf4j.Slf4j;
import org.nlb.currency.fenbi.test1;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//初始化
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//处理
        HttpServletRequest HttpServletRequest = (HttpServletRequest) servletRequest;
        log.info("{}{}",Thread.currentThread().getId(),HttpServletRequest.getRequestURI());
        test1.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
//结束时
    }
}
