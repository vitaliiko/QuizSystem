<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AJAX</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js" ></script>

    <script>
        function getQuestion() {
            $.getJSON('/user/getQuestion', function(question) {
                var result = "";
                $('#questionText').text(question.questionText);
                $.each(question.answers, function(index, value) {
                    result += "<p><input type='radio' name='answer' value=" + value.text + ">" + value.text + "</p>";
                });
                $('#answersDiv').html(result);
            });
        }

        $(document).ready(function() {
           $('#sendAnswer').click(function() {
               time = 30;
               var input = $("input[name='answer'][type='radio']:checked");
               var userSelection;
               if (input.length) {
                   userSelection = input.val();
               } else {
                   userSelection = null;
               }
               $.ajax({
                   url: '/user/setUserAnswer',
                   data: {userSelection},
                   type: 'POST',
                   success: getQuestion()
               });
           });
        });

        var time = 30;
        setInterval(function() {
            $('#timer').text('00:' + time);
            time--;
            if (time == 0) {
                getQuestion();
                time = 30;
            }
        }, 1000);
    </script>

</head>

<body onload="getQuestion();">
    <form id="questionForm">
        <div align="center">
            <div id="timer">00:30</div>
            Question:
            <p id="questionText"></p>
            <br>
            <form id="answersForm">
                <div id="answersDiv"></div>
                <input id="sendAnswer" type="button" value="next">
            </form>

        </div>
    </form>

</body>
</html>
