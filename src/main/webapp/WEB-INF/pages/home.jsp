<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Quiz</title>
    </head>

    <body>
        <div align="center">
            <form action="/quiz/startTest">
                <h2>Hello, ${user.firstName} ${user.lastName}</h2>
                <p>
                    Questions in test: ${questionsCount}
                    Attempts count: ${user.attempts}
                    Best result: ${user.bestResult}
                    Last attempt: ${user.date}
                </p>
                <input type="submit" value="Start Test">
            </form>
        </div>
    </body>
</html>