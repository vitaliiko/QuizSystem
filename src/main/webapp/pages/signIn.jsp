<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>

    <link href="${pageContext.request.contextPath}/pages/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/pages/css/signin.css" rel="stylesheet">
</head>
<body>
    <div class="container" style="width: 300px;">
        <form action="/quiz/signIn" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>

            <h3>${errorMessage}</h3>

            <input type="text" class="form-control" name="firstName" placeholder="First Name" id="inputFirstName" required="" autofocus="">

            <input type="text" class="form-control" name="lastName" placeholder="Last Name" id="inputLastName" required="" autofocus="">

            <input type="text" class="form-control" name="email" placeholder="E-mail" id="inputEmail" required="" autofocus="">

            <button type="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>
        </form>
    </div>

</body>
</html>
