<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<div>
    <form action="/quiz/signIn" method="post">
        <div align="center">
            <h2>Please sign in</h2>
            <h3>${errorMessage}</h3>

            <table>
                <tr>
                    <td><label for="inputFirstName">First name:</label></td>
                    <td><input type="text" name="firstName" id="inputFirstName" required="" autofocus=""></td>
                </tr>
                <tr>
                    <td><label for="inputLastName">Last Name:</label></td>
                    <td><input type="text" name="lastName" id="inputLastName" required="" autofocus=""></td>
                </tr>
                <tr>
                    <td><label for="inputEmail">Email:</label></td>
                    <td><input type="text" name="email" id="inputEmail" required="" autofocus=""></td>
                </tr>
            </table>
            <br>
            <button type="submit">Sign in</button>
        </div>
    </form>

    <form action="/quiz/signIn" method="post">
        <input type="hidden" name="firstName" value="Vasyl">
        <input type="hidden" name="lastName" value="Ivanov">
        <input type="hidden" name="email" value="vasyl.ivanov@gmail.com">
        <button type="submit">Default user</button>
    </form>
</div>
</body>
</html>
