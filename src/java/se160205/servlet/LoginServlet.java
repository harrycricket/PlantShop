package se160205.servlet;

import se160205.dao.AccountDAO;
import se160205.dto.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh Van Phuot
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMeString = request.getParameter("rememberMe");
        HttpSession session = request.getSession(true);

        boolean rememberMe = "y".equalsIgnoreCase(rememberMeString) ? true : false;
        boolean hasError = false;
        Account acc = null;

        System.out.println(email + " " + password + " " + rememberMe);

        if (email == null || email.trim().length() == 0) {
            request.setAttribute("emailError", "Email is required");
            hasError = true;
        } else if (password == null || password.trim().length() == 0) {
            request.setAttribute("passwordError", "Password is required");
            hasError = true;
        }

        if (!hasError) {
            acc = AccountDAO.getAccount(email, password);
            if (acc != null) {
                session.setAttribute("loginedUser", acc);

                //if use choose remember me, save cookie
                if (rememberMe) {
                    //store cookie for one day
                    final int TIME = 24 * 60 * 60;
                    String token = System.currentTimeMillis() + "";
                    Cookie userCookie = new Cookie("token", token);
                    //update token in database
                    AccountDAO.updateToken(email, token);
                    userCookie.setMaxAge(TIME);
                    response.addCookie(userCookie);
                }
            } else {
                request.setAttribute("loginError", "Username or password is incorrect");
                hasError = true;
            }
        }

        if (hasError) {
            acc = new Account(email, password);
            request.setAttribute("errorAccount", acc);
            request.setAttribute("rememberMe", rememberMe);
            request.getRequestDispatcher("./views/login.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
            return;
        }

    }

    @Override
    public String getServletInfo() {
        return "";
    }

}
