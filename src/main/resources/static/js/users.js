$(document).on("click", "#user", function (event) {
    event.preventDefault();

    // preuzimamo vrednosti unete u formi
    let username = $("#username").val();
    let password = $("#password").val();
    let name = $("#name").val();
    let surname = $("#surname").val();
    let phoneNumber = $("#phoneNumber").val();
    let emailAddress = $("#emailAddress").val();
    let birthday = $("#birthday").val();



    let newUser = {
        username,
        password,
        name,
        surname,
        phoneNumber,
        emailAddress,
        birthday
    }


    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/users",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUser),
        success: function (response) {
            console.log(response);

            alert("Korisnik je uspešno kreiran!");
            window.location.href = "index.html";                // privremeno POSLE PROMENI!!!!!
        },
        error: function () {
            alert("Greška prilikom dodavanja korisnika!");
        }
    });
});