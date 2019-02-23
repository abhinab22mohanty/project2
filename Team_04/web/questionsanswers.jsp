<%@ page import="com.model.QuestionAnswers" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.QuestionAnswers" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="student.dto.QuizContent" %>
<%@ page import="student.dto.AnswerOption" %><%--
  Created by IntelliJ IDEA.
  User: yuvan
  Date: 2/20/2019
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
</head>
<style>
    .QuesAnsDiv
    {
        width:50%;
        margin-top:10%;
        margin-left: 30%;
    }
    .navBtn
    {
        margin-left: 62%;
    }

    .prevBtn, .nextBtn
    {
        display: inline-block;
        white-space: nowrap;
        flex-basis: auto;
        width: auto;
        border: none;
        cursor: pointer;
        border-radius: 4px;
        text-align: center;
        font-family: CircularPro, "Helvetica Neue", Helvetica, "Segoe UI", Tahoma, Arial, sans-serif;
        font-weight: 400;
        line-height: 1.28571429;
        letter-spacing: .5px;
        text-transform: uppercase;
        padding: 10px 30px 10px;
        transition: box-shadow 420ms cubic-bezier(.165, .84, .44, 1), color 420ms cubic-bezier(.165, .84, .44, 1), background 420ms cubic-bezier(.165, .84, .44, 1);

    }
    .quesStyle
    {
        font-size: 200%;
        margin-bottom: 40px;

    }
    .options:hover
    {
        background-color: lightgray;
        cursor: pointer;

    }

</style>
<body>
<div class="QuesAnsDiv">

    <%
        QuizContent question = (QuizContent)request.getAttribute("data");
        String buttonType = "";
        if(question.getQues_type().equals("SA"))
        {
            buttonType = "radio";
        }
        else
        {
            buttonType = "checkbox";
        }

        int count = 1;
    %>

    <b> QUESTION <%= count++ %> :</b>
    <hr>
    <div class="quesStyle"><%=question.getQues_desc()%></div>
    <%
        for (AnswerOption answer : question.getAnswerOptions()){

           request.setAttribute("ansId", answer.getAns_id());
           request.setAttribute("ansDesc", answer.getAns_desc());
    %>

    <form method ="post">
    <div class="options"><input type=<%=buttonType%> class="optionTag" name="selectedOptionId" value="<%= answer.getAns_id() %>"><%= answer.getAns_desc() %></input></div>
    <%}%>

</div>
<div class="navBtn">

<input type="button" text="previous" value="previous" class="prevBtn"/>
<%--<input type="button" text="next" value="next" class="nextBtn"/>--%>
    <input type="submit" text="next" value="next" formaction="./loadquestionanswerservlet" name= "action" class="nextBtn"/>
    </form>
</div>

</body>
</html>
