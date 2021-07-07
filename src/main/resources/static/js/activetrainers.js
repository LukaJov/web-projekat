$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trainers/active",
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
                let btn = "<button class='red' data-id=" + trainer.id + ">Remove</button>";
                row += "<td>" + btn + "</td>";

                $('#trainers').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});