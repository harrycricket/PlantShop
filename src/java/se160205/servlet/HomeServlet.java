package se160205.servlet;

import se160205.dao.PlantDAO;
import se160205.dto.Plant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huynh Van Phuot
 */
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        List<Plant> listAllPlants = PlantDAO.getAllPlants();

//        PATINATION
        int totalSize = listAllPlants.size();
        int currentPage = 1;
        int recordsPerPage = 8;
        int numOfPages = (int) Math.ceil(totalSize * 1.0 / recordsPerPage);

        String page = request.getParameter("page");
        try {
            currentPage = Integer.parseInt(page);
            if (currentPage > numOfPages) {
                currentPage = 1;
            }
        } catch (Exception e) {
            currentPage = 1;
        }

        int start = (currentPage - 1) * recordsPerPage;

        List<Plant> list = new ArrayList<>();

        if (currentPage != numOfPages) {
            list = listAllPlants.subList(start, start + recordsPerPage);
        } else {
            list = listAllPlants.subList(start, totalSize);
        }

        System.out.println("current page: " + currentPage);
        System.out.println("List: " + list);
        System.out.println("Size: " + totalSize);
        System.out.println("totalPage: " + numOfPages);
        System.out.println("perPage: " + recordsPerPage);

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("listPlants", list);
        request.getRequestDispatcher("./views/user/home.jsp").forward(request, response);
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
