$(document).ready(function () {
    var userType = window.localStorage.getItem('role');
    if (userType != 2) {
        alert("Nedozvoljen pristup")
        window.location.href = "index.html";
    }
    var id = window.localStorage.getItem('id');

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnesscenters/my?trainerId=" + id,
        dataType: "json",
        success: function (response) {
            console.log(response);

            window.localStorage.setItem('fitCenterId', response.id);
        },
        error: function () {

        }
    });
})