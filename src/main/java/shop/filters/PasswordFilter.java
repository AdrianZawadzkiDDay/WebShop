package shop.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "checkPasswordFilter", servletNames = {"registerServlet"})
public class PasswordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("repeatPassword");

        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (password == null || password.isEmpty() || !confirmPassword(password, confirmpassword)) {
                request.setAttribute("error", "check your password and repeat-password");
                request.getRequestDispatcher("/register.jsp").forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean confirmPassword(String password, String confirmpassword) {
        return confirmpassword.equals(password);
    }

}
