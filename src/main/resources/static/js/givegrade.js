$(document).on('click', '.give', function () {
    event.preventDefault();

    let id = window.localStorage.getItem('termId');

    let grade = $("#grade").val();
    let userId = window.localStorage.getItem('id');
    let userType = window.localStorage.getItem('role');

    let gradeDTO = {
        grade
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/terms/grades/" + id + "?userType=" + userType + "&userId=" + userId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(gradeDTO),
        success: function (response) {
            console.log("SUCCESS:\n", response);
            window.location.href = "done.html"

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});
