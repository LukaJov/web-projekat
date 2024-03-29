$(document).on("click", "#trainer", function (event) {
    event.preventDefault();                                         // sprečavamo automatsko slanje zahteva da bismo pokupili (i validirali) podatke iz forme

    // preuzimamo vrednosti unete u formi
    let username = $("#username").val();
    let password = $("#password").val();
    let name = $("#name").val();
    let surname = $("#surname").val();
    let phoneNumber = $("#phoneNumber").val();
    let emailAddress = $("#emailAddress").val();
    let birthday = $("#birthday").val();



    let newTrainer = {
        username,
        password,
        name,
        surname,
        phoneNumber,
        emailAddress,
        birthday
    }

    let userType = window.localStorage.getItem('role');
    if(userType ===null || userType===undefined)
    {
        userType = 0;
    }
    let fitCenterId = window.localStorage.getItem('fitCenterId');
    if(fitCenterId===null)
    {
        fitCenterId = 1;
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/" + fitCenterId + "/trainers?role=" + userType ,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTrainer),
        success: function (response) {
            console.log(response);

            alert("Trener je uspešno kreiran!");

            if(userType!=3) {
                window.location.href = "index.html";
            }
            else {
                window.location.href = "rooms.html";
            }
        },
        error: function () {
            alert("Greška prilikom dodavanja trenera!");
        }
    });
});

