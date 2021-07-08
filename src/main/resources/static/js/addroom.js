$(document).on("click", ".add", function (event) {
    event.preventDefault();                                         // sprečavamo automatsko slanje zahteva da bismo pokupili (i validirali) podatke iz forme

    // preuzimamo vrednosti unete u formi
    let capacity = $("#capacity").val();
    let label = $("#label").val();


    let newRoom = {
        capacity,
        label
    }

    let centerId = window.localStorage.getItem('fitCenterId');
    let userType = window.localStorage.getItem('role')
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId + "/rooms?userType=" + userType,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newRoom),
        success: function (response) {
            console.log(response);

            alert("Sala je uspešno kreirana!");
            window.location.href = "rooms.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja sale!");
        }
    });
});
$(document).ready(function () {
    var userType = window.localStorage.getItem('role');
    if (userType != 3) {
        alert("Nedozvoljen pristup")
        window.location.href = "index.html";
    }
})