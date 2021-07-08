$(document).ready(function () {
    var fitCenterId = window.localStorage.getItem('fitCenterId');

        var userType = window.localStorage.getItem('role');
        if (userType != 3) {
            alert("Nedozvoljen pristup")
            window.location.href = "index.html";
        }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/" + fitCenterId+ "/trainers",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let trainer of response) {
                let row = "<tr>";
                row += "<td>" + trainer.name + "</td>";
                row += "<td>" + trainer.surname + "</td>";
                row += "<td>" + trainer.phoneNumber + "</td>";
                row += "<td>" + trainer.emailAddress + "</td>";
                row += "<td>" + trainer.birthday + "</td>";
                // kreiramo button i definisemo custom data atribut id = id zaposlenog
                let btn = "<button class='green' data-id=" + trainer.id + ">Accept</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#trainers').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

/*$(document).on('click', '#requests',function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trainers",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let trainer of response) {
                let row = "<tr>";
                row += "<td>" + trainer.name + "</td>";
                row += "<td>" + trainer.surname + "</td>";
                row += "<td>" + trainer.phoneNumber + "</td>";
                row += "<td>" + trainer.emailAddress + "</td>";
                row += "<td>" + trainer.birthday + "</td>";
                // kreiramo button i definisemo custom data atribut id = id zaposlenog
                let btn = "<button class='green' data-id=" + trainer.id + ">Accept</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#trainers').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});*/


$(document).on('click', '.green', function () {

    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    let trainerId = this.dataset.id;

    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/trainers/" + trainerId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            $('[data-id="' + trainerId + '"]').parent().parent().remove();

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

