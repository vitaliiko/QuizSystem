<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Question</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js" ></script>

    <script>
        $(document).ready(function() {
            var i = 3;
            var maxFieldsCount = 6;

            $('#add').click(function() {
                if (i < maxFieldsCount) {
                    i++;
                    $("<div class='answerDiv'><input type='radio' name='answer' value=" + i + ">" +
                            "<input type='text' class='field' name='dynamic[]'/></div>").fadeIn('slow').appendTo('.inputs');
                }
            });

            $('#remove').click(function() {
                if (i > 2) {
                    $('.answerDiv:last').remove();
                    i--;
                }
            });

            $('.submit').click(function() {
                var question = $('#question').val();
                var answers = [];
                var rightAnswer = $("input[name='answer'][type='radio']:checked");
                if (!question.length) {
                    alert("Please, input question!");
                    return false;
                }
                if (!rightAnswer.length) {
                    alert("Please check right answer");
                    return false;
                }
                if (answers.size < 2) {
                    alert("Answers count must be not less 2");
                    return false;
                }
                $.each($('.field'), function() {
                    answers.push($(this).val());
                });
                $.ajax({
                    url: '/quiz/addQuestion',
                    data: {question: question, answers: answers, rightAnswer: rightAnswer.val()},
                    type: 'POST',
                    success: function(data) {
                        alert(data);
                    }
                });
                return false;
            });
        });
    </script>

</head>
<body>
    <div class="dynamic-form" align="center">

        <a href="#" id="add">Add</a> | <a href="#" id="remove">Remove</a>

        <div class="inputs">
            <textarea id="question" rows="5" cols="50" placeholder="Question"></textarea>
            <p>Answers:</p>
            <div class="answerDiv"><input type="radio" name="answer" value="1"><input type="text" name="dynamic[]" class="field"/></div>
            <div class="answerDiv"><input type="radio" name="answer" value="2"><input type="text" name="dynamic[]" class="field"/></div>
            <div class="answerDiv"><input type="radio" name="answer" value="3"><input type="text" name="dynamic[]" class="field"/></div>
        </div>
        <input name="submit" type="button" class="submit" value="Submit" />
    </div>

</body>
</html>
