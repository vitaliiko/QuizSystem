<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>AJAX</title>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js" ></script>

        <script type="text/javascript">
            function crunchifyAjax() {
                $.ajax({
                    url: 'ajaxtest',
                    success: function(data) {
                        $('#result').html(data);
                    }
                });
            }
        </script>

        <script>
            setInterval(crunchifyAjax, 3 * 1000);
        </script>
    </head>

    <body>
        <div align="center">
            <h1>Hello</h1>
            <p>
                User ID : ${userId}
            </p>
            <div id="result"></div>

            <c:forEach var="question" items="${questions}">
                ${question.questionText} <br>
                <c:forEach var="answer" items="${question.answers}">
                    ${answer.text} <br>
                </c:forEach>
                <br>
            </c:forEach>
        </div>
    </body>
</html>