package se160205.servlet;

import se160205.dao.AccountDAO;
import se160205.dto.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huynh Van Phuot
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phoneNumber");

        boolean hasError = false;

        if (email == null || email.length() == 0) {
            request.setAttribute("emailError", "Email is required");
            hasError = true;
        }
        if (fullname == null || fullname.length() == 0) {
            request.setAttribute("fullnameError", "Fullname is required");
            hasError = true;
        }
        if (password == null || password.length() == 0) {
            request.setAttribute("passwordError", "Password is required");
            hasError = true;
        }

        if (password != null && password.length() < 6) {
            request.setAttribute("passwordError", "Password must contain at least 6 characters");
            hasError = true;
        }

        if (phone == null || phone.length() == 0 || !phone.matches("^[0-9]{10}$")) {
            request.setAttribute("phoneError", "Phone must have 10 digits");
            hasError = true;
        }

        if (!hasError) {

            int status = 1;
            int role = 0;

            boolean result = AccountDAO.insertAccount(email, password, fullname, phone, status, role);
            if (!result) {
                request.setAttribute("insertError", "Email existed");
            } else {
                response.sendRedirect("mainController?action=login");
                return;
            }
        }

        Account errorAccount = new Account(email, password, fullname, phone);
        request.setAttribute("errorAccount", errorAccount);

        System.out.println(errorAccount);
        System.out.println(hasError);
        request.getRequestDispatcher("./views/register.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
