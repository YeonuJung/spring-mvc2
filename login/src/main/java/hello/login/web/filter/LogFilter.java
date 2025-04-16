package hello.login.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LogFilter doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}] [{}]", uuid, requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Request [{}]아니 [{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy");
    }
}
