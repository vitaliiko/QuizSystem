<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tests</title>
</head>
<body>
    <c:forEach var="test" items="${tests}">
        ${test.name}
        ${test.date}
        ${test.points}
        ${test.spentTime}
    </c:forEach>
    ${message}

    <form action="/user/startTest" method="post">
        <input type="submit" value="Start test">
    </form>

</body>
</html>
