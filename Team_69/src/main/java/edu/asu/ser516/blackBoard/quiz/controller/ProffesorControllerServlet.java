package edu.asu.ser516.blackBoard.quiz.controller;
import java.io.IOException;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.asu.ser516.blackBoard.quiz.bean.Proffessor;
import edu.asu.ser516.blackBoard.quiz.dao.ProffessorDAO;


public class ProffesorControllerServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if("fetchQuizList".equals(flag)){
			System.out.println("Hi!....");
			ProffessorDAO proffessorDAO = new ProffessorDAO();
			List quizList = proffessorDAO.getAllQuizzes();
			request.setAttribute("quizList", quizList);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/displayQuizDetails.jsp");
			rd.forward(request, response);
			
			
		}
	}

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        

        String flag = request.getParameter("flag");
		if("InsertProfDetails".equals(flag)){
			System.out.println("Hi!....");
			String name = request.getParameter("name");
	        String instructions = request.getParameter("instructions");
	        String quiz_type = request.getParameter("quiz_type");
	        String assignment_group = request.getParameter("assignment_group");
	        String shuffle = request.getParameter("shuffle");
	        String time_limit = request.getParameter("time_limit");
	        String attempts = request.getParameter("attempts");

			ProffessorDAO proffessorDAO = new ProffessorDAO();
			//System.out.println("Quiz : "+proffessorDAO.InsertProfDetails());
		
            response.sendRedirect("professorDetail.jsp");
        }  

    }

}
