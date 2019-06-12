package TrialExam.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(1)
public class LargeRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        int size = servletRequest.getContentLength();
        if (size > 1000) {
            throw new ServletException("Request too large");
        } else {
            filterChain.doFilter(servletRequest, servletResponse); // pass on to the next filter}}
        }
    }
}