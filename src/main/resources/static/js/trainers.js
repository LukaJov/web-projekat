$(document).on("click", "#trainer", function (event) {     // kada je submit-ovana forma za kreiranje novog zaposlenog
    event.preventDefault();                                         // sprečavamo automatsko slanje zahteva da bismo pokupili (i validirali) podatke iz forme

    // preuzimamo vrednosti unete u formi
    let username = $("#username").val();
    let password = $("#password").val();
    let name = $("#name").val();
    let surname = $("#surname").val();
    let phoneNumber = $("#phoneNumber").val();
    let emailAddress = $("#emailAddress").val();
    let birthday = $("#birthday").val();


    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    let newTrainer = {
        username,
        password,
        name,
        surname,
        phoneNumber,
        emailAddress,
        birthday
    }

    // ajax poziv za kreiranje novog zaposlenog na backend-u
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/trainers",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(newTrainer),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (response) {
            console.log(response);

            alert("Trener je uspešno kreiran!");// prikazujemo poruku uspeha korisniku
            window.location.href = "index.html";                // privremeno POSLE PROMENI!!!!!
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška prilikom dodavanja trenera!");
        }
    });
});