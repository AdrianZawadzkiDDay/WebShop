package shop;

import shop.model.User;
import shop.model.UserDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDb db;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String userNameParameter = httpServletRequest.getParameter("username");
        String passwordParameter = httpServletRequest.getParameter("password");

        Optional<User> optionalUser = db.checkLogin(userNameParameter, passwordParameter);

        if (optionalUser.isPresent()){
            final User user = optionalUser.get();
            httpServletRequest.getSession().setAttribute("user", user);
            httpServletResponse.sendRedirect("/shop");
        } else {
            httpServletRequest.setAttribute("error", true);
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
        }

    }

    @Override
    public void init() throws ServletException {
        db = UserDb.getInstance();
    }
}
