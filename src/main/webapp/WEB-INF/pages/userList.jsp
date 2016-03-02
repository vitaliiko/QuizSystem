<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="list" type="java.util.List<com.testingSystem.entity.User>"--%>

<html>
<body>
    <table>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Admin</th>
        </tr>
    <c:forEach var="user" items="${list}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.admin}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>