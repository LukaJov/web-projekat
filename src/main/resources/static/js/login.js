$(document).on('click', '#member', function () {

    let username = $("#username").val();
    let password = $("#password").val();

    let query = "username=" + username + "&" + "password=" + password + "&" + "userType=Member";
    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/login" +"?" + query,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            alert("Uspesno ste se ulogovali!");// prikazujemo poruku uspeha korisniku
            window.location.href = "terms.html";
            window.localStorage.setItem('role', '1');
            window.localStorage.setItem('id', response.id);

        },
        error: function (response) {
            console.log("ERROR:\n", response);
            alert("Neuspesno logovanje!");
        }
    });
});

$(document).on('click', '#trainer', function () {

    let username = $("#username").val();
    let password = $("#password").val();
    let query = "username=" + username + "&" + "password=" + password + "&" + "userType=Trainer";

    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/login" + "?"+ query,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            alert("Uspesno ste se ulogovali!");//
            window.location.href = "trainerterms.html";
            window.localStorage.setItem('role', '2');
            window.localStorage.setItem('id', response.id);

        },
        error: function (response) {
            console.log("ERROR:\n", response);
            alert("Neuspesno logovanje!");
        }
    });
});

$(document).on('click', '#admin', function () {

    let username = $("#username").val();
    let password = $("#password").val();
    let query = "username=" + username + "&" + "password=" + password + "&" + "userType=Admin";

    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/login" + "?" + query,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            alert("Uspesno ste se ulogovali!");//
            window.location.href = "fitnesscenters.html";
            window.localStorage.setItem('role', '3');
            window.localStorage.setItem('id', response.id);

        },
        error: function (response) {
            console.log("ERROR:\n", response);
            alert("Neuspesno logovanje!");
        }
    });
});
