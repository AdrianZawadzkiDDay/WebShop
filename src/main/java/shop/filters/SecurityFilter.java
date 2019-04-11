package shop.filters;


import shop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "adminSecurityFilter", servletNames = {"addProductServlet"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       final HttpServletRequest reqest = (HttpServletRequest) servletRequest;
       final HttpServletResponse response = (HttpServletResponse) servletResponse;
       if(reqest.getMethod().equalsIgnoreCase("POST") ||
       reqest.getMethod().equalsIgnoreCase("GET")) {
           final HttpSession session = reqest.getSession(false);
           final User user = (User) reqest.getSession().getAttribute("user");

           if (user != null && user.getRoles().contains("admin")) {
               filterChain.doFilter(servletRequest, servletResponse);
           } else {
               response.sendRedirect("/login");
           }
       } else {
           filterChain.doFilter(reqest, response);
       }

    }

    @Override
    public void destroy() {

    }
}
