package se160205.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huynh Van Phuot
 */
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        String redirectUrl = "";
        String action = request.getParameter("action");

        if ("home".equals(action)) {
            redirectUrl = "home";
        } else if ("login".equals(action)) {
            redirectUrl = "login";
        } else if ("register".equals(action)) {
            redirectUrl = "register";
        } else if ("search".equals(action)) {
            redirectUrl = "search";
        } else if ("addToCart".equals(action)) {
            redirectUrl = "addToCart";
        } else if ("viewCart".equals(action)) {
            redirectUrl = "viewCart";
        } else if ("btn-update".equals(action)) {
            redirectUrl = "updateCartItem";
        } else if ("deleteCartItem".equals(action)) {
            redirectUrl = "deleteCartItem";
        } else if ("checkout".equals(action)) {
            redirectUrl = "checkout";
        } else if ("logout".equals(action)) {
            redirectUrl = "logout";
        } else if ("viewAllOrders".equals(action)) {
            redirectUrl = "viewAllOrders";
        } else if ("viewOrderDetail".equals(action)) {
            redirectUrl = "viewOrderDetail";
        } else if ("changeProfile".equals(action)) {
            redirectUrl = "changeProfile";
        } else if ("changeOrderStatus".equals(action)) {
            redirectUrl = "changeOrderStatus";
        } else if ("viewPlant".equals(action)) {
            redirectUrl = "viewPlant";
        } else {
            redirectUrl = "home";
        }

        request.getRequestDispatcher(redirectUrl)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
