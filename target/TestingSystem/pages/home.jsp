<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Quiz</title>
    </head>

    <body>
        <div align="center">

            <form action="/quiz/startTest">
                <h4>Hello, ${user.firstName} ${user.lastName}</h4>
                <table>
                    <tr>
                        <td>Total count of questions:</td>
                        <td>${questionsCount}</td>
                    </tr>
                    <tr>
                        <td>Questions in one quiz:</td>
                        <td>${questionsInQuiz}</td>
                    </tr>
                    <tr>
                        <td>Time limit:</td>
                        <td>${timeLimit} seconds per question</td>
                    </tr>
                    <tr>
                        <td>Attempts count:</td>
                        <td>${user.attempts}</td>
                    </tr>
                    <tr>
                        <td>Best result:</td>
                        <td>${user.bestResult}</td>
                    </tr>
                    <tr>
                        <td>Last attempt: </td>
                        <td>
                            <fmt:formatDate type="date" value="${user.date}"/>
                        </td>
                    </tr>
                </table>

                <input type="submit" value="Start Test">
                <br><br><br>

                <c:if test="${bestUsers.size() > 0}">
                    <table>
                        <caption style="font-weight: bold;">Best results</caption>
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