<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AJAX</title>

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
    <%--<spring:url value="/resources/jquery.js" var="jquery.js"/>--%>
    <%--<script src="${jquery.js}"></script>--%>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js" ></script>

    <script>
        var questionId;
        var questionsMax;
        var questionsCounter = 1;
        var time = 30;

        function getMaxQuestionsCount() {
            $.get('/quiz/getMaxQuestionsCount', function(count) {
                questionsMax = count;
                $('#counter').text('1 of ' + questionsMax);
            });
        }

        function getQuestion(answer) {
            $.getJSON('/quiz/getQuestion', {answer:answer, questionId:questionId}, function(question) {
                var result = "";
                $('#questionText').text(question.questionText);
                questionId = question.id;
                $.each(question.answers, function(index, value) {
                    result += "<p><input type='radio' name='answer' value=" + value.text + ">" + value.text + "</p>";
                });
                $('#answersDiv').html(result);
            });
        }

        $(document).ready(function() {
            getQuestion();
            getMaxQuestionsCount();
            $('#sendAnswer').click(function() {
                var userSelection = $("input[name='answer'][type='radio']:checked");
                if (!checkQuestionsCount()) {
                    return;
                }
                if (userSelection.length) {
                    time = 30;
                    getQuestion(userSelection.val());
                } else {
                    alert("Please, select answer");
                }

            });
        });

        function checkQuestionsCount() {
            $('#counter').text(++questionsCounter + ' of 10');
            if (questionsCounter == 10) {
                $('#sendAnswer').val('Done');
                questionsCounter = 1;
                return false;
            }
            return true;
        }

        setInterval(function() {
            $('#timer').text((time < 10 ? '00:0' : '00:') + time);
            if (time == 0) {
                if (checkQuestionsCount()) {
                    getQuestion();
                } else {
                    return;
                }
                time = 30;
            }
            time--;
        }, 1000);
    </script>

</head>

<body>
    <form id="questionForm">
        <div align="center">
            <p id="timer">00:30</p>
            <p id="counter"></p>
            <p id="questionText"></p>
            <form id="answersForm">
                <div id="answersDiv"></div>
                <input id="sendAnswer" type="button" value="next">
            </form>

        </div>
    </form>

</body>
</html>
