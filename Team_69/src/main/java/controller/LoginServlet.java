package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.LoginServices;
import services.StudentServices;

/**
 * Controller class for handling Login requests
 *
 * @author : Jahnvi Rai
 * @version : 1.0
 * @since : 02/19/2019
 */

public class LoginServlet extends HttpServlet{
    /**
     * Handles the get request redirecting the user to the student landing
     * page
     *
     * @param request
     * @param response
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(200);
        StudentServices studentServices = new StudentServices();
        List<String> quizNames =studentServices.fetchAllQuizNames();
        List<Integer> quizIds = studentServices.fetchAllQuizIds(quizNames);
        List<String> quizStatus = studentServices.fetchQuizStatus(quizNames);
        request.setAttribute("quizNames",quizNames);
        request.setAttribute("quizStatus",quizStatus);
        request.setAttribute("quizIds",quizIds);
        getServletContext().getRequestDispatcher("/views/studentLanding.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(200);
        String userEmail = (String) request.getAttribute("userEmai");
        String userPassword = (String) request.getAttribute("userPassword");
        HttpSession session = request.getSession();
        LoginServices loginServices = new LoginServices();
        String userType = loginServices.checkUserType(userEmail);
        int userId = loginServices.fetchUserId(userEmail);
        session.setAttribute("userId",userId);
        if(userType.equals("Student")){
            getServletContext().getRequestDispatcher("/views/studentLanding.jsp").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/views/professorLanding.jsp").forward(request, response);
        }

    }
}
