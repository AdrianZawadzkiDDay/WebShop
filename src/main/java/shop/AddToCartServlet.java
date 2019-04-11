package shop;

import shop.model.Product;
import shop.model.ProductDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/buyProduct")
public class AddToCartServlet extends HttpServlet {
    private ProductDb db;

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Long id = Long.valueOf(httpServletRequest.getParameter("id"));
        Integer quantity = Integer.valueOf(httpServletRequest.getParameter("quantity"));
        Optional<Product> optionalProduct = db.getProductById(id);

        if(optionalProduct.isPresent() && quantity > 0){
            final Product product = optionalProduct.get();
            if (cartExist(httpServletRequest)) {
                final Map<Product, Integer> cart = (Map<Product, Integer>) httpServletRequest.getSession().getAttribute("cart");

                if (cart.containsKey(product)) {
                    Integer oldQuantity = cart.get(product);
                    cart.remove(product);
                    cart.put(product, quantity + oldQuantity);
                } else {
                    cart.put(product, quantity);
                }
            } else {
                final Map<Product, Integer> cart = new HashMap<>();
                cart.put(product, quantity);
                httpServletRequest.getSession().setAttribute("cart", cart);
            }

        } else {
            httpServletRequest.setAttribute("error", "PRODUCT_NOT_FOUND");
        }
        httpServletResponse.sendRedirect("/shop");

    }

    private boolean cartExist(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getAttribute("cart") != null;
    }

    @Override
    public void init() throws ServletException {
        db = ProductDb.getInstance();
    }
}
