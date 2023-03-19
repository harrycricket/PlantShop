package se160205.servlet;

import se160205.dao.PlantDAO;
import se160205.dto.Plant;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huynh Van Phuot
 */
public class SearchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String textSearch = request.getParameter("textSearch");
        String searchBy = request.getParameter("searchBy");

        System.out.println(textSearch + " " + searchBy);

        String redirectUrl;

        if (textSearch == null || textSearch.trim().length() == 0) {
            request.setAttribute("searchError", "Enter plant or categories to search");
            redirectUrl = "home";
        } else {

            List<Plant> result = PlantDAO.findPlants(textSearch, searchBy);

            request.setAttribute("textSearch", textSearch);
            request.setAttribute("searchBy", searchBy);

            request.setAttribute("searchResult", result);
            redirectUrl = "./views/user/search.jsp";
        }

        request.getRequestDispatcher(redirectUrl).forward(request, response);
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
