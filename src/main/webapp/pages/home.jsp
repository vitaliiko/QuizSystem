<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>

    <link href="${pageContext.request.contextPath}/pages/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/pages/css/signin.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="header.jsp"/>
    <div class="container" align="center">
        <h4>Hello, ${user.firstName} ${user.lastName}</h4>
    </div>

    <form action="/quiz/startTest">
        <div class="container" style="width: 50%; margin: 0 auto;">
            <table class="table">
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
        </div>

        <div class="container" align="center">
            <br><br>
            <input type="submit" value="Start Test" class="btn btn-lg btn-danger">
        </div>

    </form>

            <br><br><br>

    <div class="container" style="width: 50%; margin: 0 auto;">
        <c:if test="${bestUsers.size() > 0}">
            <table class="table">
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
    </div>
</body>
</html>