package shop;

import shop.model.ProductDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addProduct", name = "addProductServlet")
public class addProduct extends HttpServlet {
    private ProductDb db;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/addProduct.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String nameParameter = httpServletRequest.getParameter("name");
        String descriptionParameter = httpServletRequest.getParameter("description");
        Long priceParameter = Long.valueOf(httpServletRequest.getParameter("price"));
        String categoryParameter = httpServletRequest.getParameter("category");
        Integer quantityParameter = Integer.valueOf(httpServletRequest.getParameter("quantity"));

       long id = db.addNewProduct(nameParameter, descriptionParameter, priceParameter, categoryParameter, quantityParameter);
       httpServletResponse.sendRedirect("/product?id=" + id);
    }

    @Override
    public void init() throws ServletException {
        db = ProductDb.getInstance();
    }
}
