package se160205.servlet;

import se160205.dao.OrderDAO;
import se160205.dto.Account;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh Van Phuot
 */
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session == null) {
            response.sendRedirect("home");
            return;
        }

        Account loginedAccount = (Account) session.getAttribute("loginedUser");

        System.out.println(loginedAccount);
        if (loginedAccount == null) {
            request.setAttribute("loginWarning", "You have to login to checkout");
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        int total = Integer.parseInt(request.getParameter("total"));
        if (total > 0) {
            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
            System.out.println("Cart: " + cart);
            boolean result = OrderDAO.insertOrder(loginedAccount.getId(), cart);

            if (result) {
                session.setAttribute("cart", null);
                request.setAttribute("announcement", "Save order successfully");
            } else {
                request.setAttribute("error", "These products are out of stock");
            }

            request.getRequestDispatcher("viewCart").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
