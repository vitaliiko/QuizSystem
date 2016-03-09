<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<div>
    <form action="/user/signIn" method="post">
        <h2>Please sign in</h2>

        <h3>${errorMessage}</h3>

        <label for="inputFirstName">First name</label>
        <input type="text" name="firstName" id="inputFirstName" required="" autofocus="">

        <label for="inputLastName">Last Name</label>
        <input type="text" name="lastName" id="inputLastName" required="" autofocus="">

        <label for="inputEmail">Email</label>
        <input type="text" name="email" id="inputEmail" required="" autofocus="">

        <button type="submit">Sign in</button>
    </form>

    <form action="/user/signIn" method="post">
        <input type="hidden" name="firstName" value="Vasyl">
        <input type="hidden" name="lastName" value="Ivanov">
        <input type="hidden" name="email" value="vasyl.ivanov@gmail.com">
        <button type="submit">Default user</button>
    </form>
</div>
</body>
</html>
