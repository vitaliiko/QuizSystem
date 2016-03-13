<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question</title>
</head>
<body>
    ${question.questionText}
    <c:forEach var="answer" items="${question.answers}">
        ${answer.text}
    </c:forEach>
</body>
</html>
