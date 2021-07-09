$(document).on("click", ".btn", function (event) {
    event.preventDefault();                                         // sprečavamo automatsko slanje zahteva da bismo pokupili (i validirali) podatke iz forme

    // preuzimamo vrednosti unete u formi
    let date = $("#date").val();
    let price = $("#price").val();
    let roomId = $("#room").val();
    let trainingId = $("#training").val();
    let fitCenterId = window.localStorage.getItem('fitCenterId');
    let id = window.localStorage.getItem('id');
    let userType = window.localStorage.getItem('role');

    let termDTO = {
        date,
        price
    }


    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/"+ fitCenterId + "/terms?roomId=" + roomId + "&trainingId=" + trainingId + "&id=" + id + "&userType=" + userType,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(termDTO),
        success: function (response) {
            console.log(response);

            alert("Termin je uspešno kreiran!");
            window.location.href = "schedule.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja!");
        }
    });
});

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