package shop;

import shop.model.Product;
import shop.model.ProductDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    private ProductDb db;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute("products", db.getProducts());

        if (httpServletRequest.getCookies() != null) {
            for (final Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("lastProductView")) {
                    Long idOfLastProductView = Long.valueOf(cookie.getValue());
                    List<Product> products = db.getProducts();
                    products.stream()
                            .filter(p -> p.getId().equals(idOfLastProductView))
                            .findFirst()
                            .ifPresent(product -> {
                                httpServletRequest.setAttribute("lastProductView", product);
                            });
                }
            }
        }
        httpServletRequest.getRequestDispatcher("/shop.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init() throws ServletException {
        db = ProductDb.getInstance();
    }
}
