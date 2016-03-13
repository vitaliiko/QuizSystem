<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AJAX</title>

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
    <%--<spring:url value="/resources/jquery.js" var="jquery.js"/>--%>
    <%--<script src="${jquery.js}"></script>--%>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>

    <script>
        var questionId;
        var questionsMax;
        var questionsCounter = 1;
        var time;
        var timeLimit;
        var intervalId;

        function getLimits() {
            $.get('/quiz/getLimits', function(limits) {
                questionsMax = limits[0];
                $('#counter').text('1 of ' + questionsMax);
                timeLimit = limits[1];
                time = limits[1];
                $('#timer').text('00:' + time);
            });
        }

        function getQuestion(answer) {
            $.getJSON('/quiz/getQuestion', {answer: answer, questionId: questionId}, function(question) {
                var points = "";
                $('#questionText').text(question.questionText);
                questionId = question.id;
                $.each(question.answers, function(index, value) {
                    points += "<p><input type='radio' name='answer' value=" + value.text + ">" + value.text + "</p>";
                });
                $('#answersDiv').html(points);
            });
        }

        function getResult() {
            clearInterval(intervalId);
            $.getJSON('/quiz/getResult', function (result) {
                $('#questionDiv').hide();
                $('#resultDiv').show();
                $('#spentTime').text('Spent time: ' + result.spentTime);
                $('#rightAnswers').text('Right answers: ' + result.rightAnswers + '/' + result.questionsCount);
                $('#points').text('Result: ' + result.points);
                $('#attempts').text('Attempts: ' + result.attempts);
                $('#messageText').text(result.messageText);
            })
        }

        $(document).ready(function() {
            getQuestion();
            getLimits();
            startTimer();
            $('#questionDiv').show();
            $('#resultDiv').hide();

            $('#sendAnswer').click(function() {
                var userSelection = $("input[name='answer'][type='radio']:checked");
                if (userSelection.length) {
                    $('#messageText').text('');
                    if (!checkQuestionsCount()) {
                        getResult();
                    }
                    time = timeLimit;
                    getQuestion(userSelection.val());
                } else {
                    $('#messageText').text('Please, select answer');
                }
            });
        });

        function checkQuestionsCount() {
            if (questionsCounter == questionsMax) {
                return false;
            }
            $('#counter').text(++questionsCounter + ' of ' + questionsMax);
            if (questionsCounter == questionsMax) {
                $('#sendAnswer').val('Done');
            }
            return true;
        }

        function startTimer() {
            intervalId = setInterval(function () {
                $('#timer').text((time < 10 ? '00:0' : '00:') + time);
                if (time == 0) {
                    if (checkQuestionsCount()) {
                        getQuestion();
                    } else {
                        getResult();
                    }
                    time = timeLimit;
                }
                time--;
            }, 1000);
        }
    </script>

</head>

<body>
    <div id="messageDiv" align="center">
        <p id="messageText"></p>
    </div>

    <div id="questionDiv" align="center" style="width: 1000px;">
        <p id="timer"></p>
        <p id="counter"></p>
        <p id="questionText"></p>
        <div id="answersForm">
            <div id="answersDiv" align="left" style="position: absolute"></div>
            <input id="sendAnswer" type="button" value="next">
        </div>
    </div>

    <form action="/quiz/startTest" id="resultDiv">
        <div align="center">
            <p id="rightAnswers"></p>
            <p id="points"></p>
            <p id="spentTime"></p>
            <p id="attempts"></p>
            <input type="submit" value="Try Again"/>
        </div>
    </form>
</body>
</html>
