package vn.hcmuaf.edu.fit.lab6.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CheckURLFilter",urlPatterns = "/*")
public class CheckURLFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String URL = httpServletRequest.getContextPath() + httpServletRequest.getServletPath();
        session.setAttribute("URL", URL);
        chain.doFilter(request, response);
    }
}
