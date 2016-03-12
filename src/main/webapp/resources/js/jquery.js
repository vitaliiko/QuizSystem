var questionId;
var questionsMax;
var questionsCounter = 1;
var time = 30;

function getMaxQuestionsCount() {
    $.get('/quiz/getMaxQuestionsCount', function(count) {
        questionsMax = count;
        $('#counter').text('1 of ' + questionsMax);
    });
}

function getQuestion(answer) {
    $.getJSON('/quiz/getQuestion', {answer:answer, questionId:questionId}, function(question) {
        var result = "";
        $('#questionText').text(question.questionText);
        questionId = question.id;
        $.each(question.answers, function(index, value) {
            result += "<p><input type='radio' name='answer' value=" + value.text + ">" + value.text + "</p>";
        });
        $('#answersDiv').html(result);
    });
}

$(document).ready(function() {
    $('#sendAnswer').click(function() {
        var userSelection = $("input[name='answer'][type='radio']:checked");
        if (!checkQuestionsCount()) {
            return;
        }
        if (userSelection.length) {
            time = 30;
            getQuestion(userSelection.val());
        } else {
            alert("Please, select answer");
        }

    });
});

function checkQuestionsCount() {
    $('#counter').text(++questionsCounter + ' of 10');
    if (questionsCounter == 10) {
        $('#sendAnswer').val('Done');
        questionsCounter = 1;
        return false;
    }
    return true;
}

setInterval(function() {
    $('#timer').text((time < 10 ? '00:0' : '00:') + time);
    if (time == 0) {
        if (checkQuestionsCount()) {
            getQuestion();
        } else {
            return;
        }
        time = 30;
    }
    time--;
}, 1000);
