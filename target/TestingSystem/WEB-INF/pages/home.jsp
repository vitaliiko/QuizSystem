<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Quiz</title>
    </head>

    <body>
        <div align="center">
            <form action="/quiz/startTest">
                <h3>Hello, ${user.firstName} ${user.lastName}</h3>
                <p>Questions in test: ${questionsCount}</p>
                <p>Attempts count: ${user.attempts}</p>
                <p>Best result: ${user.bestResult}</p>
                <p>Last attempt: <fmt:formatDate type="date" value="${user.date}"/></p>
                <input type="submit" value="Start Test">
                <br><br>
                <p>Date &emsp;&emsp; User &emsp;&emsp; Result</p>
                <c:forEach var="user" items="${bestUsers}">
                    <p>
                        <fmt:formatDate type="date" value="${user.date}"/> &emsp;
                        ${user.firstName} ${user.lastName} &emsp; ${user.bestResult} &emsp;
                    </p>
                </c:forEach>
            </form>
        </div>
    </body>
</html>