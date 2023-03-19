package se160205.servlet;

import se160205.dao.OrderDAO;
import se160205.dto.Order;
import se160205.utils.DateUtils;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huynh Van Phuot
 */
public class ViewAllOrdersAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String filterMode = request.getParameter("filter");

        List<Order> listOrders = null;

        if ("byacc".equals(filterMode)) {
            String email = request.getParameter("email");
            if (email != null && !email.trim().isEmpty()) {
//                listOrders = OrderDAO.getOrders(email, 0);
            }
            request.setAttribute("filter", "byacc");
            request.setAttribute("email", email);

        } else if ("bydate".equals(filterMode)) {
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            request.setAttribute("filter", "bydate");
            request.setAttribute("dateFrom", dateFrom);
            request.setAttribute("dateTo", dateTo);

            if (dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty()) {
                Date dFrom = new Date(DateUtils.parse(dateFrom).getTime());
                Date dTo = new Date(DateUtils.parse(dateTo).getTime());

                if (dFrom != null && dTo != null && dFrom.compareTo(dTo) <= 0) {
                    listOrders = OrderDAO.getOrders(dFrom, dTo);
                }
            }
        }

        if (listOrders == null) {
            listOrders = OrderDAO.getOrders();
        };

        request.setAttribute("listOrders", listOrders);
        request.getRequestDispatcher("./views/admin/orders.jsp").forward(request, response);

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
