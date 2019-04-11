package shop.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "checkEmailFilter", servletNames = {"registerServlet"})
public class MailFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        String email = request.getParameter("email");

        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (email == null || email.isEmpty() || !validateEmail(email)) {
                request.setAttribute("error", "incorrect email");
                request.getRequestDispatcher("/register.jsp")
                        .forward(servletRequest, servletResponse);
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

    private boolean validateEmail(String email) {
        return email.matches("[\\w-\\+]+[\\w-\\+\\.]+@[\\w-]+[\\w-.]+\\.[a-z]{2,}");
    }
}
