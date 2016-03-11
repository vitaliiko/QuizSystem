<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AJAX</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js" ></script>

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
               var userSelection = $("input[name='answer'][type='radio']:checked");
               if (userSelection.length) {
                   time = 30;
                   $.ajax({
                       url: '/user/setUserAnswer',
                       data: userSelection.val(),
                       type: 'POST',
                       success: getQuestion()
                   });
               } else {
                   alert("Please, select answer")
               }

           });
        });

        var time = 30;
        setInterval(function() {
            $('#timer').text((time < 10 ? '00:0' : '00:') + time);
            if (time == 0) {
                getQuestion();
                time = 30;
            }
            time--;
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
