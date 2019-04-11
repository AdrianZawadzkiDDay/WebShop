
package shop.filters;


import shop.model.Product;
import shop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "/orderFilter", servletNames = {"orderServlet"})
public class OrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest reqest = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(reqest.getMethod().equalsIgnoreCase("POST")) {

            final HttpSession session = reqest.getSession(false);
            final User user = (User) reqest.getSession().getAttribute("user");
            final Map<Product, Integer> products = (Map<Product, Integer>) reqest.getSession().getAttribute("cart");

            if (user != null && user.getRoles().contains("user") && products != null && !products.isEmpty()) {
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