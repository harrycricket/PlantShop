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
public class AdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String redirectUrl = "";

        if ("viewAllUsers".equals(action)) {
            redirectUrl = "viewAllUsers";
        } else if ("updateAccStatus".equals(action)) {
            redirectUrl = "updateAccStatus";
        } else if ("viewAllOrders".equals(action)) {
            redirectUrl = "viewAllOrdersAdmin";
        } else if ("viewAllPlants".equals(action)) {
            redirectUrl = "viewAllPlants";
        } else if ("updatePlant".equals(action)) {
            redirectUrl = "savePlant?action=update";
        } else if ("addPlant".equals(action)) {
            redirectUrl = "savePlant?action=add";
        } else if ("deletePlant".equals(action)) {
            redirectUrl = "deletePlant";
        } else if ("viewAllCategories".equals(action)) {
            redirectUrl = "viewAllCategories";
        } else if ("updateCategory".equals(action)) {
            redirectUrl = "saveCategory?action=update";
        } else if ("addCategory".equals(action)) {
            redirectUrl = "saveCategory?action=add";
        } else {
            redirectUrl = "viewAllUsers";
        }

        request.getRequestDispatcher(redirectUrl).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
