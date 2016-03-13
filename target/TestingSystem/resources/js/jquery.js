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
    $.getJSON('/quiz/getQuestion', {answer : answer, questionId : questionId}, function(question) {
        var result = "";
        $('#questionText').text(question.questionText);
        questionId = question.id;
        $.each(question.answers, function(index, value) {
            result += "<p><input type='radio' name='answer' value=" + value.text + ">" + value.text + "</p>";
        });
        $('#answersDiv').html(result);
    });
}

function getResult() {
    $.getJSON('/quiz/getResult', function (result) {
        $('#questionDiv').hide();
        $('#resultDiv').show();
        $('#spentTime').text('Spent time: ' + result.spentTime);
        $('#rightAnswers').text('Right answers: ' + result.rightAnswers + '/' + result.questionsCount);
        $('#points').text('Result: ' + result.result);
        $('#attempts').text('Attempts: ' + result.attempts);
        $('#messageText').text(result.messageText);
    })
}

$(document).ready(function() {
    getQuestion();
    getMaxQuestionsCount();
    $('#questionDiv').show();
    $('#resultDiv').hide();
    $('#sendAnswer').click(function() {
        var userSelection = $("input[name='answer'][type='radio']:checked");
        if (userSelection.length) {
            $('#messageText').text('');
            if (!checkQuestionsCount()) {
                getResult();
            }
            time = 30;
            getQuestion(userSelection.val());
        } else {
            $('#messageText').text('Please, select answer');
        }
    });
});

function checkQuestionsCount() {
    $('#counter').text(++questionsCounter + ' of 10');
    if (questionsCounter == 9) {
        $('#sendAnswer').val('Done');
    }
    return questionsCounter != 10;
}

setInterval(function() {
    $('#timer').text((time < 10 ? '00:0' : '00:') + time);
    if (time == 0) {
        if (checkQuestionsCount()) {
            getQuestion();
        } else {
            getResult();
        }
        time = 30;
    }
    time--;
}, 1000);