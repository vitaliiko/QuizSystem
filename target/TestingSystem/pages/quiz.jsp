<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>

    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/js/jquery.js"></script>

</head>

<body>
    <jsp:include page="header.jsp"/>
    <div id="messageDiv" align="center">
        <h3 id="messageText"></h3>
    </div>

    <div id="questionDiv" style="width: 20%; margin: 0 auto;">
        <div align="center">
            <p id="counter"></p>
            <p id="questionText"></p>
        </div>

        <div id="answersForm">
            <div id="answersDiv"></div>
        </div>

        <div align="center">
            <p id="timer"></p>
            <input id="sendAnswer" type="button" value="Next">
        </div>
    </div>

    <form action="/quiz/startTest" id="resultDiv">
        <div align="center">
            <table>
                <caption style="font-weight: bold;">Results</caption>
                <tr>
                    <td align="right">Right answers:</td>
                    <td><p id="rightAnswers"></p></td>
                </tr>
                <tr>
                    <td align="right">Points:</td>
                    <td><p id="points"></p></td>
                </tr>
                <tr>
                    <td align="right">Spent time:</td>
                    <td><p id="spentTime"></p></td>
                </tr>
                <tr>
                    <td align="right">Attempts:</td>
                    <td><p id="attempts"></p></td>
                </tr>
            </table>

            <br>
            <input type="submit" value="Try Again"/>
        </div>
    </form>
</body>
</html>
