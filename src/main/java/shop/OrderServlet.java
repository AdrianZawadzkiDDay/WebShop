package shop;

import shop.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet(value = "/order", name = "/orderServlet")
public class OrderServlet extends HttpServlet {
    private OrderDb db;
    private ProductDb products;

//    private OrderDb orderDb;
//    private ProductDb productDb;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/order.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String address = httpServletRequest.getParameter("address");
        User user = ((User) httpServletRequest.getSession().getAttribute("user"));
        Map<Product, Integer> items = (Map<Product, Integer>) httpServletRequest.getSession().getAttribute("cart");

        boolean allOk = true;

        for (Map.Entry<Product, Integer> product : items.entrySet()) {
            Optional<Product> first = products.getProducts().stream()
                    .filter(prod -> prod.getId().equals(product.getKey().getId()))
                    .findFirst();
            if (first.isPresent()) {
                Product existingProd = first.get();
                if (!(existingProd.getQuantity() - product.getValue() >= 0)) {
                    allOk = false;
                    httpServletResponse.sendRedirect("/shop" );
                    break;
                } else {
                    allOk = false;
                    int newQuantity = first.get().getQuantity() - product.getValue();
                    first.get().setQuantity(newQuantity);
                    httpServletResponse.sendRedirect("/order");
                    break;
                }
            }
        }
        if (allOk) {
            db.addNewOrder(items, address, user.getEmail());
            long orderId = db.addNewOrder(items, address, user.getEmail());

            httpServletResponse.sendRedirect("/order");
        }

    }

//    @Override
//    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
//
//        String address = httpServletRequest.getParameter("adress");
//        User user = ((User) httpServletRequest.getSession().getAttribute("user"));
//
//        Map<Product, Integer> products = (Map<Product, Integer>) httpServletRequest.getSession().getAttribute("cart");
//
//        long orderId = orderDb.addNewOrder(products, address, user.getEmail());
//        httpServletResponse.sendRedirect("/order?id=" + orderId);
//
//    }

    @Override
    public void init() throws ServletException {
        db = OrderDb.getInstance();
        products = ProductDb.getInstance();
    }

//    @Override
//    public void init() throws ServletException {
//        orderDb = OrderDb.getInstance();
//        productDb = productDb.getInstance();
//    }
}
