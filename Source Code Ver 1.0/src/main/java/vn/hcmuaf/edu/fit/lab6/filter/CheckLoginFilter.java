package vn.hcmuaf.edu.fit.lab6.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CheckLoginFilter",urlPatterns = "/orders-history/*")
public class CheckLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String loginURI = httpServletRequest.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("acc") != null;
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURI);

        if(loggedIn || loginRequest){
                chain.doFilter(request, response);
        } else{
            String redirectURL = httpServletRequest.getContextPath() + httpServletRequest.getServletPath();
            session.setAttribute("redirectURL", redirectURL);
            httpServletResponse.sendRedirect(loginURI);
        }
    }
}
