package shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //httpServletRequest.setAttribute("cart", httpServletRequest.getSession().getAttribute("cart"));
        httpServletRequest.getRequestDispatcher("/cart.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
