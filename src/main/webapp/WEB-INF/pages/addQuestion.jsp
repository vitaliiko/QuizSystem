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
                    $('<div><input type="text" class="field" name="dynamic[]"/></div>').fadeIn('slow').appendTo('.inputs');
                    i++;
                }
            });

            $('#remove').click(function() {
                if (i > 2) {
                    $('.field:last').remove();
                    i--;
                }
            });

            $('.submit').click(function() {
                var answers = [];
                $.each($('.field'), function() {
                    answers.push($(this).val());
                });
                if(answers.length == 0) {
                    answers = "none";
                }
                $.ajax({
                    url: '/user/addQuestion',
                    data: {question: {id: 1, name: 'wwerwer'}, answers: [{name: 'fsdfsaf', checked: 'true'}, {name: 'fsdfsaf', checked: 'false'}, {name: 'fsdfsaf', checked: 'false'}]},
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
    <div class="dynamic-form">

        <a href="#" id="add">Add</a> | <a href="#" id="remove">Remove</a>

        <form>
            <div class="inputs">
                <div><input type="text" name="dynamic[]" class="field"/></div>
                <div><input type="text" name="dynamic[]" class="field"/></div>
                <div><input type="text" name="dynamic[]" class="field"/></div>
            </div>
            <input name="submit" type="button" class="submit" value="Submit" />
        </form>
    </div>

</body>
</html>
