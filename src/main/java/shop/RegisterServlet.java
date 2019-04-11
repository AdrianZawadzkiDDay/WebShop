package shop;


import shop.model.User;
import shop.model.UserDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(value = "/register", name = "registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserDb db;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String userNameParameter = httpServletRequest.getParameter("username");
        String passwordParameter = httpServletRequest.getParameter("password");
        String repearPasswordParameter = httpServletRequest.getParameter("repeatPassword");
        String emailParameter = httpServletRequest.getParameter("email");
        List<String> rolesParameter = Collections.singletonList("user");

       if(userNameParameter == null || userNameParameter.isEmpty() ||
       emailParameter == null || emailParameter.isEmpty() ||
       passwordParameter == null || passwordParameter.isEmpty()) {
           httpServletRequest.setAttribute("error", "incorrect filled form");
           httpServletRequest.getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
       } else if(db.checkIfUserExist(userNameParameter, emailParameter)){
                httpServletRequest.setAttribute("error", "username or email already exist");
                httpServletRequest.getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
        }  else if(!check(emailParameter, passwordParameter, repearPasswordParameter)) {
           httpServletRequest.setAttribute("error", "incorrect mail or password");
           httpServletRequest.getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
       } else {
            final User newUser = db.addUser(userNameParameter, emailParameter, passwordParameter, rolesParameter);
            httpServletRequest.getSession().setAttribute("user", newUser);
            httpServletResponse.sendRedirect("/shop");
        }
    }

    private boolean check(String emailParameter, String passwordParameter, String repeatPasswordParameter) {
        return emailParameter != null &&
                validateEmailRegEx(emailParameter) &&
                passwordParameter.equals(repeatPasswordParameter);
    }

    private boolean validateEmailRegEx(final String emailParameter){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(emailParameter);

        return m.find();
    }

    @Override
    public void init() throws ServletException {
        db = UserDb.getInstance();
    }
}
