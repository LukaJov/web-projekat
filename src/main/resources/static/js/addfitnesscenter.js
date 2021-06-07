$(document).on("click", ".btn", function (event) {
    event.preventDefault();                                         // sprečavamo automatsko slanje zahteva da bismo pokupili (i validirali) podatke iz forme

    // preuzimamo vrednosti unete u formi
    let name = $("#name").val();
    let address = $("#address").val();
    let phoneNumber = $("#phoneNumber").val();
    let emailAddress = $("#emailAddress").val();



    let newFitnessCenter = {
        name,
        address,
        phoneNumber,
        emailAddress
    }


    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/fitnesscenters",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newFitnessCenter),
        success: function (response) {
            console.log(response);

            alert("Fitnes centar je uspešno kreiran!");
            window.location.href = "fitnesscenters.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja fitnes centra!");
        }
    });
});
