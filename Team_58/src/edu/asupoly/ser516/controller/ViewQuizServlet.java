package edu.asupoly.ser516.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.util.Date;
=======
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
>>>>>>> d87851244f298198455f4723830f7203bbe9179d

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.asupoly.ser516.model.QuestionsVO;
import edu.asupoly.ser516.model.QuizVO;
import edu.asupoly.ser516.model.ViewQuizDAOBean;




/**
 * Servlet code takes quizId from courseDashboard.ftl  and renders a page displaying
 * information about the quiz and it's questions.
 * Database connections and retrievals are all conducted via the DAO interface.
 * @author Aditya Samant
 * @version 1.1
 * @see resources/courseDashboard.ftl
 * @see edu.asupoly.ser516.model/ViewQuizDAOBean.java
 * @see resources/viewQuiz.ftl
 * */
public class ViewQuizServlet extends HttpServlet{
	// This servlet will not make any Get requests.
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
	}
	/**
	 *Grabs quizId from courseDashboard and renders viewQuiz page
	 *
	 *The viewQuiz page displays what the selected quiz name is, its scheduled date whether it is graded or not graded
	 *and displays the question, the correct answer, the total points each question is worth and 
	 *@param req  Request made to server
	 *@param res  Responses from server
	 *
	 *@throws IOException
	 *@throws ServletException
	 * */
	public void doPost(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException{
		   
	       HttpSession session = req.getSession();
	       List<QuestionsVO> quizQuestions = new ArrayList<QuestionsVO>();
	       int quizId = Integer.parseInt(req.getParameter("Quiz"));
	       
	       
	       //Get today's date for comparison
	       Calendar cal = Calendar.getInstance();
	       cal.set(Calendar.HOUR_OF_DAY, 0);
	       cal.set(Calendar.MINUTE, 0);
	       cal.set(Calendar.SECOND,0);
	       cal.set(Calendar.MILLISECOND,0);
	       
	       Date today = new Date(cal.getTime().getTime());
	       boolean isAfter = false;
	       
	       //counter to addup and get total points for the quiz
	       int total = 0;
	       
	       try {
<<<<<<< HEAD
	    	   	   ViewQuizDAOBean bean = new ViewQuizDAOBean();
	    	   	   QuizVO quiz = bean.getQuizInfo(quizId);
	    	   	   quizQuestions = bean.getQuestionsInfo(quizId);
	    	   	   
	    	   	   for (int i = 0; i < quizQuestions.size(); i++) {
	    	   		 int points = quizQuestions.get(i).getTotalPoints();
	    	   		 total += points;
	    	   	   }
	    	   	   
	    	   	   Date scheduledDate = quiz.getQuizScheduledDate();
	    	   	   if (scheduledDate.before(today)) {
		   			   isAfter = true;
	    	   	   }
	    	   	   
	    	   	  // QuizVO quizInfo = new QuizVO(quizId, quizName);
		   		   
		   		   //Add Quiz info to Session attributes
		   		   session.setAttribute("Id", quizId);
		   		   session.setAttribute("Grade",quiz.isGraded());
		   		   session.setAttribute("Schedule", quiz.getQuizScheduledDate());
		   		   session.setAttribute("Directions", quiz.getQuizInstruction());
		   		   session.setAttribute("isAfter", isAfter);
		   		   session.setAttribute("QuizQuestions",quizQuestions);
		   		   session.setAttribute("Name", quiz.getQuizTitle());
		   		   session.setAttribute("Total", total);
	       
		   		   res.sendRedirect(req.getContextPath()+"/viewQuiz.ftl");
=======
	    	   //The information in the following items will be placed securely on a seperate file.
	    	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	  
	           String hostName = "showtimefinder.database.windows.net"; 
	           String dbName = "ser516_db"; 
	           String user = "scrum_mates@showtimefinder"; 
	           String password = "Azure@Cloud"; 
	           String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
	               + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
	           Connection connection = null;
	   		   connection = DriverManager.getConnection(url);
	   		   String schema;
	   		   schema = connection.getSchema();
	   		   System.out.println("Successful connection - Schema: " + schema); 
	   		   
	   		   //Query strings to gets entire question info and only the isGraded boolean from Quiz table
	   		   PreparedStatement query;
	   		   query = connection.prepareStatement("select A.*, B.isGraded, B.quizTitle, B.quizScheduledDate, B.quizInstruction " 
	   				+ "from [dbo].[Questions] A "
	   				+ "join [dbo].[Quiz] B "
	   				+ "on A.quizId = B.quizId "
	   		   		+ "where A.quizId = ?");
	   		   query.setInt(1, quizId);
	   		   ResultSet result = query.executeQuery();
	   		   
	   		   while (result.next()) {
	   			   //Questions DB results 
	   			   int questionId = result.getInt("questionId");
	   			   int points = result.getInt("totalPoints");
	   			   String question = result.getString("question");
	   			   //Values read as strings but actually JSON
	   			   String answer = result.getString("actualAnswer"); 
	   			   String choices = result.getString("totalChoices"); 
	   			   
	   			   //Parse Json String objects to json Object
	   			   JSONParser parser = new JSONParser();
	   			   JSONObject jo = (JSONObject) parser.parse(choices);
	   			   JSONObject jo2 = (JSONObject) parser.parse(answer);
	   			   
	   			   String choice1 = (String) jo.get("incorrectAnswer1");
	   			   String choice2 = (String) jo.get("incorrectAnswer2");
	   			   String choice3 = (String) jo.get("incorrectAnswer3");
	   			   String ans = (String) jo2.get("CORRECT");
	   			   
	   			   total += points;
	   			   
	   			   //Add to Quiz and Questions Objects
	   			   QuestionsVO quest = new QuestionsVO(questionId, points, question, ans, choice1, choice2, choice3);
	   			   quizQuestions.add(quest);
	   			   
	   			   //QuizDB results
	   			   if(result.getRow()==1) {
		   		   quizName = result.getString("quizTitle");
				   instruction = result.getString("quizInstruction");
				   scheduledDate = result.getDate("quizScheduledDate");
				   graded = result.getBoolean("isGraded"); 
	   			   }
	   		   }
	   		   
	   		   if (scheduledDate.before(today)) {
	   			   isAfter = true;
	   		   }
	   		   
	   		   QuizVO quizInfo = new QuizVO(quizId, quizName);
	   		   
	   		   //Add Quiz info to Session attributes
	   		   session.setAttribute("Id", quizId);
	   		   session.setAttribute("Grade", graded);
	   		   session.setAttribute("Schedule", scheduledDate);
	   		   session.setAttribute("Directions", instruction);
	   		   session.setAttribute("isAfter", isAfter);
	   		   session.setAttribute("QuizQuestions",quizQuestions);
	   		   session.setAttribute("quizInfo", quizInfo);
	   		   session.setAttribute("Total", total);
	   		   
	   		   System.out.println(quizInfo.getQuizTitle());
	   		
	   		   res.sendRedirect(req.getContextPath()+"/viewQuiz.ftl");
>>>>>>> d87851244f298198455f4723830f7203bbe9179d
	       }catch(Exception e) {
	    	   	   e.printStackTrace();
	       }
	}
}
