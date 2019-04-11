package shop.filters;

import shop.model.UserDb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "checkIfUserExistsFilter", servletNames = {"registerServlet"})
public class AlreadyExistFilter implements Filter {
    private UserDb userDb;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDb = UserDb.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (username == null || username.isEmpty() ||
                    email == null || email.isEmpty()) {
                request.setAttribute("error", "email or username cannot be empty");
                request.getRequestDispatcher("/register.jsp").forward(servletRequest, servletResponse);
            } else if (userDb.checkIfUserExist(username, email)) {
                request.setAttribute("error", "user or email already exists");
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
}
