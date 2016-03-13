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
                <p>Best points: ${user.bestResult}</p>
                <p>Last attempt: <fmt:formatDate type="date" value="${user.date}"/></p>
                <input type="submit" value="Start Test">
                <br><br>
                <c:if test="${bestUsers.size() > 0}">
                    <table>
                        <tr>
                            <td>Date</td>
                            <td>Name</td>
                            <td>Result</td>
                        </tr>
                        <c:forEach var="user" items="${bestUsers}">
                            <tr>
                                <td><fmt:formatDate type="date" value="${user.date}"/></td>
                                <td>${user.firstName} ${user.lastName}</td>
                                <td>${user.bestResult}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </form>
        </div>
    </body>
</html>